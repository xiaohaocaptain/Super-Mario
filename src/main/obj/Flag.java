package main.obj;

import main.util.BackGround;
import main.util.ImagesValue;
import java.awt.image.BufferedImage;


public class Flag implements Runnable{
    //坐标
    private int x;
    private int y;
    //用于显示图像
    private BufferedImage show=null;
    //获取当前的背景
    private BackGround nowBj=null;
    //创建一个旗子线程
    private Thread thread=new Thread(this);

    public Flag(int x, int y,BackGround nowBj) {
        this.x = x;
        this.y = y;
        this.nowBj = nowBj;
        show= ImagesValue.flag;
        thread.start();
    }

    @Override
    public void run() {
        while (true){
            if (this.nowBj.isIfArriveGan()){
                if (this.y<374){
                    this.y+=5;
                }
                else {
                    this.nowBj.setIfOnSoil(true);
                }
            }
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public BackGround getNowBj() {
        return nowBj;
    }

    public BufferedImage getShow() {
        return show;
    }
}
