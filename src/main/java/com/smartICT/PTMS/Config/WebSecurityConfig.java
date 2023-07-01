package com.smartICT.PTMS.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity()
public class WebSecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(request -> request
                        .requestMatchers(HttpMethod.GET).authenticated()
                        .requestMatchers(HttpMethod.POST).authenticated()
                        .requestMatchers(HttpMethod.PUT).authenticated()
                        .requestMatchers(HttpMethod.DELETE).authenticated()
                        .requestMatchers("/login").permitAll()
                )
                .formLogin(form -> form.permitAll())
                .httpBasic(Customizer.withDefaults());

        return http.build();
    }
}

    /*
        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests((requests) -> requests
                        .requestMatchers(("/login")).permitAll()
                        .requestMatchers("**").authenticated()
                .anyRequest().authenticated()) //other URLs are only allowed authenticated users.

                .formLogin(form -> form.permitAll());
// Bu çalışan
         */

/*
    protected void configure(HttpSecurity http) throws Exception {

        http
                .authorizeHttpRequests(authz -> authz
                        .requestMatchers("/login")
                        .authenticated()
                        .anyRequest().permitAll());
    }
  */


/*
        http
                .cors(cors->cors.disable());

 */


       /*
        http.
                cors().and().csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and().authorizeRequests()
                .requestMatchers("/api/**").permitAll()
                .requestMatchers("/api/drivers/**").permitAll()
                .anyRequest().authenticated();
        http.addFilterBefore(authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);
        return http.build();
        */

/*
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                .authorizeRequests().antMatchers("*").authenticated();

        super.configure(http);
    }

 */
    //Cors configuration
    //web.cors
    //setalowedmethods
    //Addfilterbefor
    //class:usernamepasswordautohtacitonfilter.classs


    /*     */

