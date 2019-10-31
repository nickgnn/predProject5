package com.myPackage.classes;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestSpring {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("appContext.xml");

        MusicPlayer player = context.getBean("musicPlayer", MusicPlayer.class);

        System.out.println(player.getName());
        System.out.println(player.getVolume());

        ClassicMusic classicMusic = context.getBean("classicMusic", ClassicMusic.class);

        context.close();
    }
}
