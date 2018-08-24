package pl.milionerzy.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Controller for profile page
 *
 * @author Piotr Krzyminski
 */
@Controller
@RequestMapping(value = "/profile")
public class ProfileController {

    @GetMapping
    public String getProfile() {
        return "profile";
    }

}
