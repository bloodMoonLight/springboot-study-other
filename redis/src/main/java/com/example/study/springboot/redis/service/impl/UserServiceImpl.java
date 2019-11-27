package com.example.study.springboot.redis.service.impl;

import com.example.study.springboot.redis.pojo.User;
import com.example.study.springboot.redis.repository.UserDao;
import com.example.study.springboot.redis.service.UserService;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserServiceImpl implements UserService, ApplicationContextAware {
    @Autowired
    UserDao userDao;

    @Autowired
    private ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    /**
     * Cacheable:表示先从缓存中通过定义的键查询，如果可以查询到数据，则返回。如果查不到，则执行该方法，返回数据，
     * 并将返回的结果保存在缓存中
     * key：缓存用来查询的id
     * @param id
     * @return
     */
    @Override
    @Transactional
    @Cacheable(value = "redisCache", key = "'redis_user_'+#id")
    public User getUser(Long id) {
        return userDao.getUser(id);
    }

    /**
     * CachePut: 表示将方法的结果放到缓存中
     * value：为配置文件中定义的缓存名称
     * key为参数中所携带的id，因为这里是保存用户，所以使用mybatis的主键返回策略来拼接
     * @param user
     * @return
     */
    @Override
    @Transactional
    @CachePut(value = "redisCache", key = "'redis_user_'+#result.id")
    public User insertUser(User user) {
        userDao.insertUser(user);
        return user;
    }

    /**
     * 更新数据后，更新缓存，如果condition配置项使结果返回null，则不缓存
     * @return
     */
    @Override
    @Transactional
    @CachePut(value = "redisCache",condition = "#result != 'null'", key = "'redis_user_'+#id")
    public User updateUser(Long id,String userName) {
        // 在Spring中如果在同一个class下两个方法相互调用，那么被调用的方法上的所有注解全部失效，因为没有使用代理对象调用
        //此时可以使用其他类来调用，或者从容器中获取对象
        UserService bean = applicationContext.getBean(UserService.class);
        //此时getUser方法是代理对象，注解生效，在控制层输出中可以看到，没有发送查询语句，而是使用缓存中的数据得到该对象是存在的
        User user = bean.getUser(id);
        if(user == null){
            return null;
        }
        user.setUserName(userName);
        userDao.updateUser(user);
        return user;
    }

    /**
     * CacheEvict：通过定义的键移除缓存。
     * beforeInvocation：boolean类型的配置项，表示是在方法执行之前就移除缓存，还是方法执行之后移除缓存
     * @param id
     * @return
     */
    @Override
    @Transactional
    @CacheEvict(value = "redisCache",key = "'redis_user_'+#id", beforeInvocation = false)
    public int deleteUser(Long id) {
        return userDao.deleteUser(id);
    }

    /**
     * 因为该方法通过很多参数进行查询，命中率低，所以不做缓存
     * @param username
     * @param note
     * @return
     */
    @Override
    @Transactional
    public List<User> findUsers(String username, String note) {
        return userDao.findUsers(username,note);
    }
}
