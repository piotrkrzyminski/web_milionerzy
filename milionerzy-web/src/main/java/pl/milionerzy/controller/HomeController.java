package pl.milionerzy.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Controller for home page.
 *
 * @author Piotr Krzyminski
 */
@Controller
@RequestMapping(value = "/")
public class HomeController {

    @GetMapping
    public String getHome() {
        return "index";
    }
}
