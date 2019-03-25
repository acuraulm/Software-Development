package services;

import java.rmi.RemoteException;

import entities.Car;
import serviceInterfaces.ITaxService;

public class TaxService implements ITaxService{

	public double computeTax(Car car) throws RemoteException{
		System.out.println("Remote tax service call.");
		double sum;
		double eng =  car.getEngineSize();
		
		if (eng >= 3001)
			sum = 290.00;
		else if (eng <= 3000 && eng > 2600)
			sum = 144.00;
		else if (eng <= 2600 && eng > 2000)
			sum = 72.00;
		else if (eng <= 2000 && eng > 1600)
			sum = 18.00;
		else 
			sum = 8.00;
		
		return (eng/200)*sum;
	}

}
