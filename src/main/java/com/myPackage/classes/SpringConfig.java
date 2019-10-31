package com.myPackage.classes;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ComponentScan("com.myPackage.classes")
@PropertySource("classpath:musicPlayer.properties")
public class SpringConfig {

}
