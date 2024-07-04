package ynu.edu.controller;

import org.springframework.web.bind.annotation.*;
import ynu.edu.entity.CommonResult;
import ynu.edu.entity.User;

@RestController
@RequestMapping("/user")
public class UserController {
    @GetMapping("/getUserById/{userId}")
    public CommonResult<User> getUserById(@PathVariable("userId") Integer userId) {
        CommonResult<User> result = new CommonResult<>();
        Integer code = 200;
        String message = "success";
        try {
            User u = new User(userId, "小明", "123456");
            result.setResult(u);
        } catch (Exception e) {
            code = 500;
            message = "failed";
        }
        result.setMessage(message);
        result.setCode(code);
        return result;
    }

    @PutMapping("/updateUserName")
    public CommonResult<User> updateUserName(@RequestBody User user) {
        CommonResult<User> result = new CommonResult<>();
        User user1 = new User(1, "小明", "123456");
        Integer code = 200;
        String message = "修改名称成功:" + user1.getUserName();
        try {
            message = message + "->" + user.getUserName();
            result.setResult(user);
        } catch (Exception e) {
            code = 500;
            message = "failed";
        }
        result.setMessage(message);
        result.setCode(code);
        return result;
    }

    @PostMapping("/login")
    public CommonResult<User> login(@RequestParam("userName") String userName, @RequestParam("password") String password) {
        CommonResult<User> result = new CommonResult<>();
        Integer code = 200;
        String message = "登录成功！";
        try {
            if (userName.equals("小明") && password.equals("123456")) {
                User u = new User(1, "小明", "123456");
                result.setResult(u);
            } else {
                code = 500;
                message = "登录失败！";
            }
        } catch (Exception e) {
            code = 500;
            message = "登录失败！";
        }
        result.setMessage(message);
        result.setCode(code);
        return result;
    }

    @DeleteMapping("/logout/{userName}")
    public CommonResult<String> logout(@PathVariable("userName") String userName) {
        CommonResult<String> commonResult = new CommonResult<>();
        Integer code = 200;
        String message = "注销成功！";
        try {
            if (userName.equals("小明")) {
                String result = "logout";
                commonResult.setResult(result);
            } else {
                code = 500;
                message = "注销失败！";
            }
        } catch (Exception e) {
            code = 500;
            message = "注销失败！";
        }
        commonResult.setMessage(message);
        commonResult.setCode(code);
        return commonResult;
    }

    @GetMapping("/load")
    public CommonResult<String> load() {
        CommonResult<String> commonResult = new CommonResult<>();
        commonResult.setResult("12001");
        commonResult.setCode(200);
        commonResult.setMessage("success");
        return commonResult;
    }
}
