package com.example.springboot.other.springmvc.converter;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;

import java.io.IOException;
import java.util.List;

/**
 * 将http请求体转换为对应的Java对象
 */
//@Configuration
public class UserConverter implements HttpMessageConverter {
    /**
     *  是否可读
     * @param aClass 该类型为Java的类型。
     * @param mediaType 该类型为Http请求类型
     * @return
     */
    @Override
    public boolean canRead(Class aClass, MediaType mediaType) {
        return false;
    }

    /**
     * 是否可以写入
     * 判断class类型是否能够转换为mediaType
     * @param aClass
     * @param mediaType
     * @return
     */
    @Override
    public boolean canWrite(Class aClass, MediaType mediaType) {
        return false;
    }

    /**
     * 可支持的媒体类型列表
     * @return
     */
    @Override
    public List<MediaType> getSupportedMediaTypes() {
        return null;
    }

    /**
     * 当canread验证通过后，读入HTTP请求信息
     * @param aClass
     * @param httpInputMessage
     * @return
     * @throws IOException
     * @throws HttpMessageNotReadableException
     */
    @Override
    public Object read(Class aClass, HttpInputMessage httpInputMessage) throws IOException, HttpMessageNotReadableException {
        return null;
    }

    /**
     * 当canwrite方法验证通过后，写入响应
     * @param o
     * @param mediaType
     * @param httpOutputMessage
     * @throws IOException
     * @throws HttpMessageNotWritableException
     */
    @Override
    public void write(Object o, MediaType mediaType, HttpOutputMessage httpOutputMessage) throws IOException, HttpMessageNotWritableException {

    }
}
