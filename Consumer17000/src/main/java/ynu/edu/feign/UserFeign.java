package ynu.edu.feign;

import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;
import ynu.edu.config.LoadBalancerRandom;
import ynu.edu.entity.CommonResult;
import ynu.edu.entity.User;

@FeignClient(name = "provider")
@LoadBalancerClient(name = "provider", configuration = LoadBalancerRandom.class)
public interface UserFeign {
    @GetMapping("/user/getUserById/{userId}")
    CommonResult<User> getUserById(@PathVariable("userId") Integer userId);

    @PutMapping("/user/updateUserName")
    CommonResult<User> updateUserName(@RequestBody User user);

    @PostMapping("/user/login")
    CommonResult<User> login(@RequestParam("userName") String userName, @RequestParam("password") String password);

    @DeleteMapping("/user/logout/{userName}")
    CommonResult<String> logout(@PathVariable("userName") String userName);

    @GetMapping("/user/load")
    CommonResult<String> load();
}
