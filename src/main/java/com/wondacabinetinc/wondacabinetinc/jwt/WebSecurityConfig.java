package com.wondacabinetinc.wondacabinetinc.jwt;

import com.wondacabinetinc.wondacabinetinc.datalayer.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configurers.provisioning.InMemoryUserDetailsManagerConfigurer;
import org.springframework.security.config.annotation.authentication.configurers.userdetails.DaoAuthenticationConfigurer;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@RequiredArgsConstructor
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final EmployeeRepository employeeRepository;

    @Value("${default-employee.username:admin}")
    private String DEFAULT_EMPLOYEE_USERNAME;
    @Value("${default-employee.password:admin}")
    private String DEFAULT_EMPLOYEE_PASSWORD;

    @Autowired
    UserDetailsServiceImpl userDetailsService;

    @Autowired
    private AuthEntryComponent unauthorizedHandler;

    @Bean
    public AuthFilter authFilter(){
        return new AuthFilter();
    }

    @Override
    public void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
        final DaoAuthenticationConfigurer dao = new DaoAuthenticationConfigurer<>(username -> userDetailsService.loadUserByUsername(username));
        dao.passwordEncoder(passwordEncoder());


        final InMemoryUserDetailsManagerConfigurer inMem = new InMemoryUserDetailsManagerConfigurer();
        inMem.withUser(DEFAULT_EMPLOYEE_USERNAME).password("{noop}" + DEFAULT_EMPLOYEE_PASSWORD)
                        .roles("EMPLOYEE");

        authenticationManagerBuilder.apply(inMem);
        authenticationManagerBuilder.apply(dao);
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable()
                .exceptionHandling().authenticationEntryPoint(unauthorizedHandler).and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                .authorizeRequests().antMatchers("/auth/**").permitAll()
                .antMatchers("/test/**").permitAll()
                .anyRequest().authenticated();
        http.addFilterBefore(authFilter(), UsernamePasswordAuthenticationFilter.class);
    }
}
