package acu.project1.view;

import org.jsondoc.core.annotation.ApiMethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import acu.project1.business.service.StudentService;
import acu.project1.business.transfer.StudentData;

import java.util.Date;
import java.util.List;

@Controller
public class ViewController {
    private String appMode;
    @Autowired
    private StudentService studentService;
    @Autowired
    public ViewController(Environment environment){
        appMode = environment.getProperty("app-mode");
    }

    @RequestMapping("/index")
    public String index(Model model){
        model.addAttribute("datetime", new Date());
        model.addAttribute("username", "Guest");
        model.addAttribute("mode", appMode);
        return "index";
    }
    
    
    @RequestMapping("/teacher")
    public String teacher(Model model) {
    	model.addAttribute("username", "Username");
    	model.addAttribute("name","Joldos Marius");
    	model.addAttribute("age", 34);
    	model.addAttribute("mode", appMode);
    	
    	return "teacher";
    }
    
    @RequestMapping("/student")
    public String student(Model model) {
    	model.addAttribute("username", "Username");
    	model.addAttribute("name","Acu Raul");
    	model.addAttribute("age", 21);
    	model.addAttribute("mode", appMode);
    	
    	return "student";
    }

}
