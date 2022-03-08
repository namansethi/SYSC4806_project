package sysc4806.project.webpage;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LandingController {
    @GetMapping("/")
    public String landing() {
        return "landing";
    }

    @GetMapping("/login")
    public String login() { return "login"; }

    @GetMapping("/trial")
    public String trial() { return "trial"; }

    @GetMapping("/pricing")
    public String pricing() { return "pricing"; }

    @GetMapping("/register")
    public String register() { return "register"; }
}
