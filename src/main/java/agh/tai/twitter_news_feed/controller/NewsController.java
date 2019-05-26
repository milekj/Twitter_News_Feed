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
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.Duration;
import java.time.Period;

@Controller
@RequestMapping("/news")
public class NewsController {
    private final static int NEWS_TO_DOWNLOAD_NUMBER = 10;
    private final static int NEWS_PER_INTEREST_TO_SHOW_NUMBER = 3;
    private final static Duration DURATION_BETWEEN_UPDATES = Duration.ofMinutes(1);
    private final static Period PERIOD_BETWEEN_UPDATES = Period.ZERO;
    private NewsService newsService;

    @Autowired
    public NewsController(NewsService newsService) {
        this.newsService = newsService;
    }

    @GetMapping("")
    public String userNews(@AuthenticationPrincipal SocialUserDetailsImpl userDetails,
                           Model model) {
        User user = userDetails.getUser();
        newsService.updateNewsIfNecessary(user, NEWS_TO_DOWNLOAD_NUMBER, PERIOD_BETWEEN_UPDATES, DURATION_BETWEEN_UPDATES);
        model.addAttribute("interestsNewsMap", newsService.getNewsPerInterest(user,  NEWS_PER_INTEREST_TO_SHOW_NUMBER));
        return "news";
    }

    @GetMapping("/update")
    public String updateNews(@AuthenticationPrincipal SocialUserDetailsImpl userDetails) {
        newsService.updateNews(userDetails.getUser(), NEWS_TO_DOWNLOAD_NUMBER);
        return "redirect:/news";
    }
}
