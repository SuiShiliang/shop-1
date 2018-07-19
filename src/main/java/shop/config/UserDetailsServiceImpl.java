package shop.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import shop.mapper.UserMapper;
import shop.model.User;


@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    
    private UserMapper userMapper;
    
    @Autowired
    public UserDetailsServiceImpl(UserMapper userMapper) {
        this.userMapper = userMapper;
    }
    
    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {
        User user = userMapper.findOneByUsername(username);
        
        if (user == null) { 
            throw new UsernameNotFoundException("用户名不存在: " + username);
        } 
        
        return new UserDetailsImpl(user);
    }
}
