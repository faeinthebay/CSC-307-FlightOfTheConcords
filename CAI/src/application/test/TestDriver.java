package application.test;

public class TestDriver {

	public static void main(String args[]) {
		
		AccountsTest accountsTest = new AccountsTest();
		
		try {
			accountsTest.testAddAccount();
			accountsTest.testCheckAccount();
			accountsTest.testCheckUserPrivilege();
			accountsTest.testFlights();
		}
		catch(Exception e) {
			System.out.println("Exception occurred in AccountsTest");
		}
	}
	
}
