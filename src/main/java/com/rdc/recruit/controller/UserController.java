package com.rdc.recruit.controller;

import com.rdc.recruit.annotation.IpRequest;
import com.rdc.recruit.config.GeetestConfig;
import com.rdc.recruit.entity.User;
import com.rdc.recruit.service.UserService;
import com.rdc.recruit.util.GeetestLib;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.HashMap;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/")
    public String Index(){
        return "forward:indexB.html";
    }

    @CrossOrigin
    @ResponseBody
    @GetMapping(value = "/user/init")
    public String geetestStart(HttpSession session){
        GeetestLib gtSdk = new GeetestLib(GeetestConfig.getGeetest_id(), GeetestConfig.getGeetest_key(), GeetestConfig.isNewfailback());
        String resStr = "{}";
        String userid = "test"; 

        //自定义参数,选择性添加
        HashMap<String, String> param = new HashMap<>();
        param.put("user_id", userid); //用户id
//        param.put("client_type", "web"); //用户访问类型
//        param.put("ip_address", "127.0.0.1"); //用户ip

        //进行验证预处理
        int gtServerStatus = gtSdk.preProcess(param);
        //session 设置服务器状态
        session.setAttribute(gtSdk.gtServerStatusSessionKey, gtServerStatus);
        //session 设置用户id
        session.setAttribute("userid", userid);
        resStr = gtSdk.getResponseStr();
        return resStr;
    }

    @CrossOrigin
    @IpRequest
    @ResponseBody
    @PostMapping(value = "/user/validateAndAdd")
    public String validateAndAdd(User user,HttpSession session,String validate,String challenge,String seccode){
        GeetestLib gtSdk = new GeetestLib(GeetestConfig.getGeetest_id(), GeetestConfig.getGeetest_key(), GeetestConfig.isNewfailback());
        //从session中获取 server状态 与 userid
        int gt_server_status_code = (int)session.getAttribute(gtSdk.gtServerStatusSessionKey);
        String userid = (String) session.getAttribute("userid");
        //自定义参数,选择性添加
        HashMap<String, String> param = new HashMap<>();
        param.put("user_id", userid); //用户id
//        param.put("client_type", "web"); //用户访问类型
//        param.put("ip_address", "127.0.0.1"); //用户ip
        int gtResult = 0;
        if (gt_server_status_code == 1) {
            //状态正常
            gtResult = gtSdk.enhencedValidateRequest(challenge, validate, seccode, param);
            System.out.println(gtResult);
        } else {
            // gt-server非正常情况下，进行failback模式验证
            System.out.println("failback:use your own server captcha validate");
            gtResult = gtSdk.failbackValidateRequest(challenge, validate, seccode);
            System.out.println(gtResult);
        }
        JSONObject jsonObject = new JSONObject();
        if (gtResult == 1) {
            // 验证成功
            System.out.println("验证成功");
            String resStr = userService.add(user);
            System.out.println(resStr);
            if(resStr.equals("1")){
                jsonObject.put("result","success");
            }else{
                jsonObject.put("result","error");
                jsonObject.put("message",resStr);
            }
            return jsonObject.toString();
            }
        else {
            // 验证失败
            jsonObject.put("result","error");
            jsonObject.put("message","验证失败");
            return jsonObject.toString();
        }

    }

    @CrossOrigin
    @ResponseBody
    @GetMapping(value = "/user/selectRand")
    public User selectRand(){
        User user = userService.selectRand();
        return user;
    }
}
