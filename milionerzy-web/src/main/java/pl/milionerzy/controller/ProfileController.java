package pl.milionerzy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.milionerzy.data.user.UserData;
import pl.milionerzy.facades.user.UserFacade;

/**
 * Controller for profile page
 *
 * @author Piotr Krzyminski
 */
@Controller
@RequestMapping(value = "/profile")
public class ProfileController {

    @Autowired
    private UserFacade userFacade;

    @GetMapping
    public String getProfile(Model model) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        UserData loggedInUser = userFacade.getUserByUsername(auth.getName());
        if (loggedInUser == null) {
            return "redirect:/"; // user is not authenticated. Back to main page.
        }

        model.addAttribute("user", loggedInUser);

        return "profile";
    }

}
