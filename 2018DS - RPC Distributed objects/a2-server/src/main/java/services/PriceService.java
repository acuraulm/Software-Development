package services;

import java.rmi.RemoteException;

import entities.Car;
import serviceInterfaces.IPriceService;

public class PriceService implements IPriceService{

	public double computePrice(Car car) throws RemoteException{
		// TODO Auto-generated method stub
		System.out.println("Remote price service call.");
		if(2018 - car.getYear() < 7)
			return car.getPrice() - (car.getPrice()/7)*(2018 - car.getYear());
		else
			return 0;
			
	}

}
