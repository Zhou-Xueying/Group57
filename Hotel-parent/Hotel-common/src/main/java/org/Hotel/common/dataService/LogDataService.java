package org.Hotel.common.dataService;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

import org.Hotel.common.po.LogPO;

public interface LogDataService extends Remote{
	public boolean insert(LogPO po) throws RemoteException;
	
	public boolean delete(LogPO po) throws RemoteException;
}
