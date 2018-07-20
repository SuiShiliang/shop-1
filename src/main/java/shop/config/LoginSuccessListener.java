package shop.config;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.InteractiveAuthenticationSuccessEvent;
import org.springframework.stereotype.Component;

import shop.mapper.UserMapper;


/**
 * 当有人登录成功时，springsecurity会发布事件InteractiveAuthenticationSuccessEvent到spring容器，
 * spring容器会找到实现了该事件监听器接口的组件，并调用其onApplicationEvent方法
 */
@Component
public class LoginSuccessListener implements ApplicationListener<InteractiveAuthenticationSuccessEvent> {
    private UserMapper userMapper;
    
    @Autowired
    public LoginSuccessListener(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public void onApplicationEvent(InteractiveAuthenticationSuccessEvent event) {
        UserDetailsImpl userDetails = (UserDetailsImpl) event.getAuthentication().getPrincipal();
        
        System.out.println("有人登录成功了: #" + 
                userDetails.getUser().getId() + ", " + 
                userDetails.getUsername());
        
        userMapper.updateLastLoginTime(userDetails.getUser().getId(), new Date());
    }
}
