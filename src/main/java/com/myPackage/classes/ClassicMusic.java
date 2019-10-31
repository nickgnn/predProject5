package com.myPackage.classes;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

//@Component
public class ClassicMusic implements Music {
//    @PostConstruct
    public void doMyInit() {
        System.out.println("Doing my initialization");
    }

//    @PreDestroy
    public void doMyDestroy() {
        System.out.println("Doing my destruction");
    }

    @Override
    public String getSong() {
        return "Hungarian Rhapsody";
    }
}
