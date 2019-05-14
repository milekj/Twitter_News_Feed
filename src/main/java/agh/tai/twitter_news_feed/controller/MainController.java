  package agh.tai.twitter_news_feed.controller;

import agh.tai.twitter_news_feed.authentication.SocialUserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.web.ProviderSignInUtils;
import org.springframework.social.twitter.api.Twitter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.context.request.WebRequest;

import java.util.Objects;

@Controller
public class MainController {

    private ProviderSignInUtils providerSignInUtils;

    @Autowired
    public MainController(ProviderSignInUtils providerSignInUtils) {
        this.providerSignInUtils = providerSignInUtils;
    }

    @GetMapping("/")
    public String index(@AuthenticationPrincipal SocialUserDetailsImpl userDetails){
        if (userDetails != null)
            return "redirect:/info";
        return "index";
    }

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
        if (Objects.nonNull(connection)) {
            String displayName = connection.getDisplayName();
            providerSignInUtils.doPostSignUp(displayName, request);
            return "redirect:/auth/twitter";
        }
        return "redirect:/postLogin";
    }

    @GetMapping("/postLogin")
    public String postLogin(@AuthenticationPrincipal SocialUserDetailsImpl userDetails) {
        if(!userDetails.getConnection().test()) {
            SecurityContextHolder.clearContext();
            return "redirect:/auth/twitter";
        }
        return "redirect:/info";
    }

}
