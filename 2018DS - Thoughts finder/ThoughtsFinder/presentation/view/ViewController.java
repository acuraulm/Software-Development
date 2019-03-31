package acuraulm.ThoughtsFinder.presentation.view;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.jsondoc.core.annotation.ApiMethod;
import org.jsondoc.core.annotation.ApiPathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import acuraulm.ThoughtsFinder.business.transfer.AppuserDTO;
import acuraulm.ThoughtsFinder.business.transfer.CommentDTO;

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
      //  AppuserDTO appuserDTO = new AppuserDTO();
       // appuserDTO.setId(1);
       // appuserDTO.setName("Acu Raul-Mihai");
      //  model.addAttribute("appuser", appuserDTO);
        return "index";
    }
    
    @RequestMapping("/login")
    public String login(Model model) {
    	model.addAttribute("mode", appMode);
    	return "pages/login";
    }
    
    @RequestMapping("/profile")
    public String profile(Model model) {
    	model.addAttribute("mode", appMode);
    	return "pages/profile";
    }
    
    @RequestMapping("/thought")
    public String thought(Model model) {
    	model.addAttribute("mode", appMode);
    	    	
    	return "pages/thought";
    }

}
