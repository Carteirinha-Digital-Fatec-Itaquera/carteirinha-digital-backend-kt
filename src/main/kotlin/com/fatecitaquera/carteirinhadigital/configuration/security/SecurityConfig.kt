package com.fatecitaquera.carteirinhadigital.configuration.security

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import org.springframework.web.cors.CorsConfiguration
import org.springframework.web.cors.CorsConfigurationSource
import org.springframework.web.cors.UrlBasedCorsConfigurationSource

@Configuration
class SecurityConfig(val jwtAuthFilter: JwtAuthFilter) {

    @Bean
    fun getEncoder(): PasswordEncoder = BCryptPasswordEncoder()

    @Bean
    fun authenticationManager(authenticationConfiguration: AuthenticationConfiguration): AuthenticationManager =
        authenticationConfiguration.authenticationManager

    @Bean
    fun securityFilterChain(http: HttpSecurity, jwtAuthEntryPoint: JwtAuthEntryPoint): SecurityFilterChain {
        http
            .cors {  }
            .csrf {
                it.disable()
            }
            .sessionManagement {
                it.disable()
            }
            .exceptionHandling {
                it.authenticationEntryPoint(jwtAuthEntryPoint)
            }
            .authorizeHttpRequests { auth ->
                auth
                    .requestMatchers("/autenticacoes/**").permitAll()

                    .requestMatchers("/redefinirsenha/**").permitAll()

                    .requestMatchers("/primeiro-acesso/**").permitAll()

                    .requestMatchers("/secretarias/criar").permitAll()

                    .requestMatchers(
                        "/estudantes/encontrar-todos",
                        "/estudantes/encontrar-por-ra",
                        "/estudantes/criar",
                        "/estudantes/atualizar",
                        "/estudantes/deletar"
                    ).hasRole("SECRETARY")

                    .requestMatchers("/estudante/buscar-carteirinha").hasRole("STUDENT")

                    .anyRequest().authenticated()
            }
            .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter::class.java)
        return http.build()
    }

    @Bean
    fun corsConfigurationSource(): CorsConfigurationSource {
        val configuration = CorsConfiguration()
        configuration.allowedOrigins = listOf("*")
        configuration.allowedMethods = listOf("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS")
        configuration.allowedHeaders = listOf("*")
        configuration.allowCredentials = false

        val source = UrlBasedCorsConfigurationSource()
        source.registerCorsConfiguration("/**", configuration)
        return source
    }

}
