package com.rdc.recruit;

import com.rdc.recruit.bean.CheckPicture;
import com.rdc.recruit.controller.UserController;
import com.rdc.recruit.util.CheckImgUtil;
import com.rdc.recruit.util.FileUtil;
import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionContext;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Enumeration;
import java.util.Random;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RecruitApplicationTests {

    @Test
    public void contextLoads() throws IOException {

    }

    @Test
    public void test() throws IOException {

    }
}

