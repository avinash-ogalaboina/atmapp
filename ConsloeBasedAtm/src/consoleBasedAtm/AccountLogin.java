package consoleBasedAtm;

import java.util.Scanner;

public class AccountLogin {
	
	String accountnumber;
	
	ATMMain accountLogin=new ATMMain();
	Account ref1 = new Account();
	int indexval ;
  
	
	Scanner sc = new Scanner(System.in);
	
	     public void accountlogin() {
	    	 System.out.println(ATMMain.BLUE+"ENTER USER ACCOUNT NUMBER:"+ATMMain.RESET);
	    	 accountnumber =  sc.next();
	    	 System.out.println(ATMMain.BLUE+"ENTER USER PIN:"+ATMMain.RESET);
	    	String pin = sc.next();
	    	try {
				perform(accountnumber, pin);
			} catch (CredentialsException e) {
				e.printStackTrace();
			} catch (NoUserFound e) {
				e.printStackTrace();
			}
	     }
	     public void perform( String accountnumber,String pin) throws CredentialsException,  NoUserFound  {
	    	if(ATMMain.index>0) {
	    	  if(authenticateUser(accountnumber , pin)) {
	    	  System.out.println(ATMMain.GREEN+"ACCOUNT lOGIN SUCESSFULLY DONE"+ATMMain.RESET);
	    	  
	    	  System.out.println(ATMMain.BLUE+"1.Withdraw Money"+ATMMain.RESET);
	    	  System.out.println(ATMMain.BLUE+"2.Deposit Money"+ATMMain.RESET);
	    	  System.out.println(ATMMain.BLUE+"3.Check Account Balance"+ATMMain.RESET);
	    	  System.out.println(ATMMain.BLUE+"4.EXIt"+ATMMain.RESET);
	    	  
	    	  int option = sc.nextInt();	    	  
	    	  switch(option) {
	    	  case 1:
	    		  try {
	    			  
	    			  withdraw();
	    			  perform(accountnumber, pin);
	    		
	    		  }
	    		  catch(InvalidAmount e) {
	    			  e.printStackTrace();
	    		  }
	    		  break;
	    		
	    	  case 2:
                    try {
            	    deposit();
            	    perform(accountnumber, pin);
            	  
            	   
                   }catch(InvalidAmount e) {
	    			  e.printStackTrace();
	    		     }
                     break;
	
	    	  case 3:
	    		  checkBalance();
	    		  perform(accountnumber, pin);
	    		   break;
	    	  case 4 :
	    		  System.out.println("USER LOG-OUT SUCCESSFULL DONE");
	    		  return;
	    		  
	    	default :
	    		System.out.println(ATMMain.RED+"INVALID OPERATION"+ATMMain.RESET);
	    	  
	    	  }
	    	  
	    		
	    	}
	    	
	    	else{
	    	   throw new CredentialsException("USER ID OR PASSWORD MISS-MATCH ");
	    	}
	    	}
	    	else {
	    		throw new NoUserFound("NO USER STORED");
	    	}
	    	
	     }
	
	public boolean authenticateUser(String accountNumber, String pin) {
		boolean isEqual = false;
		for(int i =0;i<ATMMain.index;i++) {
			if(ATMMain.users[i].getaccountNumber().equals(accountNumber)&& ATMMain.users[i].getpin().equals(pin)) {
			    indexval = i; 
				isEqual = true;
			}
		}
		if(isEqual) {
			
			return true;
		}
		
		return false;
  
	}

    public void withdraw() throws InvalidAmount {
        // Validate withdrawal condition
    			 System.out.println(ATMMain.BLUE+"ENTER THE AMOUNT TO WITH-DRAW"+ATMMain.RESET);
    			double amount = sc.nextDouble();
    			if(amount%100==0 && amount<=ATMMain.users[indexval].getbalance() && amount>0 ) {
    				
    				double balance = ATMMain.users[indexval].getbalance() - amount ;
    				ATMMain.users[indexval].setbalance(balance);
    				System.out.println(ATMMain.GREEN+"WITH-DRAWAL DONE"+ATMMain.RESET);
    			}
    			else {
    				if(amount>=ATMMain.users[indexval].getbalance())
    				  throw new InvalidAmount("INSUFFICENT FUNDS");
    				      
    				else {
    					throw new InvalidAmount("AMOUNT SHOULD BE IN MULTIPLE OF 100 ");
    					
    				}
    				
    			}
    			
    	          
    		}
    	
    public void deposit() throws InvalidAmount  {
        // Validate deposit conditions
    
    			 System.out.println(ATMMain.BLUE+"ENTER THE AMOUNT TO DEPOSITE "+ATMMain.RESET);
    			double amount = sc.nextDouble();
    			if(amount>=100 && amount%100==0) {
    				
    				double balance = ATMMain.users[indexval].getbalance() + amount ;
    				ATMMain.users[indexval].setbalance(balance);
    				System.out.println(ATMMain.GREEN+"DEPOSITE  DONE"+ATMMain.RESET);
    			}
    			else {
    				if(amount%100 !=0 || amount <0) {
    					
    					throw new InvalidAmount("AMOUNT SHOULD BE IN POSTIVE AND MUTLPLE OF 100 TO DEPOSITE");
    				
    				
    			}
    	           	       	          
    		}
    	}
    	

//
    public void checkBalance() {
	
    			
       System.out.println(ATMMain.GREEN+"CURRENT ACCOUNT BALANCE "+ATMMain.users[indexval].getbalance()+ATMMain.RESET);

    	
    	
    	
    }

}
