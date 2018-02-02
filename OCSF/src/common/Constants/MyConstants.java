package common.Constants;

public class MyConstants 
{
	private MyConstants()
	{
		
	}
	//---------------------------------------------------------------------------------------------//

	public static final int CLIENT_FRAME_START_X = 100;
	public static final int CLIENT_FRAME_START_Y = 50;
	public static final int CLIENT_FRAME_START_WIDTH = 1000;
	public static final int CLIENT_FRAME_START_HEIGHT = 700;
	
	public static final int PANEL_WIDTH = 500;
	public static final int PANEL_HEIGHT = 350;
	
	public static final float SUBSCRIPTION_2_DISCOUNT = 4;
	
	//---------------------------------------------------------------------------------------------//
	
	
	////// 'What to do' messages //////////
	public static final String LOGIN ="Login";
	public static final String LOGIN_SUCCESS="Login succeed";
	public static final String LOGIN_FAIL_PASS_WRONG = "Login failed: password is wrong";
	public static final String LOGIN_FAIL_USER_NOT_EXIST  = "Login failed: user doesn't exist";
	public static final String USER_ALREADY_LOGGED  = "You have already logged in";
	public static final String USER_NOT_FOUND = "User was not found";
	public static final String CHECK_USER_EXIST = "CheckIfUserAndPasswordExist";
	public static final String CHECK_USER_EXIST_FOR_REGISTER = "CheckIfUserEXIST";
	public static final String Registration_OK = "Check if ID exist for Registration: OK";
	public static final String Registration_Fail = "Check if ID exist for Registration: NOT OK";
	public static final String REGISTRATION_COMPLETED = "Registration has completed successfully";
	public static final String REGISTRATION_NOT_COMPLETED = "Registration hasn't completed successfully";
	public static final String UPDATING_PRICE_AFTER_CONFIRMATION ="Update new price after chain manager confirmation";
	public static final String UPDATING_PRICE_OK = "Update is good after confirmation of chain manager";
	public static final String CHECK_CAR_EXIST_FOR_REGISTER = "Check if Car already exist in DB";
	public static final String CAR_EXIST = "Car already exist in DB";
	public static final String CAR_NOT_EXIST = "Car doesn't exist in DB";
	public static final String INSERT_SALE = "Insert sale to DB";
	public static final String INSERT_SALE_OK ="Sale was inserted into DB Successfully";
	public static final String GET_STATIONS = "Get gas stations from DB";
	public static final String NO_STATIONS = "There are no gas stations yet";
	public static final String FOUND_STATIONS = "There are gas stations found in DB";
	public static final String GET_CARS = "Get cars from DB";
	public static final String NO_CARS = "There are no cars";
	public static final String FOUND_CARS = "There are cars found in DB";
	public static final String GET_PRODUCTS = "Get products from DB";
	public static final String NO_PRODUCTS = "There are no products yet";
	public static final String FOUND_PRODUCTS = "There are products found in DB";
	public static final String GET_SALES = "Get sales from DB";
	public static final String NO_SALES = "There are no sales yet";
	public static final String FOUND_SALES = "There are sales found in DB";
	public static final String SET_ACTIVE_SALE = "Marketing Manager initiates sale";
	public static final String SET_ACTIVE_SALE_OK = "Sale was initiated successfully";
	public static final String UPDATE_LOW_LEVEL = "Update low level of product";
	public static final String LOW_LEVEL_UPDATED = "Low level of product was updated";
	public static final String UPDATE_INVENTORY = "Update inventory of product in gas station";
	public static final String INVENTORY_UPDATED = "Inventory of product was updated";
	public static final String INVENTORY_UPDATE_FAIL = "ERROR: one of the selected data was not found in DB";
	public static final String DOMESTIC_FUEL_ORDER = "Make Order Of Domestic Fuel";
	public static final String DOMESTIC_FUEL_ORDER_OK = "Order Of Domestic Fuel is OK";
	public static final String TRACK_ORDER = "Track Order Of Domestic Fuel.";
	public static final String FOUND_ORDERS = "There are orders found in DB";
	public static final String NO_ORDERS = "You don't have any order";
	public static final String GET_ALL_CLIENT_DATA = "Get client's data including cars from DB";
	public static final String DATA_CLIENT = "Client's data including cars were imported from DB";
	public static final String GET_PRICE = "Get price of product from DB";
	public static final String FOUND_PRICE = "Price of product was returned from DB";
	public static final String ADD_BILL = "Add bill to the DB";
	public static final String BILL_ADDED = "Bill added successfully to DB";
	public static final String SHOW_BILLS = "Show Client`s Bills";
	public static final String NO_BILLS = "You don't have any bills";
	public static final String FOUND_BILLS = "There are bills found in DB";
	public static final String SIGN_OUT = "Sign out from system";
	public static final String SIGN_OUT_SUCCESS = "Sign out from system successfully";
	

	
	
	
	///  Subscriptions Description //////////
	public static final String CASUAL_FUELING ="Payment Mode: by Liters quantity."+"\n"+
			"More Details: Maximum Price of Liter";		
	public static final String STANDARD_MONTHLY_FUELING_SINGLE ="Payment Mode: by Liters quantity."+"\n"+
			"More Details: Discount of 4% of the"+"\n"+"price.";	
	public static final String STANDARD_MONTHLY_FUELING_SEVERAL ="Payment Mode: by Liters quantity."+"\n"+
			"More Details: Discount of 4% of the"+"\n"+"price x Number of cars +"+"\n"+""
					+ "Discount of 10% of total price.";
	public static final String FULL_MONTHLY_FUELING_SINGLE ="Payment Mode: Parmenant Price"+"\n"+
			"More Details: The Liters quantity of"+"\n"+"last month x  Discount of 4% of the"+"\n"+"price x Number of cars +"+"\n"+""
					+ "Discount of 3% of total price.";
	
	///  Domestic Fuel Order Description //////////SPECIAL_ORDER
	public static final String URGENT_ORDER ="Get Fuel Within 6 Hours."+"\n"+
			"More Details: The Cost="+"\n"+"Amount of Liters x Liter`s Price"+"\n"+"+ 2% Of The Cost.";
	public static final String STANDARD_ORDER ="Wanted Fuel Amount: 600-800 Liters. "+"\n"+
			"More Details: The Cost="+"\n"+"Amount of Liters x Liter`s Price"+"\n"+"+ Discount of 3% Of Total Cost.";
	public static final String SPECIAL_ORDER ="Wanted Fuel Amount: Over 800 Liters. "+"\n"+
			"More Details: The Cost="+"\n"+"Amount of Liters x Liter`s Price"+"\n"+"+ Discount of 4% Of Total Cost.";
	public static final String GET_PRODUCTS_IN_CATLOG = "get product in catlog";
	public static final String ADD_ITEM_TO_ORDER_FROM_CATLOG = "Add order from catlog";
}
