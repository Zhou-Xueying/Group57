package BusinessLogicService.impl;

import java.rmi.RemoteException;
import java.util.ArrayList;

import org.Hotel.common.dataService.HotelDataService;
import org.Hotel.common.dataService.MemberDataService;
import org.Hotel.common.dataService.OrderDataService;
import org.Hotel.common.dataService.PromotionDataService;
import org.Hotel.common.po.OrderPO;

import BusinessLogicService.Service.OrderLogicService;
import Controller.MemberActController;
import vo.OrderVO;

public class OrderLogicServiceImpl implements OrderLogicService {
     OrderDataService ser= (OrderDataService)RMIHelper.find("OrderDataService");
   
     
     //评价信息的更新
	@Override
	public boolean evaluate(String OrderId, String score, String EvaluateInfo) {		
		// TODO Auto-generated method stub
		boolean tem = false;
	  try {
		OrderVO vo =new OrderVO();  
		OrderPO po =  ser.orderShowAll(Integer.parseInt(OrderId));
		System.out.println(po.getStarttime());
		vo.SetbyOrderPO(po);
		vo.setComment(EvaluateInfo);
		vo.setGrade(score);		
		po=vo.toOrderPO(vo);
		System.out.println(po.getStarttime());
		 tem =ser.update(po);
	} catch (RemoteException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}		
		return tem;
	}

	
	//订单的生成
	@Override
	public boolean input(OrderVO VO) {
		// TODO Auto-generated method stub
		boolean tem = false;
		  try {
			 tem =ser.insert(MemberActController.getOrdervo().toOrderPO( MemberActController.getOrdervo()));
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
			return tem;
		}
		
	
	

//撤销订单
	@Override
	public boolean revokeUserOrder(String UserName, String OrderId) {
		// TODO Auto-generated method stub
		OrderVO temp =null;
		temp.setName(UserName);
		temp.setId(OrderId);
		boolean bool = false;
		try {
			 bool = ser.delete(temp.toOrderPO(temp));
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	
		return bool;
	}
	
	
	//更改订单状态
	@Override
	public boolean changeOrderStation(String OrderId,String OrderStation) {
		boolean tem = false;
		MemberActController.getOrdervo().setState(OrderStation);
		  try {
			  tem =ser.update(MemberActController.getOrdervo().toOrderPO( MemberActController.getOrdervo()));
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
			return tem;
		}
	//查找用户订单
	@Override
	public ArrayList<OrderVO> findUserOrderListAll(String UserName) {
		// TODO Auto-generated method stub
		ArrayList<OrderPO> a = new ArrayList<OrderPO>();
		ArrayList<OrderVO> tem = new ArrayList<OrderVO>();
		try {
			a=ser.orderShowAllByName(UserName);
		
			for(int i=0;i<a.size();i++){
				OrderVO temp=new OrderVO(UserName, UserName, UserName, UserName, UserName, UserName, UserName, UserName, UserName, UserName, UserName, UserName, UserName, UserName, UserName, UserName, UserName, UserName) ;
		//		System.out.println(a.get(i).getName());
				temp.SetbyOrderPO(a.get(i));
				
				tem.add(temp);
		//		System.out.println(tem.get(i).getName());
			}
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return tem;
	}

	
	//根据订单状态查找订单
	@Override
	public ArrayList<OrderVO> findUserOrderListStation(String UserName, String OrderStation) {
		// TODO Auto-generated method stub
		ArrayList<OrderPO> a = new ArrayList<OrderPO>();
		ArrayList<OrderVO> tem = new ArrayList<OrderVO>();
		try {
			a=ser.orderShowAllByName(UserName, OrderStation);
			for(int i=0;i<a.size();i++){
				OrderVO temp=new OrderVO(UserName, UserName, UserName, UserName, UserName, UserName, UserName, UserName, UserName, UserName, UserName, UserName, UserName, UserName, UserName, UserName, UserName, UserName) ;
		//		System.out.println(a.get(i).getName());
				temp.SetbyOrderPO(a.get(i));
				
				tem.add(temp);
		//		System.out.println(tem.get(i).getName());
			}
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		return tem;

}

//根据酒店名称查找订单
	@Override
	public ArrayList<OrderVO> findUserOrderListHotelandName(String UserName, String HotelName) {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
				ArrayList<OrderPO> a = new ArrayList<OrderPO>();
				ArrayList<OrderVO> tem = new ArrayList<OrderVO>();
				try {
					a=ser.orderShowAllByHotel(HotelName,UserName);
					for(int i=0;i<a.size();i++){
						OrderVO temp=new OrderVO(UserName, UserName, UserName, UserName, UserName, UserName, UserName, UserName, UserName, UserName, UserName, UserName, UserName, UserName, UserName, UserName, UserName, UserName) ;
				//		System.out.println(a.get(i).getName());
						temp.SetbyOrderPO(a.get(i));
						
						tem.add(temp);
				//		System.out.println(tem.get(i).getName());
					}
				} catch (RemoteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				return tem;

	}


	


	@Override
	public ArrayList<OrderVO> findUserOrderListHotel(String Hotel) {
		// TODO Auto-generated method stub
		ArrayList<OrderPO> a = new ArrayList<OrderPO>();
		ArrayList<OrderVO> tem = new ArrayList<OrderVO>();
		try {
			a=ser.orderShowAllByHotel(Hotel);
		
			for(int i=0;i<a.size();i++){
				OrderVO temp=new OrderVO() ;
		//		System.out.println(a.get(i).getName());
				temp.SetbyOrderPO(a.get(i));
				
				tem.add(temp);
		//		System.out.println(tem.get(i).getName());
			}
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return tem;
	}

	@Override
	public ArrayList<OrderVO> showALl() {
		// TODO Auto-generated method stub
		ArrayList<OrderPO> a = new ArrayList<OrderPO>();
		ArrayList<OrderVO> tem = new ArrayList<OrderVO>();
		try {
			a=ser.showAll();
		
			for(int i=0;i<a.size();i++){
				OrderVO temp=new OrderVO() ;
		//		System.out.println(a.get(i).getName());
				temp.SetbyOrderPO(a.get(i));
				
				tem.add(temp);
		//		System.out.println(tem.get(i).getName());
			}
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return tem;
	}
}
