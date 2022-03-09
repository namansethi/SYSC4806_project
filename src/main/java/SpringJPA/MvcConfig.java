package SpringJPA;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer {
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/landing").setViewName("landing");
        registry.addViewController("/").setViewName("landing");
        registry.addViewController("/login").setViewName("login");
        registry.addViewController("/pricing").setViewName("pricing");
    }
}
