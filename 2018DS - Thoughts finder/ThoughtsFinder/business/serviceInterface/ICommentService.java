package acuraulm.ThoughtsFinder.business.serviceInterface;

import java.util.Set;

import acuraulm.ThoughtsFinder.business.transfer.CommentDTO;


public interface ICommentService {

	//void createComment(CommentDTO commentDTO);
	//void updateComment(long commentId, CommentDTO commentDTO);
	void deleteCommentById(long commentId);
	Set<CommentDTO> findAllComments();
	CommentDTO findCommentById(long commentId);
	
	Set<CommentDTO> findAllCommentsByThoughtId(long thoughtId);
	void postComment(CommentDTO commentDTO);
	void editComment(long commentId, CommentDTO commentDTO);
	
}
