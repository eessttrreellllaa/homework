package ynu.edu.controller;

import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ynu.edu.entity.CommonResult;
import ynu.edu.entity.User;
import ynu.edu.feign.UserFeign;

@RestController
@RequestMapping("/cart")
public class CartController {
    @Resource
    private UserFeign userFeign;

    @GetMapping("/getCartById/{userId}")
    @CircuitBreaker(name = "circuitBreakerA", fallbackMethod = "fallbackGetCartById")
    public CommonResult<User> getCartById(@PathVariable("userId") Integer userId) {
        CommonResult<User> result = userFeign.getUserById(userId);
        return result;
    }
    public CommonResult<User> fallbackGetCartById(Integer userId, Throwable T) {
        CommonResult<User> result = new CommonResult<>();
        result.setCode(500);
        result.setResult(null);
        result.setMessage("A降级");
        System.out.println("A降级");
        return result;
    }

    @GetMapping("/getCartByName/{userName}")
    @CircuitBreaker(name = "circuitBreakerB", fallbackMethod = "fallbackGetCartByName")
    public CommonResult<User> getCartByName(@PathVariable("userName") String userName) {
        CommonResult<User> result = userFeign.getUserByName(userName);
        return result;
    }
    public CommonResult<User> fallbackGetCartByName(String userName, Throwable T) {
        CommonResult<User> result = new CommonResult<>();
        result.setCode(500);
        result.setResult(null);
        result.setMessage("B降级");
        System.out.println("B降级");
        return result;
    }

    @GetMapping("/getCartById1/{userId}")
    @Bulkhead(name = "bulkheadC", fallbackMethod = "fallbackGetCartById1")
    public CommonResult<User> getCartById1(@PathVariable("userId") Integer userId) {
        CommonResult<User> result = userFeign.getUserById(userId);
        return result;
    }
    public CommonResult<User> fallbackGetCartById1(Integer userId, Throwable T) {
        CommonResult<User> result = new CommonResult<>();
        result.setCode(500);
        result.setResult(null);
        result.setMessage("C隔离");
        System.out.println("C隔离");
        return result;
    }

    @GetMapping("/getCartById2/{userId}")
    @Bulkhead(name = "rateLimiterD", fallbackMethod = "fallbackGetCartById2")
    public CommonResult<User> getCartById2(@PathVariable("userId") Integer userId) {
        CommonResult<User> result = userFeign.getUserById(userId);
        return result;
    }
    public CommonResult<User> fallbackGetCartById2(Integer userId, Throwable T) {
        CommonResult<User> result = new CommonResult<>();
        result.setCode(500);
        result.setResult(null);
        result.setMessage("D限流");
        System.out.println("D限流");
        return result;
    }

    @GetMapping("/select")
    public CommonResult<String> select() {
        CommonResult<String> result = new CommonResult<>();
        result.setMessage("success");
        result.setCode(200);
        result.setResult("17001");
        return result;
    }
}
