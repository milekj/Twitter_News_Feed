package agh.tai.twitter_news_feed.controller;

import agh.tai.twitter_news_feed.authentication.SocialUserDetailsImpl;
import agh.tai.twitter_news_feed.service.ITwitterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class InterestController {

    private ITwitterService twitterService;

    @Autowired
    public InterestController(ITwitterService twitterService) {
        this.twitterService = twitterService;
    }

    @GetMapping("/interest")
    public String redirectToInterests(@AuthenticationPrincipal SocialUserDetailsImpl userDetails, Model model) {
        model.addAttribute("interestsMap", twitterService.getFavouriteInterests(userDetails));
        return "interest_main";
    }

    @PostMapping("/find_interests")
    public String findInterests(Model model) {
        return "redirect:/interests";
    }
}

