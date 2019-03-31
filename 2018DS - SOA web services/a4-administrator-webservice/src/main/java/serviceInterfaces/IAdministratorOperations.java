package serviceInterfaces;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;

import entities.Appuser;
import entities.Package;
import transfer.RouteDTO;
import transfer.StatusDTO;
@SuppressWarnings("restriction")
@WebService
@SOAPBinding(style=Style.RPC)
public interface IAdministratorOperations {
	
	@WebMethod
	public Appuser getAppuserById(int id);
	@WebMethod
	public Appuser getAppuserByName(String name);
	@WebMethod
	public int addPackage(Package packagee);
	@WebMethod
	public int removePackage(int packageId);
	@WebMethod
	public int trackPackage(int packageId);
	@WebMethod
	public Package[] findAllPackages();
	@WebMethod
	public int addRoute(RouteDTO routeDTO);
	@WebMethod
	public int addStatus(int packageId);
	@WebMethod
	public StatusDTO checkPackage(int packageId);

}
