package SpringJPA;

import SpringJPA.Model.User;
import SpringJPA.Model.UserRepository;
import SpringJPA.Model.UserType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;

import java.security.Principal;

@Controller
public class PageController {

    private static final Logger log = LoggerFactory.getLogger(WebpageApplication.class);

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    private void modifyNavBar(Model model, Principal principal){
        if(!(principal == null)) {
            User user = userRepository.findByUsername(principal.getName());
            model.addAttribute("userNav", user.getUsername());
            if (user.getRole() != UserType.ROLE_ADMIN) {
                model.addAttribute("adminStyleNav", "display: none;");
            }
            model.addAttribute("signInOutTextNav", "Sign Out");
            model.addAttribute("signInOutLinkNav", "/logout");
            model.addAttribute("registerStyleNav", "display: none;");
        }
        else{
            model.addAttribute("userStyleNav", "display: none;");
            model.addAttribute("adminStyleNav", "display: none;");
            model.addAttribute("signInOutTextNav", "Sign In");
            model.addAttribute("signInOutLinkNav", "/login");
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
        log.info(principal.toString());
        log.info(principal.getName());
        modifyNavBar(model, principal);
        String name = principal.getName();
        User user = userRepository.findByUsername(principal.getName());
        user.checkTrialEnd();
        model.addAttribute("user", user);
        return "userPage";
    }

    @GetMapping("/test")
    public String test() { return "test"; }

    @GetMapping("/upgrade")
    public String upgrade(Model model, Principal principal) {
        modifyNavBar(model, principal);
        return "upgrade";
    }

    @GetMapping("/user/admin")
    public String admin(Model model, Principal principal) {
        modifyNavBar(model, principal);
        model.addAttribute("users", userRepository.findAll());
        model.addAttribute("userApi", new User());
        return "adminview";
    }

    @PostMapping("/user/makeAPICall")
    public String makeAPICall(Principal principal, @ModelAttribute String placeholder){
        User user = userRepository.findByUsername(principal.getName());
        user.incrementApICallCount();
        userRepository.save(user);
        return "redirect:/user";
    }
    @PostMapping("user/admin/editRequests")
    public String editRequests(@RequestParam(value = "id") Long id, long apiCallLimit){
        User user = userRepository.findByUserId(id).get(0);
        user.setApiCallLimit(apiCallLimit);
        userRepository.save(user);
        return "redirect:/user/admin";
    }
    @PostMapping("user/admin/setRequests")
    public String setRequests(@RequestParam(value = "id") Long id){
        User user = userRepository.findByUserId(id).get(0);
        user.setApiCallCount(0L);
        userRepository.save(user);
        return "redirect:/user/admin";
    }

    @PostMapping("user/admin/editEmail")
    public String editEmail(@RequestParam(value = "id") Long id, String email){
        User user = userRepository.findByUserId(id).get(0);
        user.setEmail(email);
        userRepository.save(user);
        return "redirect:/user/admin";
    }

    @PostMapping("user/admin/changeStatus")
    public String changeStatus(@RequestParam(value = "id") Long id){
        User user = userRepository.findByUserId(id).get(0);
        if(user.getRole()!=UserType.ROLE_ADMIN){
            user.setRole(user.getRole() == UserType.ROLE_TRIAL ? UserType.ROLE_PREMIUM : UserType.ROLE_TRIAL);
        }
        userRepository.save(user);
        return "redirect:/user/admin";
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
            userRepository.save(newUser);
            return "redirect:/";
        }
        else {
            return "redirect:/register";
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
