package ynu.edu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;


@SpringBootApplication
public class ConsumerApplication17000 {

    /**
     * 使用spring提供的RestTemplate发送http请求调用微服务
     * 在主启动类中，将RestTemplate实例放入Spring容器中
     */
    @Bean
    public RestTemplate getRestTemplate(){
        return new RestTemplate();
    }


    public static void main(String[] args) {
        SpringApplication.run(ConsumerApplication17000.class, args);
    }
}
