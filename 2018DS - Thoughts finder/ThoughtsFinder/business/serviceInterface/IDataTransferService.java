package acuraulm.ThoughtsFinder.business.serviceInterface;

import acuraulm.ThoughtsFinder.business.transfer.AppuserDTO;
import acuraulm.ThoughtsFinder.business.transfer.CommentDTO;
import acuraulm.ThoughtsFinder.business.transfer.SectionDTO;
import acuraulm.ThoughtsFinder.business.transfer.ThoughtDTO;
import acuraulm.ThoughtsFinder.persistence.entities.Appuser;
import acuraulm.ThoughtsFinder.persistence.entities.Comment;
import acuraulm.ThoughtsFinder.persistence.entities.Section;
import acuraulm.ThoughtsFinder.persistence.entities.Thought;

public interface IDataTransferService {

	public AppuserDTO getAppuserDTO(Appuser appuser);
	public ThoughtDTO getThoughtDTO(Thought thought);
	public CommentDTO getCommentDTO(Comment comment);
	public SectionDTO getSectionDTO(Section section);
}
