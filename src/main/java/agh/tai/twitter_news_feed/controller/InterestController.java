package agh.tai.twitter_news_feed.controller;

import agh.tai.twitter_news_feed.authentication.SocialUserDetailsImpl;
import agh.tai.twitter_news_feed.entity.Interest;
import agh.tai.twitter_news_feed.service.InterestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class InterestController {

    private InterestService interestService;

    @Autowired
    public InterestController(InterestService interestService) {
        this.interestService = interestService;
    }

    @GetMapping("/interest")
    public String redirectToInterests(@AuthenticationPrincipal SocialUserDetailsImpl userDetails,
                                      Model model) {
        addInterestMainPropertiesToModel(userDetails, model);
        return "interest_main";
    }

    @GetMapping("/interest_tweet")
    public String getTweetsRelatedToInterest(@AuthenticationPrincipal SocialUserDetailsImpl userDetails,
                                             @RequestParam String interestName,
                                             Model model) {
        model.addAttribute("interestingTweets", interestService.getLatestTweetsWithHashTag(userDetails, interestName));
        return "interest_tweet";
    }

    @GetMapping("/add_interest")
    public String addInterest(@AuthenticationPrincipal SocialUserDetailsImpl userDetails,
                              @RequestParam String interestName,
                              Model model) {
        interestService.addInterest(new Interest(interestName, userDetails.getUser()));
        addInterestMainPropertiesToModel(userDetails, model);
        return "/interest_main";
    }

    @GetMapping("/remove_interest")
    public String removeInterest(@AuthenticationPrincipal SocialUserDetailsImpl userDetails,
                                 @RequestParam String interestName,
                                 Model model) {
        interestService.removeInterest(new Interest(interestName, userDetails.getUser()));
        addInterestMainPropertiesToModel(userDetails, model);
        return "/interest_main";
    }

    private void addInterestMainPropertiesToModel(SocialUserDetailsImpl userDetails, Model model) {
        model.addAttribute("offeredInterestsMap", interestService.getFavouriteInterests(userDetails));
        model.addAttribute("userInterests", interestService.getAllUserInterestsName(userDetails.getUser()));
    }

    @GetMapping
    public String excludeInterest(@AuthenticationPrincipal SocialUserDetailsImpl userDetails,
                                  @RequestParam String interestName,
                                  Model model) {
        interestService.excludeInterest(new Interest(interestName, userDetails.getUser()));
        addInterestMainPropertiesToModel(userDetails, model);
        return "/interest_main";
    }

}

