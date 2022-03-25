package SpringJPA;

import SpringJPA.Model.User;
import SpringJPA.Model.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;

import java.security.Principal;

@Controller
public class PageController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    private void modifyNavBar(Model model, Principal principal){
        if(!(principal == null)) {
            User user = userRepository.findByUsername(principal.getName());
            model.addAttribute("user", user.getUsername());
            model.addAttribute("signInOutText", "Sign Out");
            model.addAttribute("signInOutLink", "/logout");
        }
        else{
            model.addAttribute("signInOutText", "Sign In");
            model.addAttribute("signInOutLink", "/login");
        }
    }

    @GetMapping("/")
    public String landing(Model model, Principal principal) {
        modifyNavBar(model, principal);
        return "landing";
    }

    @GetMapping("/login")
    public String login() { return "login"; }

    @GetMapping("/pricing")
    public String pricing(Model model, Principal principal) {
        modifyNavBar(model, principal);
        return "pricing";
    }

    @GetMapping("/user")
    public String user(Model model, Principal principal) {
        String name = principal.getName();
        User user = userRepository.findByUsername(principal.getName());
        model.addAttribute("user", user);
        return "userPage";

    }

    @GetMapping("/test")
    public String test() { return "test"; }

    @GetMapping("/upgrade")
    public String upgrade() { return "upgrade"; }

    @GetMapping("/user/admin")
    public String admin(Model model, Principal principal) {
        modifyNavBar(model, principal);
        model.addAttribute("users", userRepository.findAll());
        return "adminview";
    }

    @PostMapping("/user/makeAPICall")
    public String makeAPICall(Principal principal, @ModelAttribute String placeholder){
        User user = userRepository.findByUsername(principal.getName());
        user.incrementApICallCount();
        userRepository.save(user);
        return "redirect:/user";
    }
    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute("newUser", new User());
        return "register";
    }

    @PostMapping("/register")
    public String registerSubmit(@ModelAttribute  User newUser, Model model){
        if(userRepository.findByUsername(newUser.getUsername()) == null){
            model.addAttribute("newUser", newUser);
            newUser.setPassword(passwordEncoder.encode(newUser.getPassword()));
            newUser.setRole("USER");
            userRepository.save(newUser);
            return "landing";
        }
        else {
            return "register";
        }
    }


    @Bean
    public ClassLoaderTemplateResolver secondaryTemplateResolver() {
        ClassLoaderTemplateResolver secondaryTemplateResolver = new ClassLoaderTemplateResolver();
        secondaryTemplateResolver.setPrefix("noUser/");
        secondaryTemplateResolver.setSuffix(".html");
        //secondaryTemplateResolver.setTemplateMode(TemplateMode.HTML);
        secondaryTemplateResolver.setCharacterEncoding("UTF-8");
        secondaryTemplateResolver.setOrder(1);
        secondaryTemplateResolver.setCheckExistence(true);
        return secondaryTemplateResolver;
    }

    @Bean
    public ClassLoaderTemplateResolver trialUserTemplateResolver() {
        ClassLoaderTemplateResolver trialUserTemplateResolver = new ClassLoaderTemplateResolver();
        trialUserTemplateResolver.setPrefix("TrialUser/");
        trialUserTemplateResolver.setSuffix(".html");
        //trialUserTemplateResolver.setTemplateMode(TemplateMode.HTML);
        trialUserTemplateResolver.setCharacterEncoding("UTF-8");
        trialUserTemplateResolver.setOrder(1);
        trialUserTemplateResolver.setCheckExistence(true);
        return trialUserTemplateResolver;
    }
}
