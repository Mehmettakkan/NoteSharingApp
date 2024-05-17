/*
package org.demo.notesharingapp.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@EnableWebSecurity
@Configuration
public class SecurityConfig {

    @Bean
    public UserDetailsManager userDetailsManager(DataSource dataSource) {
        JdbcUserDetailsManager jdbcUserDetailsManager = new JdbcUserDetailsManager(dataSource);

        // Define query to retrieve a user by username
        jdbcUserDetailsManager.setUsersByUsernameQuery(
                "SELECT user_name, password, enabled FROM sharers WHERE user_name=?");

        // Define query to retrieve the authorities/roles by username (assuming many-to-many relationship)
        jdbcUserDetailsManager.setAuthoritiesByUsernameQuery(
                "SELECT s.user_name, r.role " +
                        "FROM sharers s " +
                        "INNER JOIN user_roles ur ON s.id = ur.user_id " +
                        "INNER JOIN roles r ON ur.role_id = r.id " +
                        "WHERE s.user_name=?");

        return jdbcUserDetailsManager;
    }


    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(configurer ->
                        configurer
                                .requestMatchers(HttpMethod.GET, "/sharers/all", "/sharers/{id}").hasRole("USER")
                                .requestMatchers(HttpMethod.GET, "/sharers/byEmail", "/sharers/byUsername").hasRole("USER")
                                .requestMatchers(HttpMethod.POST, "/sharers").hasRole("ADMIN")
                                .requestMatchers(HttpMethod.PUT, "/sharers/update/**").hasRole("ADMIN")
                                .requestMatchers(HttpMethod.DELETE, "/sharers/delete/**").hasRole("ADMIN")
                ).formLogin(form ->
                        form
                                .loginPage("/showLoginPage").permitAll()
                                .loginProcessingUrl("/processLoginForm")
                                .defaultSuccessUrl("/index")
                )
                //.logout(logout -> logout.permitAll())
                .exceptionHandling(configurer ->
                        configurer.accessDeniedPage("/access-denied")
                );
        ;

        //use HTTP basic authentication
        http.httpBasic(Customizer.withDefaults());

        //disable Cross Site Request Forgery (CSRF)
        //in general, not required for stateless REST APIs that use POST, PUT, DELETE and/or PATCH
        http.csrf(csrf -> csrf.disable());

        return http.build();
    }

     */
/*@Bean
     public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
         http
                 .authorizeHttpRequests(authorize -> authorize
                         .anyRequest().authenticated()
                 )
                 .formLogin(withDefaults())
                 .httpBasic(withDefaults());
         return http.build();
     }*//*

}
*/
