package serviceInterfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;

import entities.Car;

public interface ITaxService extends Remote{

	double computeTax(Car car) throws RemoteException;
}
