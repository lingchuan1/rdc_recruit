package com.rdc.recruit;

import com.rdc.recruit.bean.CheckPicture;
import com.rdc.recruit.util.CheckImgUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Random;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RecruitApplicationTests {

    @Test
    public void contextLoads() throws IOException {
        Random random = new Random();
        int oriPicture = random.nextInt(3)+1;
        File originalFile = new File(oriPicture + ".jpg");
        System.out.println(originalFile.getAbsoluteFile());
        CheckPicture checkPicture = CheckImgUtil.pictureCut(originalFile,"jpg");
        FileOutputStream fout = new FileOutputStream("C:/Users/lingchuan/Desktop/bigPicture.png");
        //将字节写入文件
        try {
            fout.write(checkPicture.getBigPicture());
        } catch (IOException e) {
            e.printStackTrace();
        }
        fout.close();

        FileOutputStream fout1 = new FileOutputStream("C:/Users/lingchuan/Desktop/smallPicture.png");
        //将字节写入文件
        try {
            fout1.write(checkPicture.getSmallPicture());
        } catch (IOException e) {
            e.printStackTrace();
        }
        fout1.close();
    }

    @Test
    public void test(){
    }

}

