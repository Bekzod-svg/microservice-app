package config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.server.SecurityWebFilterChain;

@Configuration
@EnableWebFluxSecurity
public class SecurityConfig {

//    @Bean
//    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity serverHttpSecurity){
//        serverHttpSecurity.csrf(ServerHttpSecurity.CsrfSpec::disable)
//                .authorizeExchange(exchange ->
//                    exchange.pathMatchers("/eureka/**")
//                            .permitAll()
//                            .anyExchange()
//                            .authenticated()
//                ).oauth2ResourceServer(spec -> spec.jwt(Customizer.withDefaults()));
//        return serverHttpSecurity.build();
//
//    }
//

    @Bean
    public SecurityWebFilterChain webFilterChain(ServerHttpSecurity serverHttpSecurity){
        serverHttpSecurity.csrf(csrfSpec -> csrfSpec.disable())
                .authorizeExchange(exchange -> exchange.pathMatchers("/eureka/**")
                        .permitAll()
                        .anyExchange()
                        .authenticated());
        return serverHttpSecurity.build();
    }

















}
