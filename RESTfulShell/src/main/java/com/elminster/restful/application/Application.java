package com.elminster.restful.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
// same as @Configuration @EnableAutoConfiguration @ComponentScan
@ComponentScan(basePackages="com.elminster.restful")
public class Application {

  public static void main(String[] args) {
    SpringApplication app = new SpringApplication(Application.class);
    app.setShowBanner(false);
    app.run(args);
  }
}
