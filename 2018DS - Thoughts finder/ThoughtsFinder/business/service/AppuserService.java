package acuraulm.ThoughtsFinder.business.service;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import acuraulm.ThoughtsFinder.business.serviceInterface.IAppuserService;
import acuraulm.ThoughtsFinder.business.transfer.AppuserDTO;
import acuraulm.ThoughtsFinder.persistence.entities.Appuser;
import acuraulm.ThoughtsFinder.persistence.repositories.AppuserRepository;

@Service
@Transactional
public class AppuserService implements IAppuserService {
	
	@Autowired
	AppuserRepository appuserRepository;
	@Autowired
	DataTransferService dataTransferService;
	
	@Override
	public void createAppuser(AppuserDTO appuserDTO) {
		System.out.print("Creating Appuser");	
		try{
			Appuser appuser = new Appuser();
			appuser.setName(appuserDTO.getName());
			appuser.setUsername(appuserDTO.getUsername());
			appuser.setPassword(appuserDTO.getPassword());
			appuserRepository.save(appuser);
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
		System.out.print(".....done.\n");
	}

	@Override
	public void updateAppuser(long appuserId, AppuserDTO appuserDTO) {
		System.out.print("Updating Appuser");	
		try{
			Appuser appuser = appuserRepository.getOne(appuserId);
			appuser.setName(appuserDTO.getName());
			appuser.setUsername(appuserDTO.getUsername());
			appuser.setPassword(appuserDTO.getPassword());
			appuserRepository.save(appuser);
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
		System.out.print(".....done.\n");
		
	}

	@Override
	public void deleteAppuserById(long appuserId) {
		System.out.print("Deleting Appuser");	
		try{
			appuserRepository.deleteById(appuserId);
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
		System.out.print(".....done.\n");
	}

	@Override
	public Set<AppuserDTO> findAllAppusers() {
		System.out.print("Finding appusers");
		Set<AppuserDTO> appuserDTOs = new HashSet<AppuserDTO>();
		try{
			for(Appuser appuser:appuserRepository.findAll())
				appuserDTOs.add(dataTransferService.getAppuserDTO(appuser));
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
		System.out.print(".....done.\n");
		return appuserDTOs;
	}

	@Override
	public AppuserDTO findAppUserById(long appuserId) {
		System.out.print("Finding Appuser by id");
		AppuserDTO appuserDTO = new AppuserDTO();
		try{
			appuserDTO = dataTransferService.getAppuserDTO(appuserRepository.getOne(appuserId));
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
		System.out.print(".....done.\n");
		return appuserDTO;
	}

	@Override
	public AppuserDTO verifyLogin(String username, String password) {
		try {
			Appuser appuser = appuserRepository.findByUsername(username);
			if(appuser.getPassword().equals(password))
				return dataTransferService.getAppuserDTO(appuser);
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
		System.out.println("nu am gasit");
		return null;
	}

}
