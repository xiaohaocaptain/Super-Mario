package main.util;


import main.obj.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class BackGround {
    //根据条件判断后要输出的背景
    private BufferedImage nowBj=null;
    //当前背景的编号
    private int number;
    //判断是否要输出第二个背景
    private boolean endIf;

    //建立一个集合存放一张地图里的obstacles
    private List<Obstacles> obstaclesLists=new ArrayList<>();
    //建立一个集合存放一张地图里的pipe
    private List<Pipe> pipeList=new ArrayList<>();
    //建立一个集合存放一张地图里的soil
    private List<Soil> soilList=new ArrayList<>();
    //建立一个集合存放敌人
    private List<Enemy> enemiesList=new ArrayList<>();
    //存放旗杆
    private BufferedImage gan=null;
    //存放旗子
    private Flag flag=null;
    //存放城堡
    private BufferedImage tower=null;
    //判断马里奥是否达到旗杆
    private boolean ifArriveGan=false;
    //判断旗子是否落地
    private boolean ifOnSoil=false;


    public BackGround() {
    }
    public BackGround(int number, boolean endIf){
        this.number=number;
        this.endIf=endIf;
        if(endIf){
            nowBj=ImagesValue.bj2;
        }
        else{
            nowBj=ImagesValue.bj1;
        }


        //判断是否为第一个场景
        if (number==1){
            //绘制地面，上层土壤num为0，下层土壤num为1
            for (int i=0;i<=27;i++){
                soilList.add(new Soil(i*30,420,0,this));
            }
            for (int i=0;i<=120;i+=30){
                for (int j=0;j<27;j++){
                    soilList.add(new Soil(j*30,570-i,1,this));
                }
            }
            //绘制障碍物方块，不可摧毁方块为0，可摧毁方块为1
            for(int i=120;i<=180;i+=30){
                obstaclesLists.add(new Obstacles(i,300,0,this));
            }
            for (int i=300;i<=570;i+=30){
                if(i==360||i==390||i==480||i==510||i==540){
                    obstaclesLists.add(new Obstacles(i,300,0,this));
                }
                else{
                    obstaclesLists.add(new Obstacles(i,300,1,this));
                }
            }
            for (int i=420;i<=450;i+=30){
                obstaclesLists.add(new Obstacles(i,240,0,this));
            }
            //绘制水管
            for (int i=360;i<=600;i+=25){
                if(i==360){
                    pipeList.add(new Pipe(620,i,0,this));
                    pipeList.add(new Pipe(645,i,1,this));
                }
                else{
                    pipeList.add(new Pipe(620,i,2,this));
                    pipeList.add(new Pipe(645,i,3,this));
                }
            }

            //绘制第一关蘑菇敌人
            enemiesList.add(new Enemy(580,385,1,true,this));
            //绘制第一关食人花敌人
            enemiesList.add(new Enemy(632,420,2,true,this,328,428));
        }

        //判断是否为第二个场景
        if (number==2){
            //绘制地面，上层土壤num为0，下层土壤num为1
            for (int i=0;i<=27;i++){
                soilList.add(new Soil(i*30,420,0,this));
            }
            for (int i=0;i<=120;i+=30){
                for (int j=0;j<27;j++){
                    soilList.add(new Soil(j*30,570-i,1,this));
                }
            }
            //绘制障碍物方块，不可摧毁方块为0，可摧毁方块为1
            obstaclesLists.add(new Obstacles(300,330,1,this));

            for (int i=270;i<=330;i+=30){
                if(i==270||i==330){
                    obstaclesLists.add(new Obstacles(i,360,1,this));
                }
                else {
                    obstaclesLists.add(new Obstacles(i,360,0,this));
                }
            }

            for (int i=240;i<=360;i+=30){
                if(i==240||i==360){
                    obstaclesLists.add(new Obstacles(i,390,1,this));
                }
                else {
                    obstaclesLists.add(new Obstacles(i,390,0,this));
                }
            }

            obstaclesLists.add(new Obstacles(150,300,0,this));
            obstaclesLists.add(new Obstacles(240,300,1,this));

            for (int i=360;i<=540;i+=60){
                obstaclesLists.add(new Obstacles(i,270,0,this));
            }
            //绘制第一根水管
            for (int i=360;i<=600;i+=25){
                if(i==360){
                    pipeList.add(new Pipe(60,i,0,this));
                    pipeList.add(new Pipe(85,i,1,this));
                }
                else{
                    pipeList.add(new Pipe(60,i,2,this));
                    pipeList.add(new Pipe(85,i,3,this));
                }
            }
            //绘制第二根水管
            for (int i=330;i<=600;i+=25){
                if(i==330){
                    pipeList.add(new Pipe(620,i,0,this));
                    pipeList.add(new Pipe(645,i,1,this));
                }
                else{
                    pipeList.add(new Pipe(620,i,2,this));
                    pipeList.add(new Pipe(645,i,3,this));
                }
            }

            //绘制第二关蘑菇敌人1
            enemiesList.add(new Enemy(200,385,1,true,this));
            //绘制第二关蘑菇敌人2
            enemiesList.add(new Enemy(500,385,1,true,this));
            //绘制第二关食人花敌人1
            enemiesList.add(new Enemy(72,420,2,true,this,328,428));
            //绘制第二关食人花敌人2
            enemiesList.add(new Enemy(632,420,2,true,this,298,388));
        }

        //判断是否为第三个场景
        if (number==3){
            //绘制地面，上层土壤num为0，下层土壤num为1
            for (int i=0;i<=27;i++){
                soilList.add(new Soil(i*30,420,0,this));
            }
            for (int i=0;i<=120;i+=30){
                for (int j=0;j<27;j++){
                    soilList.add(new Soil(j*30,570-i,1,this));
                }
            }
            //绘制左侧梯形建筑
            int x1=60;
            for (int i=390;i>=360;i-=30){
                for (int j=x1;j<=90;j+=30){
                    obstaclesLists.add(new Obstacles(j,i,0,this));
                }
                x1+=30;
            }
            //绘制右侧梯形建筑
            int x2=290;
            for (int i=390;i>=270;i-=30){
                for (int j=x2;j<=410;j+=30){
                    obstaclesLists.add(new Obstacles(j,i,0,this));
                }
                x2+=30;
            }
            //绘制旗杆
            gan=ImagesValue.gan;
            //绘制城堡
            tower=ImagesValue.tower;
            //绘制旗子
            flag=new Flag(515,220,this);
            //绘制第三关蘑菇敌人
            enemiesList.add(new Enemy(150,385,1,true,this));
        }
    }
    public BufferedImage getNowBj() {
        return nowBj;
    }

    public int getNumber() {
        return number;
    }

    public boolean isEndIf() {
        return endIf;
    }

    public List<Obstacles> getObstaclesLists() {
        return obstaclesLists;
    }

    public List<Pipe> getPipeList() {
        return pipeList;
    }

    public List<Soil> getSoilList() {
        return soilList;
    }

    public BufferedImage getGan() {
        return gan;
    }

    public BufferedImage getTower() {
        return tower;
    }

    public Flag getFlag() {
        return flag;
    }

    public boolean isIfArriveGan() {
        return ifArriveGan;
    }

    public void setIfArriveGan(boolean ifArriveGan) {
        this.ifArriveGan = ifArriveGan;
    }

    public boolean isIfOnSoil() {
        return ifOnSoil;
    }

    public void setIfOnSoil(boolean ifOnSoil) {
        this.ifOnSoil = ifOnSoil;
    }

    public List<Enemy> getEnemiesList() {
        return enemiesList;
    }

    public void setEnemiesList(List<Enemy> enemiesList) {
        this.enemiesList = enemiesList;
    }
}