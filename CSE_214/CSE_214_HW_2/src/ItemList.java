/**The item list class is defined as the linked list class 
 * for holding ItemInfo objects
 * It has three data fields which are references to nodes 
 * in the list. (head, tail, cursor)
 * @version 1, July 20, 2016
 */
public class ItemList {
	/**This is the reference to the first node in the list
	 */
	private ItemInfoNode head;
	/**Reference to the last node in the list
	 */
	private ItemInfoNode tail;
	/**Reference that is used to traverse through and perform 
	 * operations on the list
	 */
	private ItemInfoNode cursor;
	/**The constructor for the linked list
	 * sets all of the references to null (since list is empty)
	 */
	public ItemList(){
		head = null;
		tail = null;
		cursor = null;
	}
	/**This is the insert info method which inserts into the list
	 * <p>
	 * The complexity is O(n)
	 * </p>
	 * @param name
	 * @param rfidTag
	 * @param price
	 * @param initPosition
	 */
	public void insertInfo(String name, String rfidTag, double price, String initPosition){
		ItemInfo data = new ItemInfo(name, price, rfidTag, initPosition);
		ItemInfoNode newNode = new ItemInfoNode(data);
		if(head==null){
			head = newNode;
			tail = newNode;
			cursor = newNode;
		}
		else if(head==tail){
			int place =head.getInfo().getRfidTagNumber().compareToIgnoreCase(
				newNode.getInfo().getRfidTagNumber());
			if(place<1){
				head.setNext(newNode);
				newNode.setPrev(head);
				tail = newNode;
			}
			else if(place>=1){
				head.setPrev(newNode);
				newNode.setNext(head);
				head = newNode;
				tail = newNode.getNext();
			}
		}
		else{
			cursor = head;
			while(cursor !=null){
				int place =cursor.getInfo().getRfidTagNumber().compareToIgnoreCase(
						newNode.getInfo().getRfidTagNumber());
				if(rfidTag.compareToIgnoreCase(tail.getInfo().getRfidTagNumber())>0){
					tail.setNext(newNode);
					newNode.setPrev(tail);
					tail = newNode;
				}
					if(place>0){
						if(cursor==head){
							newNode.setNext(head);
							head.setPrev(newNode);
							head = newNode;
							break;
						}
						else{
							cursor.getPrev().setNext(newNode);
							newNode.setPrev(cursor.getPrev());
							cursor.setPrev(newNode);
							newNode.setNext(cursor);
							break;
//							newNode.setNext(cursor);
//							newNode.setPrev(cursor.getPrev());
//							cursor.getPrev().setNext(newNode);
//							cursor.setPrev(newNode);
						}		
				}
				cursor = cursor.getNext();
			}
		}
	}
	/**The printAll method is used to print out all the items
	 * in the list
	 * @exception EmptyListException
	 * <p>
	 * The complexity of this algorithm in Big-O notation is O(n)
	 * because there is a while loop to traverse through the list
	 * </p>
	 */
	public void printAll(){
		try{
			if(head == null){
				throw new EmptyListException();
			}
		} catch(EmptyListException ex){
			System.out.println("The list is empty");
		}
		String header = "Item Name         RFID         origin" +
				"        current     price"
				+ "\n---------------------------------------------------------------";
		cursor = head;
		System.out.println(header);
		while(cursor != null){
			System.out.println(cursor.getInfo().toString());
			cursor = cursor.getNext();
		}
	}
	/**The printByLocation method is used to print out all items
	 * from a certain location
	 * @exception EmptyListException
	 * @param location
	 * <p>
	 * The complexity of printByLocation in Big-O notation is O(n)
	 * because you use a while loop to traverse through the list
	 * </p>
	 */
	public void printByLocation(String location){
		try{
			if(head==null){
				throw new EmptyListException();
			}
		} catch(EmptyListException ex){
			System.out.println("The list is empty");
		}
		cursor = head;
		String header = "Item Name         RFID         origin" +
				"        current     price"
				+ "\n----------------------------------------------------------------";
		System.out.println(header);
		while(cursor!= null){
			if(cursor.getInfo().getCurrentLocation().equals(location)){
				System.out.println(cursor.getInfo().toString());
			}
			cursor = cursor.getNext();
		}
	}
	/**The moveItem is used to move a specified item from its source location
	 * to a new location(which could also be a cart or "out")
	 * <p>
	 * The order of complexity for moveItem in Big-O notation is O(n)
	 * because you use a while loop to traverse through the list
	 * </p>
	 * @exception EmptyListException
	 * @param rfidTag
	 * @param source
	 * @param dest
	 * @return
	 */
	public boolean moveItem(String rfidTag, String source, String dest){
		cursor = head;
		boolean value = false;
		try{
			if(head== null){
				throw new EmptyListException();
			}
		    if(!(dest.startsWith("s") || dest.startsWith("c") || dest.equals("out") || dest.length()==6))
		    	throw new IllegalArgumentException("Invalid destination location");
		} catch(IllegalArgumentException ex){
			System.out.println("Invalid destination location");
		} catch(EmptyListException ex){
			System.out.println("The list is empty");
		}
		while(cursor!= null){
			if(cursor.getInfo().getRfidTagNumber().equals(rfidTag)){
					if(cursor.getInfo().getCurrentLocation().equals(source)){
						value = true;
						break;
					}
			}
			cursor = cursor.getNext();
		}
		cursor.getInfo().setCurrentLocation(dest);
		return value;
	}
	/**The checkOut method goes through the list and checks out
	 * each item with the specified cart number
	 * So it changes the current location to out
	 * <p>
	 * The order of complexity in Big-O notation is O(n)
	 * because you use a while loop to traverse through the list
	 * </p>
	 * @exception IllegalArgumentException
	 * @exception EmptyListException
	 * @param cartNumber
	 * @return double totalPrice of the checked out items
	 */
	public double checkOut(String cartNumber){
		double totalPrice = 0;
		try{
			if(head==null){
				throw new EmptyListException();
			}
			if(cartNumber.startsWith("c") || cartNumber.startsWith("C")){
				cursor=head;
				while(cursor!=null){
					if(cursor.getInfo().getCurrentLocation().equals(cartNumber)){
						cursor.getInfo().setCurrentLocation("out");
						totalPrice += cursor.getInfo().getPrice();
					}
					cursor = cursor.getNext();
				}
				printByLocation("out");
			} else{
				throw new IllegalArgumentException("This is not a cart number");
			}
		}
		catch(IllegalArgumentException ex){
			System.out.println("This is not a cart number");
		} catch(EmptyListException ex){
			System.out.println("The list is empty");
		}
		return totalPrice;
	}
	/**The cleanStore method takes every item in the wrong location
	 * and returns it to its original position
	 * This does not change items that are "out" or in a cart.
	 * <p>
	 * The complexity of cleanStore in Big-O notation is O(n)
	 * because you use a while loop to traverse through the list
	 * </p>
	 * @exception EmptyListException 
	 */
	public void cleanStore(){
		try{
			if(head == null){
				throw new EmptyListException();
			}
		} catch(EmptyListException ex){
			System.out.println("The list is empty");
		}
		String header = "Item Name         RFID         origin" +
				"        current     price"
				+ "\n----------------------------------------------------------------";
		System.out.println(header);
		cursor = head;
		while(cursor != null){
			if(!(cursor.getInfo().getCurrentLocation().equals(cursor.getInfo().getOriginLocation()))){
				if(!(cursor.getInfo().getCurrentLocation().equals("out")|| 
				   cursor.getInfo().getCurrentLocation().startsWith("c") || 
				   cursor.getInfo().getCurrentLocation().startsWith("C"))){
					
					System.out.println(cursor.getInfo().toString());
					cursor.getInfo().setCurrentLocation(cursor.getInfo().getOriginLocation());
				}
			}
			cursor = cursor.getNext();
		}
	}
	/**This method removes all items that have their location
	 * labeled as out.
	 * <p>
	 * The complexity of removeAllPurchased in Big-O notation is O(n)
	 * because you use a while loop to traverse through the list
	 * </p>
	 * @exception EmptyListException 
	 */
	public void removeAllPurchased(){
		try{
			if(head == null){
				throw new EmptyListException();
			}
		} catch(EmptyListException ex){
			System.out.println("The list is empty");
		}
		String header = "Item Name         RFID         origin" +
				"        current     price"
				+ "\n----------------------------------------------------------------";
		System.out.println(header);
		cursor = head;
		while(cursor != null){
			if(cursor.getInfo().getCurrentLocation().equals("out")){
				
				System.out.println(cursor.getInfo().toString());
				removeNode(cursor);
			}
			cursor = cursor.getNext();
		}
	}
	/**This is the helper method for removeAllPurchased
	 * It calls the removeNode method for whichever items are
	 * labeled as out
	 * <p>
	 * The complexity of removeNode is O(1) because there is 
	 * no while loop and there are only assignment statements
	 * so it would take approximately the same amount of time each time the
	 * method is run
	 * </p>
	 * @param node
	 */
	public void removeNode(ItemInfoNode node){
		//case 1 if cursor is head
		if(node==head){
			if(head.getNext()==null){
				head = null;
			} 
			else{
				head = head.getNext();
				head.setPrev(null);
			}
		}
		//case 2 if cursor is tail
		else if(node== tail){
			tail = tail.getPrev();
			tail.setNext(null);
		}
		//case 3 if cursor is in middle
		else{
			//Double check this case
			node.getNext().setPrev(node.getPrev());
			node.getPrev().setNext(node.getNext());
		}
	}
	/**This method prints out each item for a given rfid
	 * <p>
	 * The complexity of printByRFID is O(n)
	 * because you use a while loop to traverse through the list
	 * </p>
	 * @exception EmptyListException 
	 * @param rfid
	 */
	public void printByRFID(String rfid){
		try{
			if(head == null){
				throw new EmptyListException();
			}
		} catch(EmptyListException ex){
			System.out.println("The list is empty");
		}
		String header = "Item Name         RFID         origin" +
				"        current     price"
				+ "\n----------------------------------------------------------------";
		System.out.println(header);
		cursor = head;
		while(cursor!= null){
			if(cursor.getInfo().getRfidTagNumber().equals(rfid)){
				System.out.println(cursor.getInfo().toString());
			}
			cursor = cursor.getNext();
		}
	}
	public static void main(String[] args){
		ItemList list = new ItemList();
		//System.out.println(list.head);
		list.insertInfo("linux", "ccccccccc", 50, "s32760");
		list.insertInfo("MAC", "abaaaaaaa", 1000, "s00013");
		list.insertInfo("Ubuntu", "aa02309ff", 50, "s32760");
		list.insertInfo("Windows", "fffffffff", 500, "s90909");
		list.insertInfo("Microsoft", "aa02309ff", 300, "s33333");

		list.insertInfo("ASUS", "ddddddddd", 750, "s00013");
		list.insertInfo("Kali", "bd032dff", 30, "s32760");
		list.insertInfo("Dell", "ff321fbf", 600, "s33333");
		
		list.moveItem("ddddddddd", "s00013", "s11123");
		list.moveItem("aa02309ff", "s32760", "s22445");
		//out
		list.moveItem("ccccccccc", "s32760", "out");
		list.moveItem("aa02309ff", "s33333", "out");
		list.removeAllPurchased();
		//list.cleanStore();
		list.printAll();
		//list.printByLocation("s00013");
		//System.out.println(list.tail.getPrev().getInfo().getName());
	}
}
