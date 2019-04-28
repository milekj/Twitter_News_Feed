package agh.tai.twitter_news_feed.controller;

import agh.tai.twitter_news_feed.authentication.SocialUserDetailsImpl;
import agh.tai.twitter_news_feed.service.IInterestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class InterestController {

    private IInterestService interestService;

    @Autowired
    public InterestController(IInterestService interestService) {
        this.interestService = interestService;
    }

    @GetMapping("/interest")
    public String redirectToInterests(@AuthenticationPrincipal SocialUserDetailsImpl userDetails,
                                      Model model) {
//        interestService.removeInterest(new Interest("tai_test", userDetails.getUserId()));
//        interestService.removeInterest(new Interest("test", userDetails.getUserId()));
//        Arrays.asList("NBA", "Trump", "Java").forEach(x -> interestService.addInterest(new Interest(x, userDetails.getUserId())));
        model.addAttribute("offeredInterestsMap", interestService.getFavouriteInterests(userDetails));
        model.addAttribute("userInterests", interestService.getAllUserInterestsName(userDetails.getUser()));
        return "interest_main";
    }

    @GetMapping("/interest_tweet")
    public String getTweetsRelatedToInterest(@AuthenticationPrincipal SocialUserDetailsImpl userDetails,
                                             @RequestParam String interestName,
                                             Model model) {
        model.addAttribute("interestingTweets", interestService.getLatestTweetsWithHashTag(userDetails, interestName));
        return "interest_tweet";
    }

}

