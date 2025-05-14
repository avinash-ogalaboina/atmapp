package consoleBasedAtm;

import java.util.Scanner;

public class Account {
	  private String username;
	    private String location;
	    private String accountNumber;
	    private String pin;
	    private double balance;
	    
	    Scanner sc = new Scanner(System.in);
	   
	    Account(){
	    	
	    }
	    
	    Account (String username, String location, String accountNumber,String pin , double balance) throws InvalidAmount {
	     this.username= username;
	      this.location = location;
	      setaccountNumber(accountNumber);
	      this.pin=pin;
	      setbalance(balance);
	    	
	    }
	    
	    public String   getusername() {
	    	return username;
	    }
	    
	    public void setusername(String username) {
	    	this.username=username;
	    }
	    
	    public String getlocation() {
	    	return location;
	    }
	    
	  
	    
	    public String getaccountNumber() {
	    	return accountNumber;
	    }
	    
	    public void setaccountNumber(String accountNumber) {

	    		this.accountNumber=accountNumber;
	    	}
	    	
	    		
//	    }
	    public String getpin() {
	    	return pin;
	    }
	    
	    public void setpin(String pin) {
	    	this.pin=pin;
	    	
	    }
	    public double getbalance() {
	    	return balance;
	    }
	    
	    public  void setbalance(double balance) throws InvalidAmount {
	    	if(balance>=100) {
				
				this.balance = balance;
			}
			else {
			     throw new InvalidAmount ("AMOUNT SHOULD BE GREATER THAN 100");
			
			}
	    }
	    
	    
	    
	    
	    
	    

}
