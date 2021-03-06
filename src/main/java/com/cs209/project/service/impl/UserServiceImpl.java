package com.cs209.project.service.impl;

import com.cs209.project.entity.User;
import com.cs209.project.mapper.UserMapper;
import com.cs209.project.service.IUserService;
import com.cs209.project.service.ex.InsertException;
import com.cs209.project.service.ex.PasswordNotMatchException;
import com.cs209.project.service.ex.UsernameDuplicatedException;
import com.cs209.project.service.ex.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.Date;
import java.util.UUID;

@Service
public class UserServiceImpl implements IUserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public void reg(User user) {
        User old = userMapper.selectByUsername(user.getUsername());
        if (old != null) throw new UsernameDuplicatedException("The user name has been registered!");
        String oldPwd = user.getPwd();
        String salt = UUID.randomUUID().toString().toUpperCase();
        String md5pwd = getMD5Pwd(oldPwd, salt);
        user.setPwd(md5pwd);
        user.setSalt(salt);
        user.setIsDelete(0);
        user.setCreatedUser(user.getUsername());
        user.setModifiedUser(user.getUsername());
        Date date = new Date(System.currentTimeMillis());
        user.setCreatedTime(date);
        user.setModifiedTime(date);
        Integer rows = userMapper.insert(user);
        if (rows != 1) throw new InsertException("An exception occurred during user registration!");
    }

    @Override
    public User login(String username, String password) {
        User user = userMapper.selectByUsername(username);
        if (user == null || user.getIsDelete() == 1) throw new UserNotFoundException("User doesn't exist!");
        String salt = user.getSalt();
        String md5pwd = getMD5Pwd(password, salt);
        if (!user.getPwd().equals(md5pwd)) throw new PasswordNotMatchException("The password is incorrect!");
        return user;
    }

    private String getMD5Pwd(String pwd, String salt) {
        return DigestUtils.md5DigestAsHex((salt + pwd + salt).getBytes()).toUpperCase();
    }
}
