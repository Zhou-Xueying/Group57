package org.Hotel.server.rmi;

import java.net.MalformedURLException;
import java.rmi.AlreadyBoundException;
import java.rmi.Naming;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

import org.Hotel.server.dataImpl.HotelDataImpl;
import org.Hotel.server.dataImpl.LogDataImpl;
import org.Hotel.server.dataImpl.MemberDataImpl;
import org.Hotel.server.dataImpl.OrderDataImpl;
import org.Hotel.server.dataImpl.PromotionDataImpl;
import org.Hotel.server.dataImpl.ReservationDataImpl;
import org.Hotel.server.dataImpl.UserDataImpl;
import org.Hotel.common.config.XMLReader;
import org.Hotel.common.dataService.HotelDataService;
import org.Hotel.common.dataService.LogDataService;
import org.Hotel.common.dataService.MemberDataService;
import org.Hotel.common.dataService.OrderDataService;
import org.Hotel.common.dataService.PromotionDataService;
import org.Hotel.common.dataService.ReservationDataService;
import org.Hotel.common.dataService.UserDataService;;

public class RemoteHelper {
	
	private static final int PORT=XMLReader.loadipconfig().getPORT();
	
	/**
	 * 服务器ip地址
	 */
	private static final String IP=XMLReader.loadipconfig().getIP();

	
	public static void init(){

		if(IP==null){
			System.out.println("error IP config! Please set your ip.");
		    System.exit(0);	
		}
		
		try {
			//注册RMI通信服务器端
			System.setProperty("java.rmi.server.hostname",IP);
			//注册通讯端口
			LocateRegistry.createRegistry(PORT);
			
			//创建实现类
			HotelDataService HotelDataImpl=new HotelDataImpl();
			LogDataService LogDataImpl=new LogDataImpl();
			MemberDataService MemberDataImpl=new MemberDataImpl();
			OrderDataService OrderDataImpl=new OrderDataImpl();
			ReservationDataService ReservationDataImpl=new ReservationDataImpl();
			UserDataService UserDataImpl=new UserDataImpl();
			PromotionDataService PromotionDataImpl=new PromotionDataImpl();
	
			//注册通讯路径，把所有的Impl实现类注册到RMI服务器上
			Naming.rebind("rmi://"+IP+":"+PORT+"/HotelDataService", HotelDataImpl);
			Naming.rebind("rmi://"+IP+":"+PORT+"/UserDataService", UserDataImpl);
			Naming.rebind("rmi://"+IP+":"+PORT+"/LogDataService", LogDataImpl);
			Naming.rebind("rmi://"+IP+":"+PORT+"/MemberDataService", MemberDataImpl);
			Naming.rebind("rmi://"+IP+":"+PORT+"/OrderDataService", OrderDataImpl);
			Naming.rebind("rmi://"+IP+":"+PORT+"/ReservationDataService", ReservationDataImpl);
			Naming.rebind("rmi://"+IP+":"+PORT+"/UserDataService", UserDataImpl);
			Naming.rebind("rmi://"+IP+":"+PORT+"/PromotionDataService", PromotionDataImpl);
			
			
			
		} catch (RemoteException e) {
			
			e.printStackTrace();
		} catch (MalformedURLException e) {
			
			e.printStackTrace();
		}

		
}

}
