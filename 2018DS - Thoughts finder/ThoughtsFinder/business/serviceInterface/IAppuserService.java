package acuraulm.ThoughtsFinder.business.serviceInterface;

import java.util.Set;

import acuraulm.ThoughtsFinder.business.transfer.AppuserDTO;

public interface IAppuserService {
	
	void createAppuser(AppuserDTO appuserDTO);
	void updateAppuser(long appuserId, AppuserDTO appuserDTO);
	void deleteAppuserById(long appuserId);
	Set<AppuserDTO> findAllAppusers();
	AppuserDTO findAppUserById(long appuserId);
	
	AppuserDTO verifyLogin(String username, String password);
	
	
}
