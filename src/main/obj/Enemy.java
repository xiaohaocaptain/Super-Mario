package main.obj;

import main.util.BackGround;
import main.util.ImagesValue;
import java.awt.image.BufferedImage;


public class Enemy implements Runnable{
    //坐标
    private int x;
    private int y;
    //敌人类型
    private int type;
    //敌人的运动方向
    private boolean faceTo=true;
    //用于显示敌人图像
    private BufferedImage show;
    //背景对象
    private BackGround backGround;
    //食人花运动极限
    private int maxUp=0;
    private int maxDown=0;
    //创建线程
    private Thread thread=new Thread(this);
    //表示当前图片的状态
    private int imageType=0;

    public Enemy() {
    }

    //蘑菇
    public Enemy(int x, int y, int type, boolean faceTo, BackGround backGround) {
        this.x = x;
        this.y = y;
        this.type = type;
        this.faceTo = faceTo;
        this.backGround = backGround;
        show= ImagesValue.fungus.get(0);
        thread.start();
    }

    //食人花
    public Enemy(int x, int y, int type, boolean faceTo, BackGround backGround, int maxUp, int maxDown) {
        this.x = x;
        this.y = y;
        this.type = type;
        this.faceTo = faceTo;
        this.backGround = backGround;
        this.maxUp = maxUp;
        this.maxDown = maxDown;
        show=ImagesValue.flower.get(0);
        thread.start();
    }

    //敌人的死亡方法,即敌人先转换死亡动画再被移除
    public void die() {
        show=ImagesValue.fungus.get(2);
        this.backGround.getEnemiesList().remove(this);
    }

    @Override
    public void run() {
        while (true) {
            //判断是否是蘑菇敌人
            if (type==1) {
                if (faceTo){
                    this.x-=2;
                }
                else {
                    this.x+=2;
                }
                //动态刷新外观
                imageType=imageType==1?0:1;
                show=ImagesValue.fungus.get(imageType);
            }

            //
            boolean canRight=true;
            boolean canLeft=true;

            //读取水管信息
            for (int i=0;i<backGround.getPipeList().size();i++){
                Pipe p=backGround.getPipeList().get(i);
                //判断蘑菇敌人是否可以向左走
                if (p.getX()==this.x-45 &&p.getY()+65>this.y&&p.getY()-35<this.y){
                    canLeft=false;
                }
                //判断蘑菇敌人是否可以向右走
                if (p.getX()==this.x+36&&p.getY()+65>this.y&&p.getY()-35<this.y){
                    canRight=false;
                }
            }
            //读取障碍物信息判断
            for (int i=0;i<backGround.getObstaclesLists().size();i++){
                Obstacles o=backGround.getObstaclesLists().get(i);
                //判断蘑菇敌人是否可以向左走
                if (o.getX()==this.x-36&&o.getY()+65>this.y&&o.getY()-35<this.y){
                    canLeft=false;
                }
                //判断蘑菇敌人是否可以向右走
                if (o.getX()==this.x+36&&o.getY()+65>this.y&&o.getY()-35<this.y){
                    canRight=false;
                }
            }

            //判断方向
            if (faceTo&&!canLeft||this.x==0){
                faceTo=false;
            }
            else if (!faceTo&&!canRight||this.y==764){
                faceTo=true;
            }

            //判断是否是食人花敌人
            if (type==2) {
                if (faceTo){
                    this.y-=2;
                }
                else {
                    this.y+=2;
                }
                //判断食人花是否到达极限位置
                if (faceTo&&this.y==maxUp){
                    faceTo=false;
                }
                if (!faceTo&&this.y==maxDown){
                    faceTo=true;
                }
                //动态刷新外观
                imageType=imageType==1?0:1;
                show=ImagesValue.flower.get(imageType);
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

    public BufferedImage getShow() {
        return show;
    }

    public int getType() {
        return type;
    }
}
