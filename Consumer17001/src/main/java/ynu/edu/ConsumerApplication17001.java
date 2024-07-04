package ynu.edu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableFeignClients(basePackages = "ynu.edu.feign")
public class ConsumerApplication17001 {
    @Bean
    public RestTemplate getRestTemplate() {return new RestTemplate();}
    public static void main(String[] args) {
        SpringApplication.run(ConsumerApplication17001.class, args);
    }
}
