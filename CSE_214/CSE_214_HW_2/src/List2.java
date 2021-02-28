//
//public class List2 {
//	private ItemInfoNode head;
//	private ItemInfoNode tail;
//	private ItemInfoNode cursor;
//	
//	public List2(){
//		head = null;
//		tail = null;
//		cursor = null;
//	}
////	public void insertInfo(String name, String rfidTag, double price, String initPosition){
////		ItemInfo data = new ItemInfo(name, price, rfidTag, initPosition);
////		ItemInfoNode newNode = new ItemInfoNode(data);
////		//Go back to identify
////		//all the cases
////		//and go over sorting for the rfidTag#s
////		if(head == null){
////			head = newNode;
////			cursor = newNode;
////			tail = newNode;
////		}
////		else if(head.getNext()== null){
////			int place =head.getInfo().getRfidTagNumber().compareToIgnoreCase(
////					newNode.getInfo().getRfidTagNumber());
////			if(place<0 || place==0){
////				newNode.setNext(head);
////				head.setPrev(newNode);
////				tail = head;
////				head = newNode;
////			}
////			//This is if the newNode is lexiographically ordered first
////			//compareTo method returns value greater than or equal to 1
////			else{
////				head.setNext(newNode);
////				newNode.setPrev(head); 
////				cursor = newNode;
////				tail = newNode;
////			}
////		}
////		else{
////			//consider making separate method
////			//if gets too annoying to debug
////			int value = findSortedPosition(newNode);
////			int place = 0;
////			cursor = tail;
////			while(cursor != null){
////				place = cursor.getInfo().getRfidTagNumber().compareToIgnoreCase(
////						newNode.getInfo().getRfidTagNumber());
////				if(Math.abs(place) == value){
////					//make boolean variable
////					//change value when place isn't same as value
////					break;
////				}
////				cursor = cursor.getPrev();
////			}
////			//cursor = head;
////			if(place>0){
////				if(cursor == head){
////					newNode.setNext(cursor.getNext());
////					cursor.getNext().setPrev(newNode);
////					newNode.setPrev(cursor);
////					cursor.setNext(newNode);
////				}
////				else{
////					newNode.setNext(cursor);
////					newNode.setPrev(cursor.getPrev());
////					cursor.getPrev().setNext(newNode);
////					cursor.setPrev(newNode);
////				}
////			}
////			if(place<0){
////				if(cursor == head){
////					newNode.setNext(head);
////					head.setPrev(newNode);
////					head = newNode;
////				}
////				else{
////					newNode.setNext(cursor);
////					newNode.setPrev(cursor.getPrev());
////					cursor.setNext(newNode);
////					newNode.getNext().setPrev(newNode);
////				}
////			}
////		}
////	}
//	public void insertInfo(String name, String rfidTag, double price, String initPosition){
//	try{
//		ItemInfo data = new ItemInfo(name, price, rfidTag, initPosition);
//		ItemInfoNode newNode = new ItemInfoNode(data);
//	
//	//Go back to identify
//	//all the cases
//	//and go over sorting for the rfidTag#s
//		if(head == null){
//			head = newNode;
//			cursor = newNode;
//			tail = newNode;
//		}
//		else if(head.getNext()== null){
//			head.setNext(newNode);
//			newNode.setPrev(head);
//			cursor = newNode;
//			tail = newNode;
//		}
//		else{
//			cursor = head;
//			while(cursor.getNext() != null){
//				cursor = cursor.getNext();
//			}
//			cursor.setNext(newNode);
//			newNode.setPrev(cursor);
//			tail = newNode;
//		}
//	} catch(IllegalArgumentException ex){
//		System.out.println("Incorrect input");
//	}
//}
//public int findSortedPosition(ItemInfoNode newNode){
//	cursor = head;
//	int position = 7;
//	int place;
//	while(cursor != null){
//		place = cursor.getInfo().getRfidTagNumber().compareToIgnoreCase(
//				newNode.getInfo().getRfidTagNumber());
//		if(Math.abs(place)<position){
//			position = Math.abs(place);
//		}
//		cursor = cursor.getNext();
//	}
//	cursor = head;
//	return position;
//}
//	public int findSortedPosition(ItemInfoNode newNode){
//		cursor = head;
//		int position = 30;
//		int place;
//		while(cursor != null){
//			place = cursor.getInfo().getRfidTagNumber().compareToIgnoreCase(
//					newNode.getInfo().getRfidTagNumber());
//			if(Math.abs(place)<position){
//				position = Math.abs(place);
//			}
//			cursor = cursor.getNext();
//		}
//		cursor = head;
//		return position;
//	}
//	public void printAll(){
//		String header = "Item Name         RFID         origin" +
//				"        current     price"
//				+ "\n---------------------------------------------------------------";
//		cursor = head;
//		System.out.println(header);
//		while(cursor != null){
//			System.out.println(cursor.getInfo().toString());
//			cursor = cursor.getNext();
//		}
//	}
//	public static void main(String[] args){
//		List2 list = new List2();
//		//System.out.println(list.head);
//		list.insertInfo("1", "aaaaaaaaa", 1000, "s00013");
//		//list.printAll();
//		list.insertInfo("2", "ccccccccc", 50, "s32760");
//		list.printAll();
//		//list.printAll();
//		list.insertInfo("3", "fffffffff", 500, "s90909");
//		list.printAll();
//		list.insertInfo("4", "ddddddddd", 750, "s00013");
//		list.printAll();
//		list.insertInfo("5", "000000000", 600, "s32760");
//		list.printAll();
//		list.insertInfo("6", "bbbbbbbbb", 50, "s32760");
//		list.printAll();
//		//list.moveItem("aa02309zz", "s32760", "c22445");
//		
////		System.out.println(list.head.getInfo().getName());
////		System.out.println(list.head.getNext().getInfo().getName());
////		System.out.println(list.head.getNext().getNext().getInfo().getName());
////		System.out.println(list.head.getNext().getNext().getNext().getInfo().getName());
////		System.out.println(list.head.getNext().getNext().getNext().getNext().getInfo().getName());
//		//list.printAll();
//		//list.printByLocation("s00013");
//		//System.out.println(list.tail.getPrev().getInfo().getName());
//	}
//}
