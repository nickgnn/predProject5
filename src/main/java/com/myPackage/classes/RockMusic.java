package com.myPackage.classes;

import org.springframework.stereotype.Component;

//@Component
public class RockMusic implements Music {
    @Override
    public String getSong() {
        return "Bohemian Rhapsody";
    }
}
