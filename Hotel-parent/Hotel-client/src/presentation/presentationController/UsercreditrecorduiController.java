package presentation.presentationController;

import java.net.URL;
import java.rmi.RemoteException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.ResourceBundle;

import BusinessLogicService.Service.MemberLogicService;
import BusinessLogicService.Service.ReservationLogicService;
import BusinessLogicService.impl.MemberLogicServiceImpl;
import BusinessLogicService.impl.ReservationLogicServiceImpl;
import vo.CreditRecordVO;
import javafx.collections.ObservableList;
import Controller.MemberActController;
import Helper.UiswitchHelper;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;



public class UsercreditrecorduiController implements Initializable{
	private ObservableList<CreditRecord> finallist;
	private ReservationLogicService reservationService=new ReservationLogicServiceImpl();
	private MemberLogicService memberService=new MemberLogicServiceImpl();
	private ArrayList<CreditRecordVO> creditlist0=new ArrayList<CreditRecordVO>();
  @FXML
  private TableView<CreditRecord> CreditrecordTable;
  
  @FXML
  private TableColumn<CreditRecord,String> timecol,orderid,action,creditchange,creditlast;
  
  
  
  @FXML
  private void ReturnButtonClicked(ActionEvent event){
	 
	  UiswitchHelper.getApplication().goto_Usermainui();
  }
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		try {
			creditlist0=memberService.FindCreditbyname(LoginController.UserName);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//ArrayList<CreditRecordVO> creditlist=MemberActController.getCreditRecordVOList();
		
		ArrayList<CreditRecord> creditlist2=new ArrayList<CreditRecord>();
		for(int i=0;i<creditlist0.size();i++){
			creditlist2.add(new CreditRecord(creditlist0.get(i).action,creditlist0.get(i).orderid
					,creditlist0.get(i).time,creditlist0.get(i).creditchange,creditlist0.get(i).creditlast));
		}
		 finallist =FXCollections.observableArrayList(creditlist2);
		 timecol.setCellValueFactory(
		            new PropertyValueFactory<>("time"));
		 
			timecol.setCellFactory(TextFieldTableCell.<CreditRecord>forTableColumn());
			timecol.setOnEditCommit(
		            (CellEditEvent<CreditRecord, String> t) -> {
		                ((CreditRecord) t.getTableView().getItems().get(
		                        t.getTablePosition().getRow())
		                        ).setTime(t.getNewValue());
		        });

			
			orderid.setCellValueFactory(
			            new PropertyValueFactory<>("ordernumber"));
			 
				orderid.setCellFactory(TextFieldTableCell.<CreditRecord>forTableColumn());
				orderid.setOnEditCommit(
			            (CellEditEvent<CreditRecord, String> t) -> {
			                ((CreditRecord) t.getTableView().getItems().get(
			                        t.getTablePosition().getRow())
			                        ).setOrdernumber(t.getNewValue());
			        });
		// show action info
		action.setCellValueFactory(
	            new PropertyValueFactory<>("action"));
	 
		action.setCellFactory(TextFieldTableCell.<CreditRecord>forTableColumn());
		action.setOnEditCommit(
	            (CellEditEvent<CreditRecord, String> t) -> {
	                ((CreditRecord) t.getTableView().getItems().get(
	                        t.getTablePosition().getRow())
	                        ).setAction(t.getNewValue());
	        });
		// show creditchange info 
		creditchange.setCellValueFactory(
	            new PropertyValueFactory<>("creditchange"));
	 
		creditchange.setCellFactory(TextFieldTableCell.<CreditRecord>forTableColumn());
		creditchange.setOnEditCommit(
	            (CellEditEvent<CreditRecord, String> t) -> {
	                ((CreditRecord) t.getTableView().getItems().get(
	                        t.getTablePosition().getRow())
	                        ).setCreditchange(t.getNewValue());
	        });
		// show creditlast info 
				creditlast.setCellValueFactory(
			            new PropertyValueFactory<>("creditlast"));
			 
				creditlast.setCellFactory(TextFieldTableCell.<CreditRecord>forTableColumn());
				creditlast.setOnEditCommit(
			            (CellEditEvent<CreditRecord, String> t) -> {
			                ((CreditRecord) t.getTableView().getItems().get(
			                        t.getTablePosition().getRow())
			                        ).setCreditlast(t.getNewValue());
			        });
				
				
				CreditrecordTable.setItems(finallist);
				
	}
	
	
	
	
	
	public static class CreditRecord {
		 
        private final SimpleStringProperty action;
        
   
        private final SimpleStringProperty creditchange;
        private final SimpleStringProperty creditlast;
       
        private final SimpleStringProperty ordernumber;
        private final SimpleStringProperty time;

       
 
        private CreditRecord(String Action,String Orderid,String Time,String Creditchange,String Creditlast){
        	  this.action = new SimpleStringProperty(Action);
        	  
        	  this.creditchange = new SimpleStringProperty(String.valueOf(Creditchange));
        	  this.creditlast = new SimpleStringProperty(Creditlast);
              this.ordernumber=new SimpleStringProperty(Orderid);
              this.time=new SimpleStringProperty(Time);
        	
        }  
 
        public void setAction(String Action) {
			
        	this.action.set(Action);
		}

		public void setCreditchange(String Creditchange) {
			
			creditchange.set(Creditchange);
		}

		
		public void setCreditlast(String Creditlast) {
			
			creditlast.set(Creditlast);
		}
        
		public void setTime(String Time){
			time.set(Time);
		}
		
		public void setOrdernumber(String orderid){
			ordernumber.set(orderid);
		}
		
		public String getOrdernumber(){
			return ordernumber.get();
		}
		public String getTime(){
			return time.get();
		}


		public String getAction() {
            return action.get();
        }
 
     
	

		public String getCreditchange() {
			return creditchange.get();
		}

		public String getCreditlast() {
			return creditlast.get();
		}

	
    
  }
}

