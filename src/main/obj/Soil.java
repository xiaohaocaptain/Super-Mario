package main.obj;

import main.util.BackGround;
import main.util.ImagesValue;
import java.awt.image.BufferedImage;

public class Soil {
    //坐标
    private int x;
    private int y;
    //土壤方块在集合中的编号
    private int num;
    //用于显示图像
    private BufferedImage show=null;
    //获取当前的背景
    private BackGround nowBj=null;

    public Soil(int x, int y, int num, BackGround nowBj) {
        this.x = x;
        this.y = y;
        this.num = num;
        this.nowBj = nowBj;
        show= ImagesValue.soil.get(num);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getNum() {
        return num;
    }

    public BackGround getNowBj() {
        return nowBj;
    }

    public BufferedImage getShow() {
        return show;
    }
}
