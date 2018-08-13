package cn.wanlinus.beijing;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author wanli
 */
@SpringBootApplication
@EnableScheduling
public class BeijingApplication {

    public static void main(String[] args) {
        SpringApplication.run(BeijingApplication.class, args);
    }
}
