package services;

import java.util.List;

import javax.jws.WebService;
import javax.transaction.Transactional;

import org.hibernate.SessionFactory;

import connection.HibernateAnnotationUtil;
import dao.PackageDAO;
import dao.StatusDAO;
import entities.Package;
import entities.Route;
import entities.Status;
import serviceInterfaces.IClientOperations;
import transfer.RouteDTO;
import transfer.StatusDTO;

@SuppressWarnings("restriction")
@WebService(endpointInterface="serviceInterfaces.IClientOperations")
public class ClientOperations implements IClientOperations{
	SessionFactory factory = new HibernateAnnotationUtil().getSessionFactory();
	PackageDAO packageDAO = new PackageDAO(factory);
	StatusDAO statusDAO = new StatusDAO(factory);
	
	
	public Package[] findOwnPackages(int senderId) {
		
		try{
			
			List<Package> packagesList = packageDAO.findPackagesBySenderId(senderId);
			Package[] packages = new Package[packagesList.size()];
			
			System.out.println("ClientOperations.list(" + senderId + ")");
			for(int i=0; i<packagesList.size();i++){
				packages[i] = packagesList.get(i);
				System.out.println(packages[i].toString());
			}
			
			return packages;
		}catch(Exception e){
			e.printStackTrace();
		}
		return new Package[1];
	}

	public Package[] searchRelatedPackages(int senderId, String name) {
		try{
			List<Package> packagesList = packageDAO.findPackagesBySenderIdAndName(senderId, name);
			Package[] packages = new Package[packagesList.size()];
			
			System.out.println("ClientOperations.list(" + senderId + ", " + name + ")");
			for(int i=0; i<packagesList.size();i++){
				packages[i] = packagesList.get(i);
				System.out.println(packages[i].toString());
			}
			
			return packages;
		}catch(Exception e){
			e.printStackTrace();
		}
		return new Package[1];
	}
	
	public StatusDTO checkPackage(int packageId) {
		
		try{
			Status status = statusDAO.findStatusByPackageId(packageId);
			if(status == null)
				return new StatusDTO();
			
			StatusDTO statusDTO = new StatusDTO();
			
			statusDTO.setId(status.getId());
			statusDTO.setPackageId(status.getPackageId());
			statusDTO.setRoutes(new RouteDTO[0]);
			
			List<Route> routes = status.getRoutes();
			if(routes == null || routes.isEmpty())
				return statusDTO;
			
			RouteDTO[] routesDTO = new RouteDTO[routes.size()];
			
			for(int i=0; i<routes.size();i++){
				routesDTO[i] = new RouteDTO();
				routesDTO[i].setId(routes.get(i).getId());
				routesDTO[i].setCity(routes.get(i).getCity());
				routesDTO[i].setStatus_id(routes.get(i).getStatus().getId());
				routesDTO[i].setTime(routes.get(i).getTime());
			}
			statusDTO.setRoutes(routesDTO);
			
			return statusDTO;
		}catch(Exception e){
			e.printStackTrace();
		}
		return new StatusDTO();
	}

}
