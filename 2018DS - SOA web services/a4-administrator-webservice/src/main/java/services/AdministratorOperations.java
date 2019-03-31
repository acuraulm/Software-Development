package services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.jws.WebService;

import org.hibernate.SessionFactory;

import connection.HibernateAnnotationUtil;
import dao.AppuserDAO;
import dao.PackageDAO;
import dao.RouteDAO;
import dao.StatusDAO;
import entities.Appuser;
import entities.Package;
import entities.Route;
import entities.Status;
import serviceInterfaces.IAdministratorOperations;
import transfer.RouteDTO;
import transfer.StatusDTO;


@SuppressWarnings("restriction")
@WebService(endpointInterface="serviceInterfaces.IAdministratorOperations")
public class AdministratorOperations implements IAdministratorOperations {
	
	SessionFactory session = new HibernateAnnotationUtil().getSessionFactory();
	AppuserDAO appuserDAO = new AppuserDAO(session);
	PackageDAO packageDAO = new PackageDAO(session);
	RouteDAO routeDAO = new RouteDAO(session);
	StatusDAO statusDAO = new StatusDAO(session);

	
	public int addPackage(Package packagee) {
		try{
			packageDAO.addPackage(packagee);
			return 1;
		}catch(Exception e){
			e.printStackTrace();
		}
		return 0;
	}

	public int removePackage(int packageId) {
		try{
			packageDAO.removePackage(packageId);
			return 1;
		}catch(Exception e){
			e.printStackTrace();
		}
		return 0;
	}

	public int trackPackage(int packageId) {
		try{
			Package packagee = packageDAO.findPackagebyId(packageId);
			packageDAO.registerPackage(packagee.getId());
			
			Status status = new Status();
			status.setPackageId(packagee.getId());
			
			List<Route> routes = new ArrayList<Route>();
			
			Route route = new Route();
			route.setCity(packagee.getSenderCity());
			route.setTime(new Date());
			routes.add(route);
			status.setRoutes(routes);
			route.setStatus(status);
			
			routeDAO.addRoute(route);
			statusDAO.addStatus(status);
		}catch(Exception e){
			e.printStackTrace();
		}
		return 0;
	}

public Package[] findAllPackages() {
		
		try{
			List<Package> packagesList = packageDAO.findAllPackages();
			Package[] packages = new Package[packagesList.size()];
		
			for(int i=0; i<packagesList.size();i++){
				packages[i] = packagesList.get(i);
			}
			return packages;
		}catch(Exception e){
			e.printStackTrace();
		}
		return new Package[1];
	}

	public int addStatus(int packageId){
		try{
			Status status = new Status();
			status.setPackageId(packageId);
			status.setRoutes(new ArrayList<Route>());
			statusDAO.addStatus(status);
			return 1;
		}catch(Exception e){
			e.printStackTrace();
		}
		return 0;
	}
	public int addRoute(RouteDTO routeDTO) {
		try{
			Route route = new Route();
			route.setId(routeDTO.getId());
			route.setCity(routeDTO.getCity());
			route.setTime(routeDTO.getTime());
			System.out.println("@@@@@@@@ addRoute ADMINOP: status_id: " + routeDTO.getStatus_id());
			route.setStatus(statusDAO.findStatusById(routeDTO.getStatus_id()));
			System.out.println("@@@@@@@@ status: " + statusDAO.findStatusById(routeDTO.getStatus_id()));	
			
			routeDAO.addRoute(route);
			return 1;
		}catch(Exception e){
			e.printStackTrace();
		}
		return 0;
	}

	public Appuser getAppuserById(int id) {
		try{
			return appuserDAO.findAppuserById(id);
		}catch(Exception e){
			e.printStackTrace();
		}
		return new Appuser();
	}

	public Appuser getAppuserByName(String name) {
		try{
			return appuserDAO.findAppuserByName(name);
		}catch(Exception e){
			e.printStackTrace();
		}
		return new Appuser();
	}
	
	public RouteDTO[] getRoutesByStatusId(int statusId){
		try{
			List<Route> routes = routeDAO.findRoutesByStatusId(statusId);
			RouteDTO[] routeDTOs = new RouteDTO[routes.size()];
		
			for(int i=0; i<routes.size();i++){
				routeDTOs[i] = new RouteDTO();
				routeDTOs[i].setCity(routes.get(i).getCity());
				routeDTOs[i].setStatus_id(routes.get(i).getStatus().getId());
				routeDTOs[i].setTime(routes.get(i).getTime());
			}
			return routeDTOs;
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return new RouteDTO[0];
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
