package org.Hotel.common.po;

public class WebStaffPO {

	private int id;
	
	private String name;
	
	private String password;
	
	public WebStaffPO() {
		super();
	}
	
	public WebStaffPO(int id, String password) {
		super();
		
		this.id = id;
		this.password = password;
	}
	public WebStaffPO(String name,String password){
		
	}
	public int getId() {
		// TODO Auto-generated method stub
		return id;
	}

	public void setId(int id){
		
		this.id = id;
	}
	
	public String getPassword() {
			
		return password;
		
	}

	public void setPassword(String password) {
			
		this.password = password;
		
	}
	
	public void setName(String name){
		
		this.name = name;
	}
	
	public String getName() {
			
		return name;
		
	}

}
