package com.ditros.mcd;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;

@SpringBootApplication
@Slf4j
public class McdApplication {
    public static void main(String[] args) {
        System.out.println("Ditros Accident Data collect service starting...");
            SpringApplication.run(McdApplication.class, args);
        System.out.println("Ditros Accident Data collect service started");
    }

}
