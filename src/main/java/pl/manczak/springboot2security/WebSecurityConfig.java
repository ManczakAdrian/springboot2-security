package pl.manczak.springboot2security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Collections;

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    public PasswordEncoder getPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {


        User userAdmin = new User("Jan",
                getPasswordEncoder().encode("Jan123"),
                Collections.singleton(new SimpleGrantedAuthority("ROLE_ADMIN")));

        User useruser = new User("Karol",
                getPasswordEncoder().encode("Karol123"),
                Collections.singleton(new SimpleGrantedAuthority("ROLE_USER")));


        auth.inMemoryAuthentication().withUser(userAdmin);
        auth.inMemoryAuthentication().withUser(useruser);
    }
}
