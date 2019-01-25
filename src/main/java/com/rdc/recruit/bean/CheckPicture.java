package com.rdc.recruit.bean;

public class CheckPicture {

    private int x;  //抠图坐标x
    private int y;  //抠图坐标y
    private byte[] bigPicture;  //抠图后原图
    private byte[] smallPicture;  //目标区域

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public byte[] getBigPicture() {
        return bigPicture;
    }

    public void setBigPicture(byte[] bigPicture) {
        this.bigPicture = bigPicture;
    }

    public byte[] getSmallPicture() {
        return smallPicture;
    }

    public void setSmallPicture(byte[] smallPicture) {
        this.smallPicture = smallPicture;
    }
}
