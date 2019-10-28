package com.myPackage.classes;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestSpring {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("appContext.xml");

        Music music1 = context.getBean("rockMusic", Music.class);
        MusicPlayer musicPlayer = new MusicPlayer(music1);
        musicPlayer.playMusic();

        Music music2 = context.getBean("classicMusic", Music.class);
        MusicPlayer playerClassic = new MusicPlayer(music2);
        playerClassic.playMusic();

        context.close();
    }
}
