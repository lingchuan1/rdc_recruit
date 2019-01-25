package com.rdc.recruit.util;

import com.rdc.recruit.bean.CheckPicture;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.Random;

public class CheckImgUtil {
    private static int WIDTH = 100;  //抠图宽度
    private static int HEIGHT = 100;  //抠图高度
    private static int X;  //抠图坐标x
    private static int Y;  //抠图坐标y

    /**
     * 抠图
     * @param originalFile
     * @param fileType
     * @return
     */
    public static CheckPicture pictureCut(File originalFile, String fileType) throws IOException {
        CheckPicture checkPicture = new CheckPicture();
        if (StringUtil.isEmpty(fileType))
            throw new RuntimeException("file type is empty");

        BufferedImage oriImage = ImageIO.read(originalFile);
        int oriWidth = oriImage.getWidth();
        int oriHeight = oriImage.getHeight();
        generateCutoutCoordinates(oriWidth,oriHeight);
        checkPicture.setX(X);
        checkPicture.setY(Y);
        byte[] bigPicture = getBigPicture(oriImage,X,Y);
        byte[] smallPicture = getSmallPicture(oriImage);
        checkPicture.setBigPicture(bigPicture);
        checkPicture.setSmallPicture(smallPicture);
        return checkPicture;
    }

    /**
     * 生成图像矩阵
     * @param img
     * @return
     */
    private static int[][] getData(BufferedImage img) {
        int[][] data = new int[img.getWidth()][img.getHeight()];
        for (int i = 0; i < img.getWidth(); i++) {
            for (int j = 0; j < img.getHeight(); j++) {
                data[i][j] = img.getRGB(i, j);
            }
        }
        return data;
    }

    /**
     * 随机生成抠图坐标
     */
    private static void generateCutoutCoordinates(int oriWidth, int oriHeight) {
        Random random = new Random();
        int widthDifference = oriWidth - WIDTH;
        int heightDifference = oriHeight - HEIGHT;
        if (widthDifference < 0 || heightDifference < 0)
            throw new RuntimeException("Original picture is too small");
        X = random.nextInt(widthDifference - 10) + 5;
        Y = random.nextInt(heightDifference - 10) + 5;
    }

    /**
     * 抠图后原图生成
     * @param oriImage
     * @param x
     * @param y
     * @return
     */
    private static byte[] getBigPicture(BufferedImage oriImage, int x, int y) throws IOException {
        // 源文件备份图像矩阵 支持alpha通道的rgb图像
        BufferedImage ori_copy_image = new BufferedImage(oriImage.getWidth(), oriImage.getHeight(), BufferedImage.TYPE_4BYTE_ABGR);
        // 源文件图像矩阵
        int[][] oriImageData = getData(oriImage);

        System.out.println(oriImageData.length);
        System.out.println(oriImageData[0].length);
        //copy 源图做不透明处理
        for (int i = 0; i < oriImageData.length; i++) {
            for (int j = 0; j < oriImageData[0].length; j++) {
                int rgb = oriImage.getRGB(i, j);
                int r = (0xff & rgb);
                int g = (0xff & (rgb >> 8));
                int b = (0xff & (rgb >> 16));
                //无透明处理
                rgb = r + (g << 8) + (b << 16) + (255 << 24);
                ori_copy_image.setRGB(i, j, rgb);
            }
        }

        for (int i = 0; i < WIDTH; i++) {
            for (int j = 0; j < HEIGHT; j++) {
                //透明处理
                    int rgb_ori = ori_copy_image.getRGB(x + i, y + j);
                    int r = (0xff & rgb_ori);
                    int g = (0xff & (rgb_ori >> 8));
                    int b = (0xff & (rgb_ori >> 16));
                    rgb_ori = r + (g << 8) + (b << 16) + (150 << 24);
                    ori_copy_image.setRGB(x + i, y + j, rgb_ori);
            }
        }
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        ImageIO.write(ori_copy_image, "png", os);
        byte b[] = os.toByteArray();
        return b;
    }

    /**
     * 获取目标区域
     * @param oriImage
     * @return
     * @throws IOException
     */
    public static byte[] getSmallPicture(BufferedImage oriImage) throws IOException {
        BufferedImage smallImage = oriImage.getSubimage(X,Y,WIDTH,HEIGHT);
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        ImageIO.write(smallImage, "png", os);
        byte b[] = os.toByteArray();
        return b;
    }
}
