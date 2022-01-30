package ua.com.alevel.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserDetailsService userDetailsService;

    public WebSecurityConfig(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager customAuthenticationManager() throws Exception {
        return authenticationManager();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/css/**", "/image/**", "/js/**", "/registration", "/", "/doctor/s/**", "/doctor/details/**").permitAll()
                .antMatchers("/account", "/account/add", "/reauthenticate").access("hasAnyRole('ROLE_PERSONAL','ROLE_DOCTOR','ROLE_PATIENT','ROLE_ADMIN','ROLE_PATIENT_DOCTOR')")
                .antMatchers("/account/patient", "/patient/edit").access("hasAnyRole('ROLE_PATIENT','ROLE_PATIENT_DOCTOR')")
                .antMatchers("/account/doctor", "/doctor/edit", "/patients").access("hasAnyRole('ROLE_DOCTOR','ROLE_PATIENT_DOCTOR')")
                .antMatchers("/patient/add").access("hasAnyRole('ROLE_PERSONAL','ROLE_DOCTOR')")
                .antMatchers("/doctor/add").access("hasAnyRole('ROLE_PERSONAL','ROLE_PATIENT')")
                .antMatchers("/admin/**").access("hasRole('ROLE_ADMIN')")
                .anyRequest().authenticated()
                .and().formLogin().loginPage("/login").defaultSuccessUrl("/account").permitAll()
                .and().logout().logoutUrl("/logout").logoutSuccessUrl("/login");
    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder());
    }
}
