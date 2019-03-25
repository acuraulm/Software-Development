package server;

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

import serviceInterfaces.IPriceService;
import serviceInterfaces.ITaxService;
import services.PriceService;
import services.TaxService;

public class Server {

	public static void main(String[] args){
		
		IPriceService priceStub = new PriceService();
		ITaxService taxStub = new TaxService();
		
		try {
			priceStub = (IPriceService) UnicastRemoteObject.exportObject(new PriceService(), 0);
			taxStub = (ITaxService) UnicastRemoteObject.exportObject(new TaxService(), 0);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			
			System.out.println("Failed casting remote objects");
			e.printStackTrace();
		}
			try {
				Registry registry = LocateRegistry.createRegistry(1099);
				registry.bind("priceService", priceStub);
				registry.bind("taxService", taxStub);
				
				
				System.out.println("Server started");
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				
				System.out.println("Failed creating registry");
				e.printStackTrace();
			} catch (AlreadyBoundException e) {
				// TODO Auto-generated catch block
				System.out.println("Failed to bind stubs");
				e.printStackTrace();
			}
		
	}
}
