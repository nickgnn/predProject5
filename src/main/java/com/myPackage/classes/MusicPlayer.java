package com.myPackage.classes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MusicPlayer {
    private ClassicMusic classicMusic;

    @Autowired
    public MusicPlayer(ClassicMusic classicMusic) {
        this.classicMusic = classicMusic;
    }

    public void playMusic() {
        System.out.println("Playing: " + classicMusic.getSong());
    }
}
