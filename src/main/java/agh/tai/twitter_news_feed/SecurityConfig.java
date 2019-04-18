package agh.tai.twitter_news_feed;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.social.security.SpringSocialConfigurer;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
//            .formLogin()
//                .loginPage("/auth/twitter")
//                .loginProcessingUrl("/processSignIn")
//                .failureUrl("/signin?param.error=bad_credentials")
//            .and()
//                .logout()
//                    .logoutUrl("/signout")
//                    .deleteCookies("JSESSIONID")
//            .and()
                .authorizeRequests()
                .antMatchers("/info")
                .authenticated()
            .and()
                .rememberMe()
            .and()
                .apply(newSpringSocialConfigurer());
    }

    private SpringSocialConfigurer newSpringSocialConfigurer() {
        SpringSocialConfigurer socialConfigurer = new SpringSocialConfigurer();
        socialConfigurer.postLoginUrl("/info");
        return socialConfigurer;
    }
}
