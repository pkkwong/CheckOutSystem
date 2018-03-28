package checkoutSystem;

/**
 * @author Peter.Wong
 * @version 1.0 - solution for exercise, step 1: Shopping Cart
 *          2.0 - solution for step 2
 *          
 * 
 * A class to define a simple check out system where only 2 items are valid to be sold, orange and apple.
 * The price for each item is hard coded for this exercise to keep it quick and simple.
 *
 */
public class CheckOut {
	
	private static String[] saleItemName = {"apple", "orange"}; //all item names in lowercase
	private static double[] saleItemPrice = {0.60, 0.25};
	private static int[] saleItemCount = {0, 0};
	private static String csvDelimeter = ",";
	private static String[] fields;
	private static int nbrItemsInList;
	private static boolean specialOffer = false;

	
	/**
	 * Adds up the total of each valid item in the shopping list shopList.
	 * returns a total 0.0 if there is an error in the shopList.
	 * 
	 * @param shopList - input, list of items in shopping list with csv delimiters. 
	 * @return total - the total amount of the items in shopList.
	 */
	public  double addItemListPrices( String shopList) {
		double total = 0.0;
		
		if ( validateItemList(shopList) != 0 ) {
			System.out.printf("Invalid shopping lists");
			return total;
		}
		
		for (int i=0; i < saleItemName.length; i++) {
			saleItemCount[i] = 0;
		}
		
		for ( String itemName : fields) {
			for ( int i = 0; i < saleItemName.length; i++) {
				String s = itemName.toLowerCase().trim();
				if (s.equals(saleItemName[i])) {
					total = total + saleItemPrice[i];
					saleItemCount[i] ++;
				}
			}
		}
		//if special offer is on then reprice the total to pay.
		if (getSpecialOffer()) {
			total = applySpecialOffers();
		}
		
		return total;
	}
	
	/**
	 * Validates the list of items in shopList to ensure all items are either orange or apple
	 * Any thing else the shopList is rejected.
	 * 
	 * @param shopList - input, list of items in shopping list with csv delimiters. 
	 * @return result 0=ok,  -1= error
	 */
	public static int validateItemList( String shopList) {
		int result = 0;
		fields = shopList.split(csvDelimeter);
		setNbrItemsInList(fields.length);
		
		if ( nbrItemsInList == 0 ||
				fields[0].isEmpty()) {
			System.out.printf("Empty shopping lists");
			result=-1;
			setNbrItemsInList(0);
		}

		for ( String itemName : fields) {
			boolean Match = false;
			for ( int i = 0; i < saleItemName.length; i++) {
				// convert all names to lowercase to make name check case insensitive
				String s = itemName.toLowerCase().trim();
				if (s.equals(saleItemName[i])){
					Match = true; //only need to find one match to set it to true			
				}

			}
				if (!Match) { 
					System.out.printf("Invalid item [" + itemName + "]in shopping list \n");
					result=-1;
				}
		}

		return result;
	}
	/**
	 * @return total amount after applying the special offers.
	 * 
	 * we can create different methods for different offers.
	 */
	public double applySpecialOffers(){
		double total = 0.0;
		//special offer apples buy one get one free, oranges 3 for 2.
		
		total = buyOneGetOneFreeOffer(saleItemCount[0], saleItemPrice[0]);
		total = total + threeForTwofOffer(saleItemCount[1], saleItemPrice[1]);	
		return total;
	}
	/**
	 * Making these methods so that we can apply the offers on any item
	 * @param count - input the number of the same items
	 * @param price - inout price for one item
	 * @return total amount for buy one get one free 
	 */
	public double buyOneGetOneFreeOffer( int count, double price){
		double total = 0.0;
		int i = (count/2) + count % 2;
		total = i * price;
		return total;	
	}
		
	
	/**
	 * Making these methods so that we can apply the offers on any item
	 * @param count - input the number of the same items
	 * @param pricep - input price for one item
	 * @return total amount for 3 for the price of 2
	 */
	public double threeForTwofOffer( int count, double price){
		double total = 0.0;
		int i = (count/3);
		total = i * 2 * price; // each 3 items is priced as 2 items
		
		total = total + (count % 3) * price; //add price for remainder of items
		return total;	
	}
		
	/**
	 * @param n int - the number of items in shopping list to set
	 */
	public static void setNbrItemsInList( int n) {
		nbrItemsInList = n;
	}
	
	/**
	 * @return an int -  the number of items in shopping list
	 */
	public int getNbrItemsInList() {
		return nbrItemsInList;
		
	}
	
	/**
	 * @param b boolean - T or F input to set the flag specialOffer
	 */
	public static void setSpecialOffer( boolean b) {
		specialOffer = b;
	}
	
	/**
	 * @return boolean - T or F for specialOffer is on or off
	 */
	public boolean getSpecialOffer() {
		return specialOffer;
		
	}
	
}
