package com.FruitShopbackend.FruitShopapi.config;

import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
public class MyCustomSecurityConfiguration {
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        //todo remove h2 permission in production
        http
            .authorizeRequests().antMatchers("/fruits/h2/**").permitAll().and()
            .authorizeHttpRequests(authorize -> authorize
                    .antMatchers(
                            "/fruits/product/page-and-number-of-pages-{pageNumber}/sortedBy-{sortingMode}",
                            "/fruits/product/page-{pageNumber}/sortedBy-{sortingMode}",
                            "/fruits/cart/{cartId}/address/{addressType}",
                            "/fruits/product/image/{imageName}",
                            "/fruits/cart").permitAll()
                    .antMatchers(HttpMethod.POST,"/fruits/authorization/register").permitAll()
                    .anyRequest().authenticated()
            )
            .oauth2ResourceServer(oauth2 -> oauth2
                    .jwt(jwt -> jwt
                            .jwkSetUri("https://www.googleapis.com/oauth2/v3/certs")
                    )
            );
        http.cors();
        return http.build();
    }
    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring().antMatchers("/fruits/h2/**");
    }
}