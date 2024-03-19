package com.finance.bitcourse;

import com.finance.bitcourse.Jwt.JwtAuthenticationFilter;
import com.finance.bitcourse.Jwt.JwtTokenProvider;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.EnableGlobalAuthentication;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true)
//@RequiredArgsConstructor
@AllArgsConstructor
public class SecurityConfig {

    private final JwtTokenProvider jwtTokenProvider;
//    private final CustomUserDetailsService customUserDetailsService;
//    private final CustomAccessDenieHandler accessDenieHandler;
//    private final CustomAuthenticationEntryPoint authenticationEntryPoint;

//    private static final String[] AUTH_WHITELIST = {
//            "/api/v1/member/**", "/swagger-ui/**", "/api-docs", "/swagger-ui-custom.html",
//            "/v3/api-docs/**", "/api-docs/**", "/swagger-ui.html", "/api/v1/auth/**"
//    };

//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//        return http
//                // REST API이므로 basic auth 및 csrf 보안 사용x
//                .httpBasic().disable()
//                .csrf().disable()
//                // JWT 사용하기 때문에 세션 사용x
//                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//                .and()
//                .authorizeHttpRequests()
//                // 해당 API에 대해서는 모든 요청 허가
//                .requestMatchers("/members/sign-in").permitAll()
//                // USER 권한 있어야 요청 가능
//                .requestMatchers("/members/test").hasRole("USER")
//                // 이밖에 모든 요청에 대해 인증을 필요로 한다는 설정
//                .anyRequest().authenticated()
//                .and()
//                // JWT 인증을 위해 직접 구현한 필터를 UsernamePasswordAuthenticationFilter 전에 실행
//                .addFilterBefore(new JwtAuthenticationFilter(jwtTokenProvider), UsernamePasswordAuthenticationFilter.class).build();
//    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        // CSRF, CORS
       http.csrf((csrf) -> csrf.disable());
       http.cors(Customizer.withDefaults());

       // 세션 관리 상태 없음으로 구성, Spring Security가 세션 생성 or 사용 x
        http.sessionManagement(sessionManagement -> sessionManagement.sessionCreationPolicy(
                SessionCreationPolicy.STATELESS));

        // FormLogin, BasicHttp 비활성화
        http.formLogin((form) -> form.disable());
        http.httpBasic(AbstractHttpConfigurer::disable);

        // JwtAuthFilter를 UsernamePasswordAuthenticationFilter 앞에 추가
        http.addFilterBefore(new JwtAuthenticationFilter(jwtTokenProvider), UsernamePasswordAuthenticationFilter.class);

//        http.exceptionHandling((exceptionHandling) -> exceptionHandling
//                .authenticationEntryPoint(authenticationEntryPoint)
//                .accessDeniedHandler(accessDenieHandler));

        // 권한 규칙 작성
        http.authorizeHttpRequests(authorize -> authorize
//                .requestMatchers(AUTH_WHITELIST).permitAll()
                // 해당 API에 대해서는 모든 요청 허가
                .requestMatchers("/members/sign-up").permitAll()
                .requestMatchers("/members/login").permitAll()
                // USER 권한 있어야 요청 가능
                .requestMatchers("/members/test").hasRole("USER")
                // @PreAuthrization 사용 -> 모든 경로에 대한 인증처리 pass
                .anyRequest().permitAll());


        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        // JWT 사용하려면 기본적으로 password encoder 필요 > BCrypt Encoder 사용
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }
}
