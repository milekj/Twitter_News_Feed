package agh.tai.twitter_news_feed;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.connect.UsersConnectionRepository;
import org.springframework.social.connect.web.ProviderSignInUtils;
import org.springframework.social.connect.web.SignInAdapter;
import org.springframework.social.security.SocialAuthenticationToken;
import org.springframework.social.twitter.api.Twitter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.context.request.WebRequest;

@Controller
public class MainController {
    private ProviderSignInUtils providerSignInUtils;

    @GetMapping("/info")
    public String info(@AuthenticationPrincipal SocialUserDetailsImpl userDetails, Model model) {
        Connection<Twitter> connection = userDetails.getConnection();
        model.addAttribute("user", userDetails);
        model.addAttribute("connection", connection);
        return "info";
    }

    @GetMapping("/signup")
    public String signup(WebRequest request) {
        Connection<?> connection = providerSignInUtils.getConnectionFromSession(request);
        if (connection != null) {
            String displayName = connection.getDisplayName();
            providerSignInUtils.doPostSignUp(displayName, request);
            SecurityContextHolder.getContext().setAuthentication(new SocialAuthenticationToken(connection, null));
        }
        return "redirect:/info";
    }

    @Autowired
    public void setProviderSignInUtils(ProviderSignInUtils providerSignInUtils) {
        this.providerSignInUtils = providerSignInUtils;
    }
}
