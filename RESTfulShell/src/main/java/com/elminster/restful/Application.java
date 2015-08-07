package com.elminster.restful;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
// same as @Configuration @EnableAutoConfiguration @ComponentScan
@EnableJpaRepositories()
@EntityScan()
public class Application {
  
  public static ConfigurableApplicationContext context;
  
  public static void main(String[] args) {
    SpringApplication application = new SpringApplication(Application.class);
    application.setShowBanner(false);
    context = application.run(args);
    context.registerShutdownHook();
  }
}
