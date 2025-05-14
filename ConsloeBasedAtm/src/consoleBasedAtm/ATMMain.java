package consoleBasedAtm;

import java.util.Scanner;

public class ATMMain {
	
	     static Account users [] = new Account [1000];
	      static int index =0;
	        Scanner sc = new Scanner(System.in);
	        
	        public static final String RESET = "\u001B[0m";
	        public static final String RED = "\u001B[31m";
	        public static final String GREEN = "\u001B[32m";
	        public static final String YELLOW = "\u001B[33m";
	        public static final String BLUE = "\033[94m";

	        
	       public boolean isDuplicateAccount(String accNum) {
	           for (int i = 0; i < index; i++) {
	               if (users[i].getaccountNumber().equals(accNum)) {
	                   return true;
	               }
	           }
	           return false;
	       }
	       public boolean isDuplicatePin(String pin) {
	           for (int i = 0; i < index; i++) {
	               if (users[i].getpin().equals(pin)) {
	                   return true;
	               }
	           }
	           return false;
	       }
	        
	        //creates a user
	        public void CreateUsers()  throws Duplicate, InvalidAmount  {
	        	
	        	System.out.println(BLUE+"ENTER THE USER NAME"+RESET);
	        	String username = sc.next();
	        	
	        	System.out.println(BLUE+"ENTER THE USER LOCATION");
	        	
	        	String location = sc.next();
	        	
	        	
	        	 String accountNumber;
	        	 System.out.println(BLUE +"ENTER THE USER ACCOUNT NUMBER:"+RESET);
	        	 accountNumber = sc.next();
	        
	             while (true) {
	                 if (isDuplicateAccount(accountNumber)) {
	                	 throw new Duplicate ("ACCOUNT NUMBER ALREADY EXISTS TRY WITH ANOTHER ACCOUNT NUMBER");
	                 } else {
	                     break;
	                 }
	             }
	             																																																																																																																																																																																									
	        	String pin;
	        	System.out.println(BLUE+"ENTER THE USER PIN"+RESET);
	        	pin = sc.next();
	        	
	        	while (true) {
	                 if (isDuplicatePin(pin)) {
	                	 throw new Duplicate ("USER PIN ALREADY EXISTS TRY WITH ANOTHER PIN");
	                 } else {
	                     break;
	                 }
	             }
	        	
	        	System.out.println(BLUE+"ENTER THE USER BALANCE"+RESET);
	        	double balance = sc.nextDouble();
	        	
	        	Account account = new Account( username ,location,accountNumber,pin,balance);
	        	users[index++] = account;	        	
	        	
	        	System.out.println(GREEN+"USER CREATED SUCCESSFULLY"+RESET);
	        	
	        }
	    
	        
	public static void main(String[] args)  {
		// TODO Auto-generated method stub
		 Scanner sc = new Scanner(System.in);
		ATMMain get = new ATMMain();
		Admin ref1 = new Admin(get);
		AccountLogin accountlogin = new AccountLogin();
		
		
		
		
		while(true) {
			
			System.out.println(BLUE+"SELECT THE OPTIONS"+RESET);
			
			System.out.println(BLUE+"1.ADD USERS"+RESET);
			System.out.println(BLUE+"2.ADMIN LOGIN"+RESET);
			System.out.println(BLUE+"3.ACCOUNT LOGIN"+RESET);
			System.out.println(BLUE+"4.EXIT"+RESET);
			
		
			
			int option = sc.nextInt();
			
			switch(option) {
			
			case 1:
				   try{
					   get.CreateUsers();
					   
				   }
				   catch(Duplicate   | InvalidAmount e) {
					   e.printStackTrace();
				   }
				   break;
				
			case 2:
				
			      ref1.AdminLogin();
			      break;
			case 3:
				accountlogin.accountlogin();
				break;
				
			case 4:
				System.out.println(GREEN+"THANK YOU FOR USING OUR ATM"+RESET);
				return;
				
				
		    default :
				System.out.println(RED+"INVAID ENTRY"+RESET);
				
				
				
			}
			
			
		    	
		    
		}

	}

}
