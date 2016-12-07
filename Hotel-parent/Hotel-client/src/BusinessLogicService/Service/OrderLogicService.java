package BusinessLogicService.Service;

import java.util.ArrayList;

import vo.OrderVO;

public interface OrderLogicService {
	public boolean evaluate(String string,String score ,String EvaluateInfo); //评价订单信息的保存。
    public boolean input(OrderVO VO);//订单信息的保存
    public ArrayList<OrderVO> findUserOrderListAll(String UserName);//通过用户名查找所有该用户的订单
    public ArrayList<OrderVO> findUserOrderListStation(String UserName,String OrderStation);//通过用户名和订单状态查找订单   
    public boolean revokeUserOrder(String UserName,String OrderId);//撤销订单并扣除信用值
    public boolean changeOrderStation(String OrderId,String OrderStation);//更改订单状态
    public ArrayList<OrderVO> findUserOrderListHotel(String UserName,String OrderStation);//通过用户名和酒店查找订单   
    
    
}