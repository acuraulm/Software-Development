package acu.assignment2.view;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Date;

@Controller
public class ViewController {
    private String appMode;

    @Autowired
    public ViewController(Environment environment){
        appMode = environment.getProperty("app-mode");
    }

    @RequestMapping("/")
    public String index(Model model){
        model.addAttribute("datetime", new Date());
        model.addAttribute("username", "John");
        model.addAttribute("mode", appMode);

        return "index";
    }
    
    @RequestMapping("/administrator")
    public String administrator(Model model) {
    	model.addAttribute("username", "Username");
    	model.addAttribute("name","Nume Administrator");
    	model.addAttribute("age", 34);
    	model.addAttribute("mode", appMode);
    	
    	return "administrator";
    }
    
    @RequestMapping("/student")
    public String student(Model model) {
    	model.addAttribute("username", "Username");
    	model.addAttribute("name","Nume Student");
    	model.addAttribute("age", 21);
    	model.addAttribute("mode", appMode);
    	
    	return "student";
    }
    
    @RequestMapping("/course")
    public String course(Model model) {
    	model.addAttribute("username", "acuraulm");
    	model.addAttribute("name","Acu Raul");
    	model.addAttribute("id",1);
    	model.addAttribute("mode", appMode);
    	
    	return "course";
    }
}
