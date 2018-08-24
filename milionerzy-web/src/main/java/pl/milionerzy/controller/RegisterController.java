package pl.milionerzy.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.milionerzy.data.user.CredentialData;
import pl.milionerzy.facades.user.UserFacade;

import javax.validation.Valid;

/**
 * Controller for registration page.
 *
 * @author Piotr Krzyminski
 */
@Controller
@RequestMapping(value = "/register")
public class RegisterController {

    private static final Logger LOG = LoggerFactory.getLogger(RegisterController.class);

    private final UserFacade userFacade;

    @Autowired
    public RegisterController(UserFacade userFacade) {
        this.userFacade = userFacade;
    }

    @GetMapping
    public String getRegister(Model model) {

        model.addAttribute("user", new CredentialData());

        return "register";
    }

    @PostMapping
    public String registerUserAccount(
            Model model,
            @ModelAttribute("user")
            @Valid CredentialData data) {

        try {
            userFacade.register(data);
        } catch (Exception e) {
            LOG.error(e.getMessage());
            model.addAttribute("error", e.getMessage());
            return "register";
        }

        return "redirect:/";
    }
}
