package ico.ductien.proj.monument.config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import ico.ductien.proj.monument.rest.CustomAccessDeniedHandler;
import ico.ductien.proj.monument.rest.JwtAuthenticationTokenFilter;
import ico.ductien.proj.monument.rest.RestAuthenticationEntryPoint;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
  @Bean
  public JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter() throws Exception {
    JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter = new JwtAuthenticationTokenFilter();
    jwtAuthenticationTokenFilter.setAuthenticationManager(authenticationManager());
    return jwtAuthenticationTokenFilter;
  }
  @Bean
  public RestAuthenticationEntryPoint restServicesEntryPoint() {
    return new RestAuthenticationEntryPoint();
  }
  @Bean
  public CustomAccessDeniedHandler customAccessDeniedHandler() {
    return new CustomAccessDeniedHandler();
  }
  @Bean
  @Override
  protected AuthenticationManager authenticationManager() throws Exception {
    return super.authenticationManager();
  }
  protected void configure(HttpSecurity http) throws Exception {
    // Disable crsf /rest/**
    http.csrf().ignoringAntMatchers("/api/**");
    http.authorizeRequests().antMatchers("/login**").permitAll();
    http.antMatcher("/api/**").httpBasic().authenticationEntryPoint(restServicesEntryPoint()).and()
        .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and().authorizeRequests()
        .antMatchers(HttpMethod.GET, "/api/users**").access("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
        .antMatchers(HttpMethod.POST, "/api/users").permitAll()
        .antMatchers(HttpMethod.POST, "/api/users/admin").access("hasRole('ROLE_ADMIN')")
        .antMatchers(HttpMethod.DELETE, "/api/users/**").access("hasRole('ROLE_ADMIN')")
        .antMatchers(HttpMethod.POST, "/api/monuments*").access("hasRole('ROLE_ADMIN')")
        .antMatchers(HttpMethod.PATCH, "/api/monuments*").access("hasRole('ROLE_ADMIN')")
        .antMatchers(HttpMethod.DELETE, "/api/monuments*").access("hasRole('ROLE_ADMIN')")
        .antMatchers(HttpMethod.GET, "/api/monuments*").access("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
        .antMatchers(HttpMethod.POST, "/api/departements*").access("hasRole('ROLE_ADMIN')")
        .antMatchers(HttpMethod.PATCH, "/api/departements*").access("hasRole('ROLE_ADMIN')")
        .antMatchers(HttpMethod.DELETE, "/api/departements*").access("hasRole('ROLE_ADMIN')")
        .antMatchers(HttpMethod.GET, "/api/departements*").access("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
        .antMatchers(HttpMethod.POST, "/api/lieux*").access("hasRole('ROLE_ADMIN')")
        .antMatchers(HttpMethod.PATCH, "/api/lieux*").access("hasRole('ROLE_ADMIN')")
        .antMatchers(HttpMethod.DELETE, "/api/lieux*").access("hasRole('ROLE_ADMIN')")
        .antMatchers(HttpMethod.GET, "/api/lieux*").permitAll()
        .antMatchers(HttpMethod.POST, "/api/celebrites*").access("hasRole('ROLE_ADMIN')")
        .antMatchers(HttpMethod.PATCH, "/api/celebrites*").access("hasRole('ROLE_ADMIN')")
        .antMatchers(HttpMethod.DELETE, "/api/celebrites*").access("hasRole('ROLE_ADMIN')")
        .antMatchers(HttpMethod.GET, "/api/celebrites*").access("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
        .and()
        .addFilterBefore(jwtAuthenticationTokenFilter(), UsernamePasswordAuthenticationFilter.class)
        .exceptionHandling().accessDeniedHandler(customAccessDeniedHandler());
  }
}
//.antMatchers(HttpMethod.POST, "/user/**").access("hasRole('ROLE_ADMIN')")