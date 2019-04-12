package agh.tai.Twitter.News.Feed;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

@Controller
public class MainController {
    @Autowired
    private ConnectionRepository connectionRepository;

    @GetMapping("/info")
    public String info(@AuthenticationPrincipal Principal principal, Model model) {
        System.out.println(connectionRepository.findAllConnections().getFirst("twitter").getDisplayName());
        model.addAttribute("principal", principal);
        return "index";
    }
}
