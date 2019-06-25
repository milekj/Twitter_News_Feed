package agh.tai.twitter_news_feed.controller;

import agh.tai.twitter_news_feed.authentication.SocialUserDetailsImpl;
import agh.tai.twitter_news_feed.entity.DurationWithUnit;
import agh.tai.twitter_news_feed.entity.Interest;
import agh.tai.twitter_news_feed.service.InterestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.concurrent.TimeUnit;

@Controller
public class InterestController {
    private final static DurationWithUnit DEFAULT_NEWS_UPDATE_FREQUENCY
            = new DurationWithUnit(1, TimeUnit.HOURS);
    private final InterestService interestService;

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

    @GetMapping("/interestTweet")
    public String getTweetsRelatedToInterest(@AuthenticationPrincipal SocialUserDetailsImpl userDetails,
                                             @RequestParam String interestName,
                                             Model model) {
        model.addAttribute("interestingTweets", interestService.getLatestTweetsWithHashTag(userDetails, interestName));
        return "interest_tweet";
    }

    @GetMapping("/addInterest")
    public String addInterest(@AuthenticationPrincipal SocialUserDetailsImpl userDetails,
                              @RequestParam String interestName,
                              Model model) {
        interestService.addInterest(new Interest(interestName, userDetails.getUser(), DEFAULT_NEWS_UPDATE_FREQUENCY));
        addInterestMainPropertiesToModel(userDetails, model);
        return "/interest_main";
    }

    @GetMapping("/removeInterest")
    public String removeInterest(@AuthenticationPrincipal SocialUserDetailsImpl userDetails,
                                 @RequestParam String interestName,
                                 Model model) {
        interestService.removeInterest(new Interest(interestName, userDetails.getUser(), null));
        addInterestMainPropertiesToModel(userDetails, model);
        return "/interest_main";
    }

    private void addInterestMainPropertiesToModel(SocialUserDetailsImpl userDetails, Model model) {
        model.addAttribute("offeredInterestsMap", interestService.getFavouriteInterests(userDetails));
        model.addAttribute("userInterests", interestService.getAllUserInterestsName(userDetails.getUser()));
    }

    @GetMapping("/excludeInterest")
    public String excludeInterest(@AuthenticationPrincipal SocialUserDetailsImpl userDetails,
                                  @RequestParam String interestName,
                                  Model model) {
        interestService.excludeInterest(new Interest(interestName, userDetails.getUser(), null));
        addInterestMainPropertiesToModel(userDetails, model);
        return "/interest_main";
    }

    @GetMapping("/interest/updateFrequency")
    public String setUpdateFrequency(@AuthenticationPrincipal SocialUserDetailsImpl userDetails,
                                     @RequestParam int interestId,
                                     @RequestParam long duration,
                                     @RequestParam TimeUnit unit) {
        DurationWithUnit durationWithUnit = new DurationWithUnit(duration, unit);
        interestService.setUpdateFrequency(userDetails.getUsername(), interestId, durationWithUnit);
        return "redirect:/news";
    }

}

