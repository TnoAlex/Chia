package org.summer.chia.config

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.AnonymousAuthenticationFilter
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import org.summer.chia.filter.AuthenticationFilter
import org.summer.chia.filter.AuthenticationProcessingFilter
import org.summer.chia.handler.AuthorizationDeniedHandler
import org.summer.chia.handler.LoginSuccessHandler
import org.summer.chia.handler.LogoutSuccessHandler
import org.summer.chia.security.SimpleAuthenticationProvider
import org.summer.chia.service.UserService

@EnableWebSecurity
class WebSecurityConfig {

    @Autowired
    private lateinit var userDetails: UserService

    @Autowired
    private lateinit var authError: AuthorizationDeniedHandler

    @Autowired
    private lateinit var authenticationFilter: AuthenticationFilter

    @Autowired
    private lateinit var authenticationProvider: SimpleAuthenticationProvider

    @Autowired
    private lateinit var authenticationProcessingFilter: AuthenticationProcessingFilter

    @Autowired
    private lateinit var loginSuccessHandler: LoginSuccessHandler

    @Autowired
    private lateinit var logoutSuccessHandler: LogoutSuccessHandler

    @Value("\${springdoc.api-docs.enabled}")
    private var docEnable: Boolean = false


    @Bean
    fun authenticationManager(config: AuthenticationConfiguration): AuthenticationManager = config.authenticationManager

    @Bean
    fun securityFilterChain(httpSecurity: HttpSecurity): SecurityFilterChain {
        authenticationProcessingFilter.setAuthenticationSuccessHandler(loginSuccessHandler)
        httpSecurity.csrf().disable().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.ALWAYS)
            .and().exceptionHandling().authenticationEntryPoint(authError).accessDeniedHandler(authError)
            .and().formLogin().disable()
            .authorizeRequests {
                it.antMatchers("/login/**").permitAll()
                    .antMatchers("/logout").permitAll()
                    .antMatchers("/static/**").permitAll()
                    .antMatchers("/verify/**").permitAll()
                    .antMatchers("/forget/**").permitAll()
                    .antMatchers("/teacher/**").hasRole("Teacher")
            }
            .addFilterAt(authenticationProcessingFilter, UsernamePasswordAuthenticationFilter::class.java)
            .addFilterBefore(authenticationFilter, AnonymousAuthenticationFilter::class.java)
            .userDetailsService(userDetails)
            .authenticationProvider(authenticationProvider)
            .logout().logoutSuccessHandler(logoutSuccessHandler)
        if (docEnable) {
            httpSecurity.authorizeRequests {
                it.antMatchers("/swagger-ui/**").permitAll()
                    .antMatchers("/swagger-ui.html").permitAll()
                    .antMatchers("/v3/**").permitAll()
                    .anyRequest().authenticated()
            }
        } else {
            httpSecurity.authorizeRequests {
                it.anyRequest().authenticated()
            }
        }
        return httpSecurity.build()
    }
}