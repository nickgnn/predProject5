package com.myPackage.classes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Random;

//@Component
public class MusicPlayer {
    @Value("${musicPlayer.name}")
    private String name;

    @Value("${musicPlayer.volume}")
    private int volume;


    private List<Music> musicList;

//    @Autowired
    public MusicPlayer(List<Music> musicList) {
        this.musicList = musicList;
    }

    public String playMusic() {
        Random random = new Random();

        return "Playing: " + musicList.get(random.nextInt(musicList.size())).getSong() + " with volume " + this.volume;
    }

    public String getName() {
        return name;
    }

    public int getVolume() {
        return volume;
    }
}
