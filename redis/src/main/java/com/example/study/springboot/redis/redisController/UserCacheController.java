package com.example.study.springboot.redis.redisController;

import com.example.study.springboot.redis.pojo.User;
import com.example.study.springboot.redis.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/userCache")
public class UserCacheController {

    @Autowired
    private UserService userService;

    /**
     * 查询方法
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping("/getUser")
    public User getUser(Long id){
        return userService.getUser(id);
    }

    @ResponseBody
    @RequestMapping("/insertUser")
    public User insertUser(String userName,String note){
        User u = new User();
        u.setUserName(userName);
        u.setNote(note);
        userService.insertUser(u);
        return u;
    }

    @ResponseBody
    @RequestMapping("/findUsers")
    public List<User> findUsers(String userName,String note){
        return userService.findUsers(userName,note);
    }

    @ResponseBody
    @RequestMapping("/updateUserName")
    public Map<String,Object> updateUserName(Long id,String userName){
        User user = userService.updateUser(id, userName);
        boolean flag = user != null;
        String message = flag ? "更新成功" : "更新失败";
        return resultMap(flag,message);
    }

    @ResponseBody
    @RequestMapping("/deleteUser")
    public Map<String,Object> deleteUser(Long id){
        int i = userService.deleteUser(id);
        boolean flag = i == 1;
        String message = flag ? "删除成功" : "删除失败";
        return resultMap(flag,message);
    }



    private Map<String,Object> resultMap(boolean success,String message){
        Map<String,Object> result = new HashMap<>();
        result.put("success",success);
        result.put("message",message);
        return result;
    }


}
