package me.nettee;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import static me.nettee.common.constant.Constants.ROOT_PACKAGE;

@SpringBootApplication(scanBasePackages = ROOT_PACKAGE)
public class Main {
    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }
}