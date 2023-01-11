package org.university.db.project.tinytwitter.service;

import org.springframework.stereotype.Service;
import org.university.db.project.tinytwitter.dao.UserMapper;
import org.university.db.project.tinytwitter.entity.User;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import javax.annotation.Resource;

@Service
public class UserService {

    @Resource
    private UserMapper userMapper;

    public boolean exist(String username) {
        return userMapper.findUNameCount(username) > 0;
    }

    public User login(String username, String password) {
        return userMapper.findByUNamePwd(username, password);
    }

    public boolean add(User user) {
        userMapper.insert(user);
        return true;
    }

}
