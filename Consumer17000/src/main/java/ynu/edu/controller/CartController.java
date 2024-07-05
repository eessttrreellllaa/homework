package ynu.edu.controller;

import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import ynu.edu.entity.CommonResult;
import ynu.edu.entity.User;

@RestController
@RequestMapping("/cart")

public class CartController {
    @Resource
    private RestTemplate restTemplate;

    @GetMapping("/getCartById/{userId}")
    public CommonResult<User> getCartById(@PathVariable("userId") Integer userId) {
        CommonResult<User> result = restTemplate.getForObject(
                "http://localhost:12000/user/getUserById/"+userId.toString()
                , CommonResult.class);
        return result;
    }

    @GetMapping("/select")
    public CommonResult<String> select() {
        CommonResult<String> result = new CommonResult<>();
        result.setMessage("success");
        result.setCode(200);
        result.setResult("17000");
        return result;
    }
}
