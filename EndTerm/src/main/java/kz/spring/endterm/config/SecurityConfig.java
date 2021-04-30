package kz.spring.endterm.config;

import kz.spring.endterm.model.RoleE;
import kz.spring.endterm.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private UserService userService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/auth/**").permitAll()
                // API Music
                .antMatchers("/api/musics", "/api/musics/{id}", "/api/musics/all").permitAll()
                .antMatchers("/api/musics/action/create", "/api/musics/action/update", "/api/musics/action/delete/{id}", "/api/musics/action/update/{id}").hasAuthority(RoleE.ADMIN.toString())
                // API Basket
                .antMatchers("/api/baskets", "/api/orders/all").permitAll()
                .antMatchers("/api/baskets/create").hasAnyAuthority(RoleE.ADMIN.toString(), RoleE.USER.toString())
                .antMatchers("/api/baskets/{id}/change-order-status").hasAuthority(RoleE.ADMIN.toString())
                // API Comments
                .antMatchers("/api/texts").permitAll()
                .antMatchers("/api/texts/create", "/api/texts/update", "/api/texts/delete/{id}").hasAnyAuthority(RoleE.ADMIN.toString(), RoleE.USER.toString())
                // API User
                .antMatchers("/api/users/all", "/api/users/create").permitAll()
                .antMatchers("/api/users/{id}/delete/favoriteMusic", "/api/users/{id}/update/password", "/api/users/{id}/update/add-book-to-favorites").hasAnyAuthority(RoleE.ADMIN.toString(),  RoleE.USER.toString())
                // API Swagger
                .antMatchers("/v2/api-docs",
                        "/swagger-resources/**",
                        "/configuration/ui",
                        "/configuration/security",
                        "/swagger-ui.html",
                        "/webjars/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .addFilter(new JwtTokenGeneratorFilter(authenticationManager()))
                .addFilterAfter(new JwtTokenAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService).passwordEncoder(passwordEncoder());
    }
}
