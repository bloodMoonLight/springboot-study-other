package com.example.springboot.other.springmvc.config;

import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.File;

/**
 * 解决父子工程中访问页面出现404的情况
 * @ClassName GlobalConfig
 * @Description TODO
 * @Author 张鸿志
 * @Date 2019年12月1日10:11:56 10:11
 * Version 1.0
 **/
@Configuration
public class GlobalConfig {

    @Bean
    public WebServerFactoryCustomizer<TomcatServletWebServerFactory> customizer(){
        return (factory) -> {
            factory.addContextCustomizers((context)->{   //模块中webapp的相对路径
                //springmvc为子项目的module名称
                String relativePath = "springmvc/src/main/webapp";
                File docBaseFile = new File(relativePath); //如果路径不存在，则把该路径加载进去
                if (docBaseFile.exists()){
                    context.setDocBase(docBaseFile.getAbsolutePath());
                }
            });

        };
    }
}
