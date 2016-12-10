package org.Hotel.server.dataImpl;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import org.Hotel.common.dataService.PromotionDataService;
import org.Hotel.common.po.HotelPromotionPO;
import org.Hotel.common.po.WebPromotionPO;
import org.Hotel.server.datahelp.DataFactory;
import org.Hotel.server.datahelp.PromotionDataHelper;
import org.Hotel.server.datahelp.impl.DataFactoryImpl;

public class PromotionDataImpl extends UnicastRemoteObject implements PromotionDataService,Serializable{
	

	private Map<Integer, WebPromotionPO> map_web;
	
	private Map<Integer, HotelPromotionPO> map_hotel;
	
	private PromotionDataHelper webproDataHelper,hotelproDataHelper;
	
	private DataFactory dataFactory;
	
	private static PromotionDataImpl proDataImpl;
	
	public static PromotionDataImpl getInstance() throws RemoteException{
		if(proDataImpl == null){
			proDataImpl = new PromotionDataImpl();
		}
		return proDataImpl;
	}
	
	public PromotionDataImpl()throws RemoteException{
		if(map_web == null){
			dataFactory = new DataFactoryImpl();
			webproDataHelper = dataFactory.getPromotionDataHelper();
			map_web = webproDataHelper.getWebPromotionData();
		}

		if(map_hotel == null){
			dataFactory = new DataFactoryImpl();
			hotelproDataHelper = dataFactory.getPromotionDataHelper();
			map_hotel = hotelproDataHelper.getHotelPromotionData();
		}
	}
	
	public boolean insert(WebPromotionPO po) throws RemoteException{
		if(po!=null){
		webproDataHelper.insertWebPromotionData(po);
		map_web=webproDataHelper.getWebPromotionData();
			return true;
		}
		else
			return false;
	}
	
	public boolean delete(WebPromotionPO po) throws RemoteException{
			map_web.remove(po);
			webproDataHelper.deleteWebPromotionData(po);
			return true;

	}
	
	public boolean insert(HotelPromotionPO po) throws RemoteException{
		if(po!=null){
			hotelproDataHelper.insertHotelPromotionData(po);
			map_hotel=hotelproDataHelper.getHotelPromotionData();
				return true;
			
		}else
		    return false;

	}
	
	public boolean delete(HotelPromotionPO po) throws RemoteException{

		map_hotel.remove(po);
		hotelproDataHelper.deleteHotelPromotionData(po);
		return true;


	}
	
	public boolean update(HotelPromotionPO po) throws RemoteException{

		int id = po.getId();
		if(map_hotel.get(id) != null){
			map_hotel.put(id, po);
			hotelproDataHelper.updateHotelPromotionData(po);
			return true;
		}else
		return false;
	}

	
	public ArrayList<WebPromotionPO> showAllWebPro() throws RemoteException{
		ArrayList<WebPromotionPO> webprolist = new ArrayList<WebPromotionPO>();
		Iterator<Entry<Integer, WebPromotionPO>> iterator = map_web.entrySet().iterator();
		while(iterator.hasNext()){
			Entry<Integer, WebPromotionPO> entry = iterator.next();
			WebPromotionPO webpropo = entry.getValue();
			webprolist.add(webpropo);
	}
		return webprolist;
	}
	
	public ArrayList<HotelPromotionPO> findByHotelProID(String hotel_id) throws RemoteException{
		ArrayList<HotelPromotionPO> hotelprolist = new ArrayList<HotelPromotionPO>();
		Iterator<Entry<Integer, HotelPromotionPO>> iterator = map_hotel.entrySet().iterator();
		while(iterator.hasNext()){
			Entry<Integer, HotelPromotionPO> entry = iterator.next();
			HotelPromotionPO hotelpropo = entry.getValue();
			if(hotel_id.equals(hotelpropo.getHotelid()))
				hotelprolist.add(hotelpropo);
		}
		return hotelprolist;
	}
	
	public HotelPromotionPO findByHotelProType(String type, String hotel_id) throws RemoteException{
		HotelPromotionPO hotelpropo = new HotelPromotionPO();
		Iterator<Entry<Integer, HotelPromotionPO>> iterator = map_hotel.entrySet().iterator();
		while(iterator.hasNext()){
			Entry<Integer, HotelPromotionPO> entry = iterator.next();
			
		    hotelpropo = entry.getValue();
			if(type.equals(hotelpropo.getType())&&hotel_id.equals(hotelpropo.getHotelid()))
            break;
		}
		return hotelpropo;
		}
	
	public ArrayList<HotelPromotionPO> showAllHotelPro() throws RemoteException{
		ArrayList<HotelPromotionPO> hotelprolist = new ArrayList<HotelPromotionPO>();
		Iterator<Entry<Integer, HotelPromotionPO>> iterator = map_hotel.entrySet().iterator();
		while(iterator.hasNext()){
			Entry<Integer, HotelPromotionPO> entry = iterator.next();
			HotelPromotionPO hotelpropo = entry.getValue();
			hotelprolist.add(hotelpropo);
	}
		return hotelprolist;
	}
//	public static void main(String[] args) throws RemoteException{
//		PromotionDataImpl data=PromotionDataImpl.getInstance();
//		HotelPromotionPO po2=new HotelPromotionPO("生日折扣", "南行酒店",
//				0.2,0, 
//				0,0,null,null);
//		data.update(po2);
//	}
	
}
