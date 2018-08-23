package pl.milionerzy.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Piotr Krzyminski (piotr.krzyminski@hycom.pl)
 */
@Controller
@RequestMapping(value = "/about")
public class AboutController {

    @GetMapping
    public String getAbout() {
        return "about";
    }

}
