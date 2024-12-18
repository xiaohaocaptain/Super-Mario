package main;

import javazoom.jl.decoder.JavaLayerException;
import main.obj.*;
import main.util.BackGround;
import main.util.ImagesValue;
import main.util.Music;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
/**
 @author 张皓瑜
 */

public class MyFrame extends JFrame implements KeyListener,Runnable{
    //在此类中存放所有的背景
    private List<BackGround> allBj=new ArrayList<>();
    //在此类中存放当前的背景
    private BackGround now=new BackGround();
    //用于双缓存
    private Image offScreenImage=null;
    //马里奥对象
    private Mario mario=new Mario();
    //实现马里奥移动
    private Thread thread=new Thread(this);

    public MyFrame(){
        //设置窗口大小，宽800，高600
        this.setSize(800,600);
        //设置窗口在屏幕中居中显示
        this.setLocationRelativeTo(null);
        //设置窗口可见
        this.setVisible(true);
        //设置窗口被关闭时结束程序
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //设置窗口不可被修改大小
        this.setResizable(false);
        //设置窗口标题
        this.setTitle("Super Mario" );
        //为窗口设置键盘监听
        this.addKeyListener(this);

        //初始化图片
        ImagesValue.initialize();
        //初始化马里奥
        mario=new Mario(10,355);

        //创建全部背景
        for(int i=1;i<=3;i++){
            allBj.add(new BackGround(i,i==3? true:false));
        }
        //将第一个背景作为当前场景
        now=allBj.get(0);
        mario.setAllObstacle(now);

        //绘制图像
        repaint();
        thread.start();

        try {
            new Music("main_theme");
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (JavaLayerException e) {
            throw new RuntimeException(e);
        }

    }
    @Override
    public void paint(Graphics g) {
        if(offScreenImage==null){
            offScreenImage=createImage(800,600);
        }
        //creatimage相当于创建画布，getimage相当于把画笔给对象，再用fillrect填充矩形
        Graphics graphics=offScreenImage.getGraphics();
        graphics.fillRect(0,0,800,600);

        //绘制背景
        graphics.drawImage(now.getNowBj(),0,0,this);
        //绘制敌人
        for (Enemy e:now.getEnemiesList()){
            graphics.drawImage(e.getShow(),e.getX(),e.getY(),this);
        }
        //绘制地形土壤
        for (Soil s: now.getSoilList()){
            graphics.drawImage(s.getShow(),s.getX(),s.getY(),this);
        }
        //绘制障碍物方块
        for (Obstacles o: now.getObstaclesLists()){
            graphics.drawImage(o.getShow(), o.getX(),o.getY(),this);
        }
        //绘制水管
        for (Pipe p: now.getPipeList()){
            graphics.drawImage(p.getShow(),p.getX(),p.getY(),this);
        }
        //绘制城堡
        graphics.drawImage(now.getTower(),620,270,this);
        //绘制旗子,仅在第三个场景中绘制
        if (now.getNumber()==3){
            graphics.drawImage(now.getFlag().getShow(),now.getFlag().getX(),now.getFlag().getY(),this);
        }
        //绘制旗杆
        graphics.drawImage(now.getGan(),500,220,this);
        //绘制马里奥
        graphics.drawImage(mario.getShow(),mario.getX(),mario.getY(),this);

        //将绘制的背景添加至窗口
        g.drawImage(offScreenImage,0,0,this);
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    //按下键盘时调用
    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode()==KeyEvent.VK_RIGHT){//向右移动
            mario.rightMove();
        }
        if (e.getKeyCode()==KeyEvent.VK_LEFT){//向左移动
            mario.leftMove();
        }
        if (e.getKeyCode()==KeyEvent.VK_SPACE){//跳跃
            mario.jump();
        }
    }

    //松开键盘时调用
    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode()==KeyEvent.VK_RIGHT){//向右停止
            mario.rightStop();
        }
        if (e.getKeyCode()==KeyEvent.VK_LEFT){//向左停止
            mario.leftStop();
        }
    }

    @Override
    public void run() {
        while (true){
            repaint();
            try {
                Thread.sleep(50);
                //判断马里奥是否到达下一关
                if (mario.getX()>=775){
                    now=allBj.get(now.getNumber());//集合比地图编号少1
                    mario.setAllObstacle(now);
                    mario.setX(10);
                    mario.setY(355);
                }

                //判断马里奥是否死亡
                if (mario.isIfDie()){
                    JOptionPane.showMessageDialog(this,"马里奥死亡！");
                    System.exit(0);
                }

                //判断游戏是否结束
                if (mario.isIfOnTower()){
                    JOptionPane.showMessageDialog(this,"恭喜你，顺利通关！");
                    System.exit(0);
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static void main(String[] args) {
        MyFrame myFrame=new MyFrame();
    }
}
