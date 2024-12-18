package main.obj;

import main.util.BackGround;
import main.util.ImagesValue;
import java.awt.image.BufferedImage;

public class Mario implements Runnable{
    //坐标
    private int x;
    private int y;
    //当前状态
    private String state;
    //此时应显示的图片
    private BufferedImage show=null;
    //获取障碍方块信息
    private BackGround allObstacles=new BackGround();
    //用来实现马里奥的动作
    private Thread thread=null;
    //马里奥的x轴移动速度
    private int xSpeed;
    //马里奥的y轴移动速度
    private int ySpeed;
    //马里奥动作图片索引
    private int index;
    //马里奥的方向
    private boolean faceTo=true;
    //马里奥上升时间
    private int upTime=0;
    //判断马里奥是否达到城堡口
    private boolean ifOnTower;
    //记录马里奥是否死亡
    private boolean ifDie=false;

    public Mario() {
    }

    public Mario(int x, int y) {
        this.x = x;
        this.y = y;
        show= ImagesValue.stand_r;
        this.state="stand_right";
        thread=new Thread(this);
        thread.start();
    }

    //马里奥向左移动
    public void leftMove(){
        //改变速度
        xSpeed=-5;
        //判断马里奥是否触碰到旗子
        if (allObstacles.isIfArriveGan()){
            xSpeed=0;
        }
        //判断马里奥是否处于跳跃状态
        if (state.contains("jump")){
            state="jump_left";
        }
        else {
            state="move_left";
        }
    }
    //马里奥向右移动
    public void rightMove(){
        //改变速度
        xSpeed=5;
        if (allObstacles.isIfArriveGan()){
            xSpeed=0;
        }
        //判断马里奥是否处于跳跃状态
        if (state.contains("jump")){
            state="jump_right";
        }
        else {
            state="move_right";
        }
    }
    //马里奥向左停止
    public void leftStop(){
        //改变速度
        xSpeed=0;
        //判断马里奥是否处于跳跃状态
        if (state.contains("jump")){
            state="jump_left";
        }
        else {
            state="stop_left";
        }
    }
    //马里奥向右停止
    public void rightStop(){
        //改变速度
        xSpeed=0;
        //判断马里奥是否处于跳跃状态
        if (state.contains("jump")){
            state="jump_right";
        }
        else {
            state="stop_right";
        }
    }
    //马里奥跳跃
    public void jump(){
        if (!state.contains("jump")){
            if (state.contains("left")){
                state="jump_left";
            }
            else {
                state="jump_right";
            }
            ySpeed=-10;
            upTime=7;
        }
        if (allObstacles.isIfArriveGan()){
            ySpeed=0;
        }
    }


    //马里奥下落
    public void fall(){
        if (state.contains("left")) {
            state = "jump_left";
        } else {
            state = "jump_right";
        }
        ySpeed=10;
    }

    //马里奥死亡修改判断方法
    public void die(){
        ifDie=true;
    }

