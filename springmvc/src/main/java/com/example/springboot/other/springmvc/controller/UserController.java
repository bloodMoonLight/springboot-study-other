package com.example.springboot.other.springmvc.controller;

import com.example.springboot.other.springmvc.pojo.User;
import com.example.springboot.other.springmvc.repository.UserMapper;
import com.example.springboot.other.springmvc.validatapojo.ValidatorPojo;
import com.example.springboot.other.springmvc.validator.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import javax.validation.Valid;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName UserController
 * @Description TODO
 * @Author 张鸿志
 * @Date 2019年12月1日09:36:09 9:36
 * Version 1.0
 **/
@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserMapper userMapper;


    /**
     * 使用InitBinder注解修饰的方法
     * 当请求到达控制层时，执行方法前执行该方法，改变验证器
     * @param binder
     */
    @InitBinder
    public void initBinder(WebDataBinder binder){
        // 绑定验证器
        binder.setValidator(new UserValidator());
        //定义日期参数格式，参数不再需要注解@DateTimeFormat， boolean表示是否允许为空
        binder.registerCustomEditor(Date.class,new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd"),false));
    }

    @RequestMapping("/details")
    public ModelAndView details(Long id){
        User user = userMapper.selectById(id);
        ModelAndView mv = new ModelAndView();
        mv.setViewName("user/details");
        mv.addObject("user",user);
        return mv;
    }

    @RequestMapping("/detailsForJson")
    public ModelAndView detailsForJson(Long id){
        User user = userMapper.selectById(id);
        ModelAndView modelAndView = new ModelAndView();
        MappingJackson2JsonView jsonView = new MappingJackson2JsonView();
        modelAndView.setView(jsonView);
        modelAndView.addObject("user",user);
        return modelAndView;

    }


    @RequestMapping("/no")
    @ResponseBody
    public Map<String,Object> noAnnotation(Integer intval,Long longval,String str){
        Map<String,Object> map = new HashMap<>();
        map.put("int",intval);
        map.put("Long",longval);
        map.put("string",str);
        return map;
    }

    /**
     * 使用注解传参，其中需要注意的是，注解传参不能为null，不然会出现400
     * @Description TODO
     * @params
     * @Author 张鸿志
     * @Date 2019/12/1 20:35
     * @Return
     **/
    @ResponseBody
    @RequestMapping("/yes")
    public Map<String,Object> yesAnnotation(@RequestParam("int_val") Integer intval,
                                            @RequestParam("long_val") Long longval,
                                            @RequestParam(value = "str_val",required = false) String str){
        Map<String,Object> map = new HashMap<>();
        map.put("int",intval);
        map.put("Long",longval);
        map.put("string",str);
        return map;
    }


    @RequestMapping("/array")
    @ResponseBody
    public Map<String,Object> requestArray(int [] intarr,Long [] longarr,String [] strarr   ){
        Map<String,Object> map = new HashMap<>();
        map.put("int",intarr);
        map.put("Long",longarr);
        map.put("string",strarr);
        return map;
    }

    @GetMapping("/add")
    public String add(){
        return "user/add";
    }

    @PostMapping(value = "/insert")
    @ResponseBody
    public User insert(@RequestBody User user){
        userMapper.insert(user);
        System.out.println("返回的zhujianid"+user.getId());
        return user;

    }


    @GetMapping("/{id}")
    @ResponseBody
    public User get(@PathVariable("id") Long id){
        User user = userMapper.selectById(id);
            return user;
    }

    @GetMapping("/format/form")
    public String showFormat(){
        return "user/formatter";
    }
    @PostMapping("/commit")
    @ResponseBody
    public Map<String,Object> format(@DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date date,
            @NumberFormat(pattern = "#,###.##") Double number
            ){
        Map<String,Object> map = new HashMap<>(16);
        map.put("date",date);
        map.put("number",number);
        return map;
    }

    /**
     * 测试自定义转换器，将String类型转换为User对象类型
     * @param user
     * @return
     */
    @GetMapping("/converter")
    @ResponseBody
    public User getUserByConverter(User user){
        return user;
    }

    @GetMapping("/list")
    @ResponseBody
    public List<User> list(List<User> userList){
        return userList;
    }


    @GetMapping("/page")
    public String validatePage(){
        return "user/pojo";
    }

    /**
     * @Valid表示需要验证该对象
     * @param vp 自定义POJO
     * @param errors 验证错误信息对象
     * @return
     */
    @RequestMapping(value = "/validate")
    @ResponseBody
    public Map<String,Object> validate(
            @Valid @RequestBody ValidatorPojo vp, Errors errors){
        Map<String,Object> errMap = new HashMap<>(16);
        //获取全部错误列表
        List<ObjectError> allErrors = errors.getAllErrors();
        allErrors.forEach(er ->{
            String key = null;
            String msg = null;
            //如果是字段错误
            if(er instanceof FieldError){
                FieldError fe = (FieldError)er;
                key = fe.getField();
            }else{
                //非字段错误
                key = er.getObjectName(); // 获取验证对象名称
            }
            //获取错误信息
            msg = er.getDefaultMessage();
            errMap.put(key,msg);
        });
        return errMap;
    }

    /**
     * @Valid注解标记的参数，SpringMVC会去遍历对应的验证器
     * @param user 需要去验证的对象
     * @param errors
     * @param date
     * @return
     */
    @GetMapping("/vali")
    @ResponseBody
    public Map<String,Object> vali(
            @Valid User user,Errors errors,Date date){
        Map<String,Object> map = new HashMap<>(16);
        map.put("user",user);
        map.put("date",date);
        //判断是否存在错误
        if(errors.hasErrors()){
            List<ObjectError> allErrors = errors.getAllErrors();
            allErrors.forEach(ae ->{
                if(ae instanceof FieldError){
                    FieldError fe = (FieldError) ae;
                    map.put(fe.getField(),fe.getDefaultMessage());
                }else{
                    //对象错误
                    map.put(ae.getObjectName(),ae.getDefaultMessage());
                }
            });
        }
        return map;
    }

    /**
     * 测试model数据模型
     * @param id
     * @param model
     * @return
     */
    @GetMapping("/model")
    public String userModel(Long id, Model model){
        User user = userMapper.selectById(id);
        model.addAttribute("user",user);
        return "data/user";
    }

    /**
     * 在该方法中，没有为ModelAndView手动绑定ModelMap，但SpringMVC为自动绑定
     * @param id
     * @param modelMap
     * @return
     */
    @GetMapping("/modelMap")
    public ModelAndView modelMapUser(Long id, ModelMap modelMap){
        User user = userMapper.selectById(id);
        ModelAndView mv = new ModelAndView();
        mv.setViewName("data/user");
        modelMap.put("user",user);
        return mv;
    }

    /**
     * 测试ModelAndView
     * @param id
     * @param mv
     * @return
     */
    @GetMapping("/modelAndView")
    public ModelAndView modelAndViewUser(Long id, ModelAndView mv){
        User user = userMapper.selectById(id);
        //设置数据模型
        mv.addObject("user",user);
        mv.setViewName("data/user");
        return mv;
    }

}
