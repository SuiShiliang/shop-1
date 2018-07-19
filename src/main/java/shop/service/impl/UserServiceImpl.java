package shop.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import shop.mapper.UserMapper;
import shop.service.UserService;

@Service
public class UserServiceImpl implements UserService {
    private PasswordEncoder passwordEncoder;
    
    private UserMapper userMapper;
    
    @Autowired
    public UserServiceImpl(PasswordEncoder passwordEncoder,
                           UserMapper userMapper) {
        this.passwordEncoder = passwordEncoder;
        this.userMapper = userMapper;
    }

    @Override
    public void register(String username, String password) {
        String encodedPassword = passwordEncoder.encode(password);
        userMapper.create(username, encodedPassword);
    }

}
