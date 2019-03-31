package acuraulm.ThoughtsFinder.presentation.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.jsondoc.core.annotation.Api;
import org.jsondoc.core.annotation.ApiMethod;
import org.jsondoc.core.annotation.ApiPathParam;
import org.jsondoc.core.pojo.ApiStage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import acuraulm.ThoughtsFinder.business.service.AppuserService;
import acuraulm.ThoughtsFinder.business.service.SectionService;
import acuraulm.ThoughtsFinder.business.service.ThoughtService;
import acuraulm.ThoughtsFinder.business.transfer.AppuserDTO;
import acuraulm.ThoughtsFinder.business.transfer.SectionDTO;
import acuraulm.ThoughtsFinder.business.transfer.ThoughtDTO;

@RestController
@RequestMapping(value = "/section")
@Api(
        name = "Thoughts API",
        description = "Provides a list of thoughts",
        stage = ApiStage.RC)
public class SectionController {

	@Autowired
	SectionService sectionService;
	@Autowired
	ThoughtService thoughtService;
	@Autowired
	AppuserService appuserService;
	
	@RequestMapping(value = "/appuser/{id}", method = RequestMethod.GET)
    @ApiMethod(description = "Get appuser")
    public AppuserDTO getAppuser(@ApiPathParam(name = "id") @PathVariable long id){
		AppuserDTO appuser = null;
		try {
			appuser = appuserService.findAppUserById(id);
		}
		catch(Exception ex){
			ex.printStackTrace();			
		}
    	return appuser;
    }
	
    @SuppressWarnings("unchecked")
	@RequestMapping(value = "/all", method = RequestMethod.GET)
    @ApiMethod(description = "Get all thoughts from the database")
    public Set<SectionDTO> getAll(){
    	Set<SectionDTO> sections = sectionService.findAllSections();
    	
    	return new TreeSet(sections);
    }
    

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ApiMethod(description = "Filter thoughts by section")
    public List<ThoughtDTO> getThoughtsBySectionId(@ApiPathParam(name = "id") @PathVariable long id){
    	System.out.println(id);
       return orderThoughts(thoughtService.findAllThoughtsBySectionId(id));
    }
    
	@RequestMapping(value = "/thoughts", method = RequestMethod.GET)
    @ApiMethod(description = "Get all thoughts from the database")
    public List<ThoughtDTO> getAllThoughts(){
		Set<ThoughtDTO> thoughts = thoughtService.findAllThoughts();
		
    	return orderThoughts(thoughts);
    }
    
	@RequestMapping(value = "/search/{content}", method = RequestMethod.GET)
    @ApiMethod(description = "Searches for specific content of thoughts")
    public List<ThoughtDTO> getThoughtsByContent(@ApiPathParam(name = "content") @PathVariable String content){
    	Set<ThoughtDTO> thoughts = thoughtService.findAllThoughtsByContent(content);
    	System.out.println(content);
    	return new ArrayList<ThoughtDTO>(thoughts);
    }
	
	@RequestMapping(value = "/orderbydate", method = RequestMethod.POST)
    @ApiMethod(description = "Orders the thoughts by date")
    public List<ThoughtDTO> orderThoughtsByDate(@RequestBody List<ThoughtDTO> thoughts){

		Collections.sort(thoughts,new Comparator<ThoughtDTO>() {
			@Override
			public int compare(ThoughtDTO t1, ThoughtDTO t2) {
				if(t1.getDate().before(t2.getDate()))
					return 1;
				else if(t1.getDate().after(t2.getDate()))
					return -1;
				else 
					return 0;
			}
		});
    	
    	return thoughts;
    }
	
	@RequestMapping(value = "/orderbyfollowers", method = RequestMethod.POST)
    @ApiMethod(description = "Orders the thoughts by the number of followers")
    public List<ThoughtDTO> orderThoughtsByFollowers(@RequestBody List<ThoughtDTO> thoughts){
		
    	Collections.sort(thoughts,new Comparator<ThoughtDTO>() {
			@Override
			public int compare(ThoughtDTO t1, ThoughtDTO t2) {
				if(t1.getFollowersIds().size() - t2.getFollowersIds().size() < 0)
					return 1;
				else if(t1.getFollowersIds().size() - t2.getFollowersIds().size() > 0)
					return -1;
				else 
					return 0;
			}
		});
    	
    	return thoughts;
    }
	
	private List<ThoughtDTO> orderThoughts(Set<ThoughtDTO> thoughts){
		List<ThoughtDTO> list = new ArrayList<ThoughtDTO>(thoughts);
		System.out.println("orderbydate");
    	Collections.sort(list,new Comparator<ThoughtDTO>() {
			@Override
			public int compare(ThoughtDTO t1, ThoughtDTO t2) {
				if(t1.getDate().before(t2.getDate()))
					return 1;
				else if(t1.getDate().after(t2.getDate()))
					return -1;
				else 
					return 0;
			}
		});
    	
    	return list;
	}
	
	
}
