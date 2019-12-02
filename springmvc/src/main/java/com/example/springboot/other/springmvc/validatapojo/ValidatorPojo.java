package com.example.springboot.other.springmvc.validatapojo;

import lombok.Data;
import org.hibernate.validator.constraints.Range;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.*;
import java.util.Date;
@Data
public class ValidatorPojo {

    @NotNull(message = "id不能为空")
    private Long id;

    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Future(message = "需要一个将来时期")
   // @Past(message = "只能是过去的时间")
    private Date date;

    @NotNull
    @DecimalMin(value = "0.1")  //最小值
    @DecimalMax(value = "10000.00") // 最大值
    private Double doubleValue = null;
    @NotNull
    @Min(value = 1,message = "最小值为1")
    @Max(value = 88,message = "最大值为88")
    private Integer integer;

    /** 限定范围 */
    @Range(min = 1,max = 888,message = "范围为1至888")
    private Long range;

    @Email(message = "邮箱格式错误")
    private String email;


    @Size(min = 20,max = 30,message = "字符串长度要求20到30之间")
    private String size;
}
