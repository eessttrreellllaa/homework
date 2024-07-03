package ynu.edu.controller;

import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/buildingConsumer")
public class BuildingController {
    @Resource
    private RestTemplate restTemplate;

    @GetMapping("/messageConsumer")
    public String show() {
        return restTemplate.getForObject("http://localhost:12001/building/message", String.class);
    }
}
