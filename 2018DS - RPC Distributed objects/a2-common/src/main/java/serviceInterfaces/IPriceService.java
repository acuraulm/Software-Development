package serviceInterfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;

import entities.Car;

public interface IPriceService extends Remote{

	double computePrice(Car car) throws RemoteException;
}
