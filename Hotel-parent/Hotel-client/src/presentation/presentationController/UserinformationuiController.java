package presentation.presentationController;

import java.net.URL;
import java.rmi.RemoteException;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.ResourceBundle;

import BusinessLogicService.Service.MemberLogicService;
import Controller.MemberActController;
import Controller.ReservationController;
import Helper.UiswitchHelper;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.util.StringConverter;
import presentation.userui.AlertBox;
import vo.MemberVO;

public class UserinformationuiController implements Initializable{
	 private MemberVO membervo;
	 private String membername,phonenumber;
	 private LocalDate birthday;
	 private MemberLogicService memberService;
     @FXML
     private Label MembercharacterLabel,MemberlevelLabel,MembercreditvalueLabel,DiscountLabel;
     //             会员性质              会员等级          会员信用值               会员折扣
     @FXML
     private TextField MembernameText,PhonenumberText;
     //                 会员名          手机号码（联系方式）
     @FXML
     private DatePicker BirthdayDatepicker;
     //                 会员生日
    
     
     @FXML
     private void SaveButtonClicked(ActionEvent event) throws ParseException{
    	 if(!Allisfilled()){
    		 AlertBox alt = new AlertBox();
				alt.display("请务必将用户名和联系方式填写完全");
    	 }else{
    		 membername=MembernameText.getText().toString();
    		 phonenumber=PhonenumberText.getText().toString();
    		 birthday=BirthdayDatepicker.getValue();
    		
    		 MemberActController.getmemberVo().updateInfo(membername, birthday, phonenumber);
    		 try {
				if(memberService.updateMemberinfo(membervo)){
					 AlertBox alt = new AlertBox();
						alt.display("保存成功");
				 }else{
					 AlertBox alt = new AlertBox();
						alt.display("保存失败，请重试");
				 }
				
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    		 
    	 }
     }
     
     @FXML
     private void ReturnButtonClicked(ActionEvent event){
    	 UiswitchHelper.getApplication().goto_Usermainui();
     }
     
     private boolean Allisfilled(){
    	 if(MembernameText.getText().isEmpty()||PhonenumberText.getText().isEmpty()){
    		 return false;
    	 }
    	 return true;
     }
     
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		membervo=ReservationController.getMembervo();
		MembernameText.setText(membervo.getname());
		
		MembercharacterLabel.setText(membervo.getproperty());
		MemberlevelLabel.setText(membervo.getlevel());
		MembercreditvalueLabel.setText(String.valueOf(membervo.getcredit()));
		System.out.println(membervo.getcredit());
		PhonenumberText.setText(membervo.getphonenumber());
		
		if(membervo.getBirthday()!=null){
		 DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		 String birth=membervo.Birthday;
		 LocalDate date=LocalDate.parse(birth,dtf);
		
		BirthdayDatepicker.setValue(date);}
		if(membervo.getproperty().equals("企业会员")){
			
		}
	}

}
