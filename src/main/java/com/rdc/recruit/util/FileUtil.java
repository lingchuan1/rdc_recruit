package com.rdc.recruit.util;

import java.io.*;

public class FileUtil {
    public static void inputStreamToFile(InputStream ins,File file) throws IOException {
        OutputStream os = new FileOutputStream(file);
        int bytesRead ;
        while(( bytesRead = ins.read())!=-1){
            os.write(bytesRead);
        }
        ins.close();
        os.close();
    }
}
