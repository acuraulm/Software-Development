package services;

import javax.jws.WebService;

import org.hibernate.SessionFactory;

import connection.HibernateAnnotationUtil;
import dao.AppuserDAO;
import entities.Appuser;
import serviceInterfaces.ILoginAndRegister;

@WebService(endpointInterface="serviceInterfaces.ILoginAndRegister")
public class LoginAndRegister implements ILoginAndRegister {
	SessionFactory factory = new HibernateAnnotationUtil().getSessionFactory();	
	AppuserDAO appuserDAO = new AppuserDAO(factory);
	
	
	public boolean register(String username, String password) {
		try{
			Appuser appuser = new Appuser();
			appuser.setPassword(password);
			appuser.setUsername(username);
			appuser.setIsAdmin(false);
			appuserDAO.addAppuser(appuser);
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
		
		return true;
	}

	public Appuser login(String username, String password) {
		Appuser appuser = new Appuser();
		try{
			appuser = appuserDAO.findAppuserByUsernameAndPassword(username, password);
		}catch(Exception e){
			e.printStackTrace();
		}
		return appuser;
	}

}
