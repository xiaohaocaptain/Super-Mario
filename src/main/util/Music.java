package main.util;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;
import java.io.BufferedInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Music {

    public Music() {
    }
    public Music(String name) throws IOException, JavaLayerException {
        //公共地址
        String path=System.getProperty("user.dir")+"/src/music/";

        //创建音乐链接
        BufferedInputStream main_theme=new BufferedInputStream(Files.newInputStream(Paths.get(path + "main_theme.wav")));
        BufferedInputStream flagpole=new BufferedInputStream(Files.newInputStream(Paths.get(path + "flagpole.wav")));


        if (name.equals("main_theme")){
            Player playerMain_theme=new Player(main_theme);
            playerMain_theme.play();
        }
        else if (name.equals("flagpole")){
            Player playerFlagpole=new Player(flagpole);
            playerFlagpole.play();
        }

    }
}
