package com.smartICT.PTMS.Config;

import jakarta.servlet.Filter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import static org.springframework.security.config.Customizer.withDefaults;
@EnableWebSecurity
@Configuration
public class SecurityConfig {

    @Autowired
    private CorsConfig corsConfig;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http.authorizeHttpRequests((requests) -> requests.anyRequest().authenticated());
        http.formLogin(withDefaults());
        http.httpBasic(withDefaults());

        http
                .addFilterBefore(corsFilter(), UsernamePasswordAuthenticationFilter.class)
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        return http.build();
    }

    @Bean
    public CorsFilter corsFilter() {
        return new CorsFilter(corsConfig);
    }

}


    //Cors configuration
    //web.cors
    //setalowedmethods
    //Addfilterbefor
    //class:usernamepasswordautohtacitonfilter.classs
 /*
    protected void configure(HttpSecurity http) throws Exception {

        http
                .authorizeRequests()
                .requestMatchers(HttpMethod.POST, "/api/**").authenticated()
                .anyRequest().permitAll();
}
        */

