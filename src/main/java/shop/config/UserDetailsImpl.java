package shop.config;

import java.util.Arrays;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

import shop.model.User;


public class UserDetailsImpl extends org.springframework.security.core.userdetails.User {
    private User user;
    
    public UserDetailsImpl(User user) {
        super(user.getUsername(), 
              user.getPassword(), 
              true, true, true, true, Arrays.asList(new SimpleGrantedAuthority("ROLE_USER")));
                
        this.user = user;
    }
    
    public User getUser() {
        return user;
    }
}
