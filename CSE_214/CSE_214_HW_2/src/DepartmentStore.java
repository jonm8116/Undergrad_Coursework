import java.util.Scanner;

public class DepartmentStore {

	public static void main(String[] args) {
		System.out.println("Welcome to Department Store");
		
		ItemList list = new ItemList();
		
		Scanner input = new Scanner(System.in);
		
		boolean isComplete = false;
		while(!isComplete){
	
			System.out.println("What would you like to do?");
			System.out.println("C - Clean store");
			System.out.println("I - Insert an item into the list");
			System.out.println("L - List by location");
			System.out.println("M - Move an item in the store");
			System.out.println("O - Checkout");
			System.out.println("P - Print all items in store ");
			System.out.println("R - Print by RFID tag number");
			System.out.println("U - Update inventory system");
			System.out.println("Q - Exit the program");
			System.out.print("Please select an option: ");
			//input.reset();
			String value = input.nextLine();
			//This small loop is called to make sure that 
			//the compiler does not skip the input
			while (value.equals(""))
				value = input.nextLine();
			if(value.equals("i")|| value.equals("I")){
				System.out.print("Enter the name: ");
				String itemName = input.nextLine();
				System.out.print("Enter the RFID: ");
				String itemRFID = input.nextLine();
				System.out.print("Enter the original location: ");
				String itemOrigin = input.nextLine();
				System.out.print("Enter the price: ");
				double itemPrice = input.nextDouble();
				list.insertInfo(itemName, itemRFID, itemPrice, itemOrigin);
			}
			else if(value.equals("l")||value.equals("L")){
				System.out.print("Please enter the location: ");
				String location = input.nextLine();
				list.printByLocation(location);
			}
			else if(value.equals("m")|| value.equals("M")){
				System.out.print("Enter the RFID: ");
				String itemRFID = input.nextLine();
				System.out.print("Enter the original location: ");
				String itemOrigin = input.nextLine();
				System.out.print("Enter the new location: ");
				String itemCurrent = input.nextLine();
				list.moveItem(itemRFID, itemOrigin, itemCurrent);
			}
			else if(value.equals("o")|| value.equals("O")){
				//Definitely double check this
				System.out.print("Enter cart number: ");
				String cartNum = input.nextLine();
				String cartNumValue = cartNum.substring(1, cartNum.length()-1);
				double price = list.checkOut(cartNum);
				System.out.println("\nThe total cost for all merchandise in cart "+
									cartNumValue+" was "+ price);
			}
			else if(value.equals("p")|| value.equals("P")){
				list.printAll();
			}
			//extra credit
			else if(value.equals("r")|| value.equals("R")){
				System.out.print("Please enter the RFID value: ");
				String rfid = input.nextLine();
				list.printByRFID(rfid);
			}
			else if(value.equals("u")|| value.equals("U")){
				System.out.println("The following items have been removed from the system:");
				list.removeAllPurchased();
			}
			else if(value.equals("c")|| value.equals("C")){
				System.out.println("The follwing items have been moved back to their"
						+ " original position");
				list.cleanStore();
			}
			else if(value.equals("q")|| value.equals("Q")){
				isComplete = true;
			}
		}
	}

}
