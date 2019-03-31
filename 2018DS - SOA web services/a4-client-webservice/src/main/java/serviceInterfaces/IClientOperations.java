package serviceInterfaces;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;

import entities.Package;
import transfer.StatusDTO;
@SuppressWarnings("restriction")
@WebService
@SOAPBinding(style=Style.RPC)
public interface IClientOperations {
	
	@WebMethod
	public Package[] findOwnPackages(int senderId);
	@WebMethod
	public Package[] searchRelatedPackages(int senderId, String name);
	@WebMethod
	public StatusDTO checkPackage(int packageId);

}
