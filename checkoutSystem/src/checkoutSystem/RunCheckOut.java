package checkoutSystem;

/**
 * @author Peter.Wong
 * @version 1.0 - solution for exercise, step 1: Shopping Cart
 * 
 * Main program to initialise different shopping lists to test the class for 
 * adding shopping lists in a checkout system.
 *
 */
public class RunCheckOut {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//set up different in out strings to test good and bad results
		String[] shoppingLists= {
		"orange, apple, ORange, ApplE, ORANGE",
		"orange, apple, ORange, ApplE, ORANGE, banana",
		"",
		"orange, apple, ORange, ApplE,, apple",
		"orange, apple, ORange, ApplE, ORANGE,apple, apple,apple"};
		
		CheckOut calc = new CheckOut();
		double total;
		
		for ( int i = 0; i < shoppingLists.length; i++) {
			total = calc.addItemListPrices(shoppingLists[i]);
			
		   	System.out.printf("List of items " + shoppingLists[i] +  "\n" );
		  	System.out.printf("Number of items %d \n" , calc.getNbrItemsInList());
		  	System.out.printf("Total Amount: £%.2f \n" , total);
		  	System.out.println("---------------------------------------------------");
		  	System.out.println();
		
		}
		
	}

}
