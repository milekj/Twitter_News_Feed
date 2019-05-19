package agh.tai.twitter_news_feed.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.social.security.SpringSocialConfigurer;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/info", "/news")
                .authenticated()
                    .and()
                .rememberMe()
                    .and()
                .logout()
                .logoutUrl("/logout")
                .invalidateHttpSession(true)
                .clearAuthentication(true)
                .logoutSuccessUrl("/")
                    .and()
                .apply(newSpringSocialConfigurer());
    }

    private SpringSocialConfigurer newSpringSocialConfigurer() {
        SpringSocialConfigurer socialConfigurer = new SpringSocialConfigurer();
        socialConfigurer.postLoginUrl("/postLogin");
        return socialConfigurer;
    }
}
