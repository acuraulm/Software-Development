package acuraulm.ThoughtsFinder.presentation.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Set;

import org.jsondoc.core.annotation.Api;
import org.jsondoc.core.annotation.ApiMethod;
import org.jsondoc.core.annotation.ApiPathParam;
import org.jsondoc.core.pojo.ApiStage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import acuraulm.ThoughtsFinder.business.service.CommentService;
import acuraulm.ThoughtsFinder.business.service.ThoughtService;
import acuraulm.ThoughtsFinder.business.transfer.CommentDTO;
import acuraulm.ThoughtsFinder.business.transfer.ThoughtDTO;

@RestController
@RequestMapping(value = "/thought")
@Api(
        name = "Thoughts API",
        description = "Provides a list of thoughts",
        stage = ApiStage.RC)
public class ThoughtController {

	@Autowired
	ThoughtService thoughtService;
	@Autowired
	CommentService commentService;


    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ApiMethod(description = "Filter thoughts by section")
    public ThoughtDTO getThoughtById(@ApiPathParam(name = "id") @PathVariable long id){
    	System.out.println(id);
       return thoughtService.findThoughtById(id);
    }
    
    @RequestMapping(value = "/{id}/comments", method = RequestMethod.GET)
    @ApiMethod(description = "Filter thoughts by section")
    public List<CommentDTO> getComments(@ApiPathParam(name = "id") @PathVariable long id){
    	System.out.println(id);
    	Set<CommentDTO> comments = commentService.findAllCommentsByThoughtId(id);
    	
    	List<CommentDTO> list = new ArrayList<CommentDTO>(comments);
		System.out.println("comments");
    	Collections.sort(list,new Comparator<CommentDTO>() {
			@Override
			public int compare(CommentDTO t1, CommentDTO t2) {
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
