package consoleBasedAtm;

import java.util.Scanner;

class Admin {
	
	 static Scanner sc = new Scanner(System.in);
			 Account ref1 = new Account();
			 
			ATMMain atmref;
			 Admin(ATMMain atmref){
				 this.atmref=atmref;
			 }
			 
	private static final String ADMIN_PIN = "1234";
	public void AdminLogin()  {

		System.out.println(ATMMain.BLUE+"ENTER ADMIN PIN"+ATMMain.RESET);
		String enterPin = sc.next();
	
	
			try {
				login(enterPin);
			} catch (CredentialsException e) {
				
				e.printStackTrace();
			}
		
		
		}
	public void login(String enteredPin) throws CredentialsException  {
	    if(ADMIN_PIN.equals(enteredPin)) {
	    	System.out.println(ATMMain.GREEN+"ADMIN LOGIN  SUCCESSFULLY DONE"+ATMMain.RESET);
	    	System.out.println(ATMMain.BLUE+"1.VIEW ALL ACCOUNTS"+ATMMain.RESET);
	    	System.out.println(ATMMain.BLUE+"2.DELETE ACCOUNT"+ATMMain.RESET);
	    	System.out.println(ATMMain.BLUE+"3.EXIT"+ATMMain.RESET);
	    	
	          int option = sc.nextInt();
	          
	          switch(option) {
	          
	          case 1:
	        	  try {
	        		  
	        		  viewAllAccounts();
	        		  login(enteredPin);
	        	  }
	        	  catch(NoUserFound e) {
	        		  e.printStackTrace();
	        	  }
	        	  break;
	          case 2:
	        	  try {
	        		  
	        		  deleteAccount();
	        		  login(enteredPin);
	        		  break;
	        	  }
	        	  catch(NoUserFound e){
	        		  e.printStackTrace();
	        	  }
	          case 3:
	        	  System.out.println(ATMMain.GREEN+"ADMIN LOG-OUT SUCCESFULLY DONE "+ATMMain.RESET);
	        	  return;
	        	
	         default:
	        	  System.out.println(ATMMain.RED+"INVALID"+ATMMain.RESET);
	          
	          }
	    	
	    }
	    
	    else {
	         throw new CredentialsException("Credentials Mis-Match");
	    }
	}
    
    public void viewAllAccounts() throws NoUserFound {
        // Logic to display all user accounts
            
    	if(ATMMain.index>0) {
    		
    		for(int i=0;i<ATMMain.index;i++) {
    			System.out.println(ATMMain.GREEN+"ACCOUNT HOLDER NAME: " + ATMMain.users[i].getusername()+ATMMain.RESET);
    			System.out.println(ATMMain.GREEN+"ACCOUNT HOLDER LOCATION: " + ATMMain.users[i].getlocation()+ATMMain.RESET);
    			System.out.println(ATMMain.GREEN+"ACCOUNT HOLDER ACCOUNT NUMBER: " + ATMMain.users[i].getaccountNumber()+ATMMain.RESET);
    			System.out.println(ATMMain.GREEN+"ACCOUNT HOLDER BALANCE: " + ATMMain.users[i].getbalance()+ATMMain.RESET);
    			System.out.println(ATMMain.GREEN+"NEXT USER"+ATMMain.RESET);
    			System.out.println();
    		}    
    		}
    	else {
    		throw new NoUserFound("NO USER STORED");
    	}
    	}

    public void deleteAccount() throws NoUserFound {   //String accountNumber
        // Logic to remove an account
    	System.out.println(ATMMain.BLUE+"ENTER THE ACCOUNT NUMBER TO DELETE"+ ATMMain.RESET);
        String accountNumber = sc.next();
        
        boolean isFound = true;
        
        for(int i=0;i<ATMMain.index;i++) {
        	if(accountNumber.equals(ATMMain.users[i].getaccountNumber())) {
        		isFound=false;
        		
        		for(int j =i;j<ATMMain.index-1;j++) {
        			ATMMain.users[j]= ATMMain.users[j+1];
        		}
        		
        		ATMMain.users[ATMMain.index-1]=null;
        		ATMMain.index--;
        		System.out.println(ATMMain.GREEN+"DELETED SUCESSFULLY"+ATMMain.RESET);
        		
        		break;
        	}
        }
        if(isFound) {
        	throw new NoUserFound("USER DOESN'T EXITS");
        }
        
    }
}
