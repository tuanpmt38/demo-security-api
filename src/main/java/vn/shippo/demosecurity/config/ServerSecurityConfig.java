package vn.shippo.demosecurity.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import vn.shippo.demosecurity.filter.DemoAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class ServerSecurityConfig extends WebSecurityConfigurerAdapter {


    @Bean
    DemoAuthenticationFilter demoAuthenticationFilter(){
        return new DemoAuthenticationFilter();
    }
    @Override
    protected UserDetailsService userDetailsService() {
        return super.userDetailsService();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.addFilterBefore(demoAuthenticationFilter(),DemoAuthenticationFilter.class);

    }
}
