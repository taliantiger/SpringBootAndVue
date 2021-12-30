package myproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

//import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
//import org.springframework.context.annotation.ComponentScan;
//
//@EnableAutoConfiguration
//@ComponentScan

@SpringBootApplication
public class App {
    public static void main(String[] args) {
//        SpringApplicationBuilder = new SpringApplicationBuilder(Chapter012Application.class);


        SpringApplication.run(App.class, args);
    }
}
