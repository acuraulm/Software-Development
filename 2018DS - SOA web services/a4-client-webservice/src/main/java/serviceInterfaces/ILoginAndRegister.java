package serviceInterfaces;

import javax.jws.WebMethod;  
import javax.jws.WebService;  
import javax.jws.soap.SOAPBinding;  
import javax.jws.soap.SOAPBinding.Style;

import entities.Appuser;  

@SuppressWarnings("restriction")
@WebService
@SOAPBinding(style=Style.RPC)
public interface ILoginAndRegister {

	@WebMethod
	public boolean register(String username, String password);
	@WebMethod
	public Appuser login(String username, String password);
}