    @Override
    public void run() {
        while (true){
            //判断马里奥是否处于障碍物上
            boolean ifOnObstacle=false;
            //判断马里奥是否可以右走
            boolean ifCanRightMove=true;
            //判断马里奥是否可以左走
            boolean ifCanLeftMove=true;
            //判断马里奥是否到达旗子位置
            if (allObstacles.isEndIf()&&this.x>=500){
                this.allObstacles.setIfArriveGan(true);



                //判断旗子是否下落
                if (this.allObstacles.isIfOnSoil()){
                    state="move_right";
                    if (x<690){
                        x+=5;
                    }
                    else {
                        ifOnTower=true;
                    }
                }
                else{
                    if (y < 395) {
                        xSpeed=0;
                        this.y+=5;
                        state="jump_right";
                    }
                    if (y > 395) {
                        this.y=395;
                        state="stop_right";
                    }
                }
            }
            else {
                //遍历当前场景中的所有障碍物
                //遍历obstacles
                for (int i=0;i<allObstacles.getObstaclesLists().size();i++){
                    Obstacles o=allObstacles.getObstaclesLists().get(i);
                    //判断马里奥是否位于障碍物上
                    if (o.getY()==this.y+25 && (o.getX()>this.x-30 && o.getX()<this.x+25)){
                        ifOnObstacle=true;
                    }
                    //判断马里奥是否向右走
                    if (o.getX()==this.x+25&&o.getY()>this.y-30&&o.getY()<this.y+25){
                        ifCanRightMove=false;
                    }
                    //判断马里奥是否向左走
                    if (o.getX()==this.x-30&&o.getY()>this.y-30&&o.getY()<this.y+25){
                        ifCanLeftMove=false;
                    }
                    //判断马里奥是否顶到砖块
                    if (o.getY()>=this.y-30&&o.getY()<=this.y-20&&o.getX()>this.x-30&&o.getX()<this.x+25){
                        if (o.getNum()==1){
                            allObstacles.getObstaclesLists().remove(o);
                        }
                        upTime=0;
                    }

                }
                //遍历pipe
                for (int i=0;i<allObstacles.getPipeList().size();i++){
                    Pipe p=allObstacles.getPipeList().get(i);
                    //判断马里奥是否位于障碍物上
                    if (p.getY()==this.y+25 && (p.getX()>this.x-30 && p.getX()<this.x+25)){
                        ifOnObstacle=true;
                    }
                    //判断马里奥是否向右走
                    if (p.getX()==this.x+25&&p.getY()>this.y-30&&p.getY()<this.y+25){
                        ifCanRightMove=false;
                    }
                    //判断马里奥是否向左走
                    if (p.getX()==this.x-30&&p.getY()>this.y-30&&p.getY()<this.y+25){
                        ifCanLeftMove=false;
                    }
                }
                //遍历soil
                for (int i=0;i<allObstacles.getSoilList().size();i++){
                    Soil s=allObstacles.getSoilList().get(i);
                    //判断马里奥是否位于障碍物上
                    if (s.getY()==this.y+25 && (s.getX()>this.x-30 && s.getX()<this.x+25)){
                        ifOnObstacle=true;
                    }
                }

                //判断马里奥是否碰到敌人死亡或者踩死蘑菇敌人
                for (int i=0;i<allObstacles.getEnemiesList().size();i++){
                    Enemy e=allObstacles.getEnemiesList().get(i);
                    if (e.getY()==this.y+20&&e.getX()-25<=this.x&&e.getX()+35>=this.x){
                        //踩到蘑菇时弹跳
                        if (e.getType()==1){
                            e.die();
                            upTime=3;
                            ySpeed=-10;
                        }
                        else if (e.getType()==2){
                            //马里奥死亡
                            die();
                        }
                    }
                    if (e.getX()+35>this.x&&e.getX()-25<this.x&&e.getY()+35>this.y&&e.getY()-20<this.y){
                        //马里奥死亡
                        die();
                    }
                }

                //进行马里奥跳跃操作
                if (ifOnObstacle && upTime==0){
                    if (state.contains("left")){
                        if (xSpeed!=0){
                            state="move_left";
                        }
                        else {
                            state="stop_left";
                        }
                    }
                    else {
                        if (xSpeed!=0){
                            state="move_right";
                        }
                        else {
                            state="stop_right";
                        }
                    }
                }
                else {
                    if (upTime!=0){
                        upTime--;
                    }
                    else {
                        fall();
                    }
                    y+=ySpeed;
                }
            }

            if (ifCanLeftMove&&xSpeed<0 || ifCanRightMove&&xSpeed>0){
                x+=xSpeed;
                //判断马里奥是否到达最左边，若达到则要重置x坐标为0；
                if (x<0){
                    x=0;
                }
            }
            //判断马里奥的是否处于移动状态
            if (state.contains("move")){
                index = index==0 ? 1:0;
            }
            //判断是否向左移动
            if (state.equals("move_left")){
                show=ImagesValue.run_l.get(index);
            }
            //判断是否向右移动
            if (state.equals("move_right")){
                show=ImagesValue.run_r.get(index);
            }
            //判断是否向左停止
            if (state.equals("stop_left")){
                show=ImagesValue.stand_l;
            }
            //判断是否向右停止
            if (state.equals("stop_right")){
                show=ImagesValue.stand_r;
            }
            //判断是否向左跳跃
            if (state.equals("jump_left")){
                show=ImagesValue.jump_l;
            }
            //判断是否向右跳跃
            if (state.equals("jump_right")){
                show=ImagesValue.jump_r;
            }

            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public int getXSpeed() {
        return xSpeed;
    }

    public void setXSpeed(int xSpeed) {
        this.xSpeed = xSpeed;
    }

    public int getYSpeed() {
        return ySpeed;
    }

    public void setYSpeed(int ySpeed) {
        this.ySpeed = ySpeed;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public String getState() {
        return state;
    }

    public BufferedImage getShow() {
        return show;
    }

    public BackGround getAllObstacles(BackGround now) {
        return allObstacles;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public boolean isFaceTo() {
        return faceTo;
    }

    public void setFaceTo(boolean faceTo) {
        this.faceTo = faceTo;
    }

    public void setState(String state) {
        this.state = state;
    }

    public void setShow(BufferedImage show) {
        this.show = show;
    }

    public void setAllObstacle(BackGround obstacle) {
        this.allObstacles = obstacle;
    }

    public void setThread(Thread thread) {
        this.thread = thread;
    }

    public boolean isIfOnTower() {
        return ifOnTower;
    }

    public void setIfOnTower(boolean ifOnTower) {
        this.ifOnTower = ifOnTower;
    }

    public boolean isIfDie() {
        return ifDie;
    }
}
