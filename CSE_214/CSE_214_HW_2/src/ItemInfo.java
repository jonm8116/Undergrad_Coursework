/**The ItemInfo class is a class that defines an object of
 * type of ItemInfo which could be any item in a department store
 * It has five data fields which are the item's name, price,
 * RFID number, original and current location
 * @version 1, July 20 2016
 */
public class ItemInfo {
	/**Defines the name of the item
	 */
	private String name;
	/**Defines the price of the item
	 */
	private double price; //This value must be positive
	/**Defines the RFID number of the item
	 * These numbers are used to differentiate and sort the items
	 */
	private String rfidTagNumber;
	/**The original location of the item in the store
	 */
	private String originLocation; 
	/**The current location of the item in the store
	 */
	private String currentLocation;
	/**This is the constructor which defines an ItemInfo object
	 * @param name
	 * @param price
	 * @param tagNum
	 * @param originLoc
	 */
	public ItemInfo(String name, double price, String tagNum, String originLoc){
		setName(name);
		setPrice(price);
		setRfidTagNumber(tagNum);
		setOriginLocation(originLoc);
		setCurrentLocation(originLoc);
	}
	/**Accessor method for name
	 * @return String name of item
	 */
	public String getName() {
		return name;
	}
	/**Mutator method for name
	 * @exception IllegalArgumentException thrown if name is longer than 20 characters
	 * @param name
	 */
	public void setName(String name) {
		try{
			if(name.length()>20){
				throw new IllegalArgumentException("Name is longer than 20 characters");
			}
			this.name = name;
		} catch(IllegalArgumentException ex){
			System.out.println("Name is longer than 20 characters");
		}
	}
	/**Accessor method for price
	 * @return double price of item
	 */
	public double getPrice() {
		return price;
	}
	/**Mutator method for price
	 * @exception IllegalArgumentException thrown if price is negative
	 * @param price
	 */
	public void setPrice(double price) {
		try{
			if(price<0){
				throw new IllegalArgumentException("Can't have negative money");
			}
			this.price = price;
		} catch(IllegalArgumentException ex){
			System.out.println("Can't have negative money");
		}
	}
	/**Accessor method for rfidTagNumber
	 * @return rfidTagNumber
	 */
	public String getRfidTagNumber() {
		return rfidTagNumber;
	}
	/**Mutator method for rfidTagNumber
	 * @exception IllegalArgumentException thrown if rfid is not 9 characters long
	 * or if the rfid is not in hexadecimal
	 * @param rfidTagNumber
	 */
	public void setRfidTagNumber(String rfidTagNumber) {
		if(rfidTagNumber.length() !=9){
			throw new IllegalArgumentException("RFID is not 9 characters");
		}
		if(!(rfidTagNumber.matches("^[0-9a-fA-F]+$"))){
			throw new IllegalArgumentException("RFID is not hexadecimal");
		}
		else{
			this.rfidTagNumber = rfidTagNumber;
		}
	}
	/**Accessor method for original location
	 * @return originLocation
	 */
	public String getOriginLocation() {
		return originLocation;
	}
	/**Mutator method for original location
	 * @exception IllegalArgumentException thrown if string
	 * does not equal 6 characters or string does not start with "s"
	 * @param originLocation
	 */
	public void setOriginLocation(String originLocation) {
		
			if(!(originLocation.matches("(s||S)[0-9]{5}+"))){
				throw new IllegalArgumentException("incorrect location format");
			}
			this.originLocation = originLocation;
		
	}
	/**Accessor method for current location
	 * @return currentLocation
	 */
	public String getCurrentLocation() {
		return currentLocation;
	}
	/**Mutator method for current location
	 * @param currentLocation
	 */
	public void setCurrentLocation(String currentLocation) {
			if(currentLocation.matches("(s||S)[0-9]{5}+") 
					|| currentLocation.matches("(c||C)[0-9]{3}+")||
					(currentLocation.equals("out"))){
				this.currentLocation = currentLocation;
			}
			else{
				throw new IllegalArgumentException("incorrect location format");
			}
			this.currentLocation = currentLocation;
		
	}
	/**toString method for ItemInfo objects
	 * @return String description of itemInfo object
	 */
	@Override
	public String toString() {
		String value = String.format("%-18s%-13s%-14s%-12s%2.0f", name, 
				rfidTagNumber, originLocation, currentLocation, price);
		return value;
	}

}
