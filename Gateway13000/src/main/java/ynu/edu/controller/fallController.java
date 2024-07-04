package ynu.edu.controller;

import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/fallback")
public class fallController {
    @GetMapping("/fall")
    public Mono<String> fall(ServerHttpRequest request, ServerHttpResponse response) {
        return Mono.just("熔断");
    }
}
