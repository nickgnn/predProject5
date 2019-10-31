package com.myPackage.classes;

import org.springframework.stereotype.Component;

//@Component
public class CountryMusic implements Music {
    @Override
    public String getSong() {
        return "Born to Run";
    }
}
