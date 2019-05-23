package agh.tai.twitter_news_feed.controller;

import agh.tai.twitter_news_feed.authentication.SocialUserDetailsImpl;
import agh.tai.twitter_news_feed.entity.User;
import agh.tai.twitter_news_feed.service.InterestService;
import agh.tai.twitter_news_feed.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class NewsController {
    private final static int NEWS_TO_DOWNLOAD_NUMBER = 10;
    private final static int NEWS_PER_INTEREST_TO_SHOW_NUMBER = 3;
    private InterestService interestService;
    private NewsService newsService;

    @Autowired
    public NewsController(InterestService interestService, NewsService newsService) {
        this.interestService = interestService;
        this.newsService = newsService;
    }

    @GetMapping("/news")
    public String userNews(@AuthenticationPrincipal SocialUserDetailsImpl userDetails,
                           Model model) {
        User user = userDetails.getUser();
        model.addAttribute("interestsNewsMap", newsService.getNewsPerInterest(user,  NEWS_PER_INTEREST_TO_SHOW_NUMBER));
        return "news";
    }

    @GetMapping("/update")
    public String updateNews(@AuthenticationPrincipal SocialUserDetailsImpl userDetails) {
        newsService.updateNews(userDetails.getUser(), NEWS_TO_DOWNLOAD_NUMBER);
        return "redirect:/news";
    }
}
