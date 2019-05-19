package agh.tai.twitter_news_feed.controller;

import agh.tai.twitter_news_feed.authentication.SocialUserDetailsImpl;
import agh.tai.twitter_news_feed.entity.User;
import agh.tai.twitter_news_feed.service.InterestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class NewsController {
    private InterestService interestService;

    @Autowired
    public NewsController(InterestService interestService) {
        this.interestService = interestService;
    }

    @GetMapping("/news")
    public String userNews(@AuthenticationPrincipal SocialUserDetailsImpl userDetails,
                           Model model) {
        User user = userDetails.getUser();
        model.addAttribute("interests", interestService.findAllUserInterests(user));
        System.out.println("zaladowalem");
        return "news";
    }
}
