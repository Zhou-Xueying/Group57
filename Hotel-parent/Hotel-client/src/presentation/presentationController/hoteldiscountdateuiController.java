package presentation.presentationController;

import java.net.URL;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

import BusinessLogicService.Service.PromotionLogicService;
import BusinessLogicService.impl.PromotionLogicServiceImpl;
import Controller.HotelmanageController;
import Helper.UiswitchHelper;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import presentation.userui.AlertBox;
import vo.HotelPromotionVO;

public class hoteldiscountdateuiController implements Initializable{
	
	@FXML
	private Button button_birthday;
	
	@FXML
	private Button button_businesspartner;
	
	@FXML
	private Button button_roomnumber;
	
	@FXML
	private TextField hoteldiscountdatediscount;
	
	@FXML
	private TableView hoteldiscountdateTable;
	
	@FXML
	private TableColumn hoteldiscountdateTable_start;
	
	@FXML
	private TableColumn hoteldiscountdateTable_end;
	
	@FXML
	private TableColumn hoteldiscountdateTable_discount;
	
	@FXML
	private Button delete;
	
	@FXML
	private DatePicker TimeBegin,TimeEnd;

	@FXML
	private TextField newDiscount;
	
	@FXML
	private Button confirm;
	
	@FXML
	private Button back;
	
	String hotel_name = HotelmanageController.getHotelVO().getName();
	String name = "日期折扣";
	
	@FXML
	public void toBusinesspartner(ActionEvent event){
		UiswitchHelper.getApplication().goto_businesspartnerui();
	}
	
	@FXML
	public void toRoomnumber(ActionEvent event){
		UiswitchHelper.getApplication().goto_roomnumberui();
	}
	
	@FXML
	public void toBirthday(ActionEvent event){
		UiswitchHelper.getApplication().goto_birthdayui();
	}
	
	@FXML
	public void delete(ActionEvent event){

	}
	
	@FXML
	public void add(ActionEvent event){
	
		String start = TimeBegin.getValue().toString();
		String end = TimeEnd.getValue().toString();
		
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		
		Date start_date = null;
		Date end_date = null;
		try {
			start_date = sdf.parse(start);
			end_date = sdf.parse(end);
		} catch (ParseException e) {
			AlertBox alt = new AlertBox();
			alt.display("请指定日期！");
		}
		
		String input = newDiscount.getText();
		
		if(input!=null){
			
			double discount = Double.parseDouble(input);
			
			if(discount<0||discount>100){
				AlertBox alt = new AlertBox();
				alt.display("超出输入范围！");
			}else if(discount==0){
				AlertBox alt = new AlertBox();
				alt.display("不可为0！");
			}else{
		
				HotelPromotionVO vo = new HotelPromotionVO(hotel_name, name, 0, 0, 0, discount/100, start_date, end_date);
				
				PromotionLogicService promotion = new PromotionLogicServiceImpl();
				promotion.addHotelPromotion(vo);
				
				this.showTable(name,hotel_name);
			
			}
		}
	}
	
	@FXML
	public void toBack(ActionEvent event){
		UiswitchHelper.getApplication().goto_HotelMainui();
	}

	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		this.showTable(name,hotel_name);
	
	}
	
	public void showTable(String name,String hotel_name){
		
		PromotionLogicService promotion = new PromotionLogicServiceImpl();
		ArrayList<HotelPromotionVO> proList= promotion.getHotelPromotionList(name,hotel_name);
		
		final ObservableList<DateInfo> data = FXCollections.observableArrayList();
		
		for(int i=0;i<proList.size();i++){
			
			HotelPromotionVO vo = proList.get(i);
			String start = vo.getStartDate().toString();
			String end = vo.getEndDate().toString();
			double discount = vo.getDateDiscount();;
			
			DateInfo info = new DateInfo(start,end,discount);
			
			data.add(info);
        }
		
		hoteldiscountdateTable_start.setCellValueFactory( new PropertyValueFactory<>("start") );
		hoteldiscountdateTable_end.setCellValueFactory( new PropertyValueFactory<>("end") );
		hoteldiscountdateTable_discount.setCellValueFactory( new PropertyValueFactory<>("discount") );
		
		hoteldiscountdateTable.setItems(data);
		
	}
	
	
	public static class DateInfo{
		
		private final SimpleStringProperty start;
        private final SimpleStringProperty end;
        private final SimpleStringProperty discount;
        
        private DateInfo(String start, String end, double discount){
        	this.start = new SimpleStringProperty(start);
        	this.end = new SimpleStringProperty(end);
        	this.discount = new SimpleStringProperty(String.valueOf(discount));
        }
        
        public void setStart(String start){
        	this.start.set(start);
        }
        
        public void setEnd(String end){
        	this.end.set(end);
        }
        
        public void setDiscount(double discount){
        	this.discount.set(String.valueOf(discount));
        }
        
        public String getStart(){
        	return start.get();
        }
        
        public String getEnd(){
        	return end.get();
        }
        
        public double getDiscount(){
        	return Double.parseDouble(discount.get());
        }
	}


}


