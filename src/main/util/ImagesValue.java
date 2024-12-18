package main.util;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ImagesValue {
    //文件路径前缀
    public static String path=System.getProperty("user.dir")+"//graphics/";

    //背景
    public static BufferedImage bj1=null;
    public static BufferedImage bj2=null;
    //旗帜
    public static BufferedImage flag=null;
    //旗杆
    public static BufferedImage gan=null;
    //城堡
    public static BufferedImage tower=null;
    //马里奥向左跳跃
    public static BufferedImage jump_l=null;
    //马里奥向右跳跃
    public static BufferedImage jump_r=null;
    //马里奥左站立
    public static BufferedImage stand_l=null;
    //马里奥右站立
    public static BufferedImage stand_r=null;
    //马里奥向左跑
    public static List<BufferedImage> run_r=new ArrayList<>();
    //马里奥向右跑
    public static List<BufferedImage> run_l=new ArrayList<>();
    //食人花
    public static List<BufferedImage> flower=new ArrayList<>();
    //蘑菇怪
    public static List<BufferedImage> fungus=new ArrayList<>();
    //地形方块
    public static List<BufferedImage> obstacles=new ArrayList<>();
    //土壤
    public static List<BufferedImage> soil=new ArrayList<>();
    //水管
    public static List<BufferedImage> pipe=new ArrayList<>();

    //初始化方法
    public static void initialize(){

        try {
            //初始化背景
            bj1= ImageIO.read(new File(path+"bg1.png"));
            bj2= ImageIO.read(new File(path+"bg2.png"));
            //初始化旗帜
            flag=ImageIO.read(new File(path+"flag.png"));
            //初始化旗杆
            gan=ImageIO.read(new File(path+"gan.png"));
            //初始化城堡
            tower=ImageIO.read(new File(path+"tower.png"));

            //初始化不可摧毁方块
            obstacles.add(ImageIO.read(new File(path+"brick1.png")));
            //初始化可摧毁方块
            obstacles.add(ImageIO.read(new File(path+"brick2.png")));
            //初始化上层土壤
            soil.add(ImageIO.read(new File(path+"soil_up.png")));
            //初始化下层土壤
            soil.add(ImageIO.read(new File(path+"soil_base.png")));

            //初始化马里奥站立
            stand_l=ImageIO.read(new File(path+"stand_L.png"));
            stand_r=ImageIO.read(new File(path+"stand_R.png"));
            //初始化马里奥跳跃
            jump_l=ImageIO.read(new File(path+"jump_L.png"));
            jump_r=ImageIO.read(new File(path+"jump_R.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        //初始化马里奥向左跑
        for (int i=1;i<=2;i++){

            try {
                run_l.add(ImageIO.read(new File(path+"run"+i+"_L.png")));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }
        //初始化马里奥向右跑
        for (int i=1;i<=2;i++){

            try {
                run_r.add(ImageIO.read(new File(path+"run"+i+"_R.png")));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }
        //初始化食人花
        for (int i=1;i<=2;i++){

            try {
                flower.add(ImageIO.read(new File(path+"flower"+i+".png")));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }
        //初始化蘑菇怪
        for (int i=1;i<=3;i++){

            try {
                fungus.add(ImageIO.read(new File(path+"fungus"+i+".png")));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }
        //初始化水管
        for (int i=1;i<=4;i++){

            try {
                pipe.add(ImageIO.read(new File(path+"pipe"+i+".png")));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }
    }
}
