package ynu.edu.controller;

import jakarta.annotation.Resource;
import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import ynu.edu.entity.CommonResult;
import ynu.edu.entity.User;
import ynu.edu.feign.UserFeign;

@RestController
@RequestMapping("/consumer")
public class ConsumerController {
    @Resource
    private RestTemplate restTemplate;

    @Resource
    private UserFeign userFeign;

    @GetMapping("/getUserById/{userId}")
    public CommonResult<User> getUserById(@PathVariable("userId") Integer userId) {
        CommonResult<User> result = restTemplate.getForObject(
                "http://localhost:12000/user/getUserById/" + userId.toString()
                , CommonResult.class);
        return result;
    }

    @PutMapping("/updateUserName")
    public CommonResult<User> updateUserName(@RequestBody User user) {
        MultiValueMap<String, Object> params = new LinkedMultiValueMap<>();
        params.add("user", user);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(params, headers);

        ResponseEntity<CommonResult<User>> response = restTemplate.exchange(
                "http://localhost:12000/user/updateUserName",
                HttpMethod.PUT,
                requestEntity,
                new ParameterizedTypeReference<CommonResult<User>>() {}
        );

        return response.getBody();
    }

    @PostMapping("/login")
    public CommonResult<User> login(@RequestParam("userName") String userName, @RequestParam("password") String password) {
        CommonResult<User> result = new CommonResult<>();
        MultiValueMap<String, Object> params = new LinkedMultiValueMap<>();
        params.add("userName", userName);
        params.add("password", password);

        HttpHeaders headers = new HttpHeaders();
        HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(params, headers);

        try {
            ResponseEntity<CommonResult<User>> response = restTemplate.exchange(
                    "http://localhost:12000/user/login",
                    HttpMethod.POST,
                    requestEntity,
                    new ParameterizedTypeReference<CommonResult<User>>() {}
            );

            result = response.getBody();
        } catch (RestClientException e) {
            e.printStackTrace();
        }

        return result;
    }

    @DeleteMapping("/logout/{userName}")
    public CommonResult<String> logout(@PathVariable("userName") String userName) {
        MultiValueMap<String, Object> params = new LinkedMultiValueMap<>();
        params.add("userName", userName);

        HttpHeaders headers = new HttpHeaders();
        HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(params, headers);
        ResponseEntity<CommonResult<String>> response = restTemplate.exchange(
                "http://localhost:12000/user/logout/" + userName,
                HttpMethod.DELETE,
                requestEntity,
                new ParameterizedTypeReference<CommonResult<String>>() {}
        );
        return response.getBody();
    }

    @GetMapping("/feign/getUserById/{userId}")
    public CommonResult<User> getUserByIdFeign(@PathVariable("userId") Integer userId) {
        CommonResult<User> result = userFeign.getUserById(userId);
        return result;
    }

    @GetMapping("/loadBalancer")
    public CommonResult<String> load() {
        CommonResult<String> result = userFeign.load();
        return result;
    }
}
