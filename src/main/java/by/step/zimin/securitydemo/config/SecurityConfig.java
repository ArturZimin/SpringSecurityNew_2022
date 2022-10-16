package by.step.zimin.securitydemo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public InMemoryUserDetailsManager userDetailsManager(){
        UserDetails user= User.withDefaultPasswordEncoder()
                .username("user")
                .password("password")
                .roles("USER")
                .build();

        UserDetails admin= User.withDefaultPasswordEncoder()
                .username("admin")
                .password("password")
                .roles("ADMIN")
                .build();

        return new InMemoryUserDetailsManager(user,admin);
    }

    @Bean
    public SecurityFilterChain configure(HttpSecurity http) throws Exception{

        //with help lambda
        return http.csrf(csrf->csrf.disable())
                .authorizeRequests(auth->{
                    auth.antMatchers("/").permitAll();
                    auth.antMatchers("/users").hasRole("USER");
                    auth.antMatchers("/admins").hasRole("ADMIN");
                })
                .httpBasic(Customizer.withDefaults())
                .build();
//        return http
//                .csrf().disable()
//                .authorizeRequests()
//                .antMatchers("/user").hasRole("USER")
//                .antMatchers("/admin").hasRole("ADMIN")
//                .anyRequest().authenticated()
//                .and()
//                .httpBasic()
//                .and().build();

    }
}
