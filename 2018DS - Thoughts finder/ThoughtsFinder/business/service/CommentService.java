package acuraulm.ThoughtsFinder.business.service;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import acuraulm.ThoughtsFinder.business.serviceInterface.ICommentService;
import acuraulm.ThoughtsFinder.business.transfer.CommentDTO;
import acuraulm.ThoughtsFinder.persistence.entities.Comment;
import acuraulm.ThoughtsFinder.persistence.repositories.AppuserRepository;
import acuraulm.ThoughtsFinder.persistence.repositories.CommentRepository;
import acuraulm.ThoughtsFinder.persistence.repositories.ThoughtRepository;

@Service
@Transactional
public class CommentService implements ICommentService {

	@Autowired
	CommentRepository commentRepository;
	@Autowired
	AppuserRepository appuserRepository;
	@Autowired
	ThoughtRepository thoughtRepository;
	@Autowired
	DataTransferService dataTransferService;
	@Autowired
	ThoughtService thoughtService;
/*	@Override
	public void createComment(CommentDTO commentDTO) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateComment(CommentDTO commentDTO) {
		// TODO Auto-generated method stub
		
	}
*/
	@Override
	public void deleteCommentById(long commentId) {
		System.out.print("Deleting Appuser");	
		try{
			commentRepository.deleteById(commentId);
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
		System.out.print(".....done.\n");	
	}

	@Override
	public Set<CommentDTO> findAllComments() {
		System.out.print("Finding comments by thoughtId");
		Set<CommentDTO> commentsDTOs = new HashSet<CommentDTO>();
		try{	
			for(Comment comment:commentRepository.findAll())
				commentsDTOs.add(dataTransferService.getCommentDTO(comment));
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
		System.out.print(".....done.\n");
		return commentsDTOs;
	}

	@Override
	public CommentDTO findCommentById(long commentId) {
		System.out.print("Finding comment by id");
		CommentDTO commentDTO = new CommentDTO();
		try{
			commentDTO = dataTransferService.getCommentDTO(commentRepository.getOne(commentId));
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
		System.out.print(".....done.\n");
		return commentDTO;
	}
	
	@Override
	public Set<CommentDTO> findAllCommentsByThoughtId(long thoughtId) {
		System.out.print("Finding comments by thoughtId");
		Set<CommentDTO> commentsDTOs = new HashSet<CommentDTO>();
		try{	
			for(Comment comment:commentRepository.findAll())
				if(comment.getThought().getId() == thoughtId)
					commentsDTOs.add(dataTransferService.getCommentDTO(comment));
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
		System.out.print(".....done.\n");
		return commentsDTOs;
	}

	@Override
	public void postComment(CommentDTO commentDTO) {
		System.out.print("Posting comment");
		try{
			Comment comment = new Comment();
			comment.setAppuser(appuserRepository.getOne(commentDTO.getAppuserId()));
			comment.setThought(thoughtRepository.getOne(commentDTO.getThoughtId()));
			comment.setContent(commentDTO.getContent());
			comment.setDate(commentDTO.getDate());
			
			thoughtService.followThought(commentDTO.getAppuserId(), commentDTO.getThoughtId());
			commentRepository.save(comment);	
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
		System.out.print(".....done.\n");		
	}
	
	@Override
	public void editComment(long commentId, CommentDTO commentDTO) {
		System.out.print("Editing comment");
		try{
			Comment comment = commentRepository.getOne(commentId);
			comment.setAppuser(appuserRepository.getOne(commentDTO.getAppuserId()));
			comment.setThought(thoughtRepository.getOne(commentDTO.getThoughtId()));
			comment.setContent(commentDTO.getContent());
			comment.setDate(commentDTO.getDate());
			commentRepository.save(comment);
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
		System.out.print(".....done.\n");		
		
	}


}
