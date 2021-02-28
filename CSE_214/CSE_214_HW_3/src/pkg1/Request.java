package pkg1;
/**This class is used to create the request objects for the 
 * elevator class
 * A request contains a source, destination and time entered
 * @version 1, July 29, 2016
 */
public class Request {
	/**This is the floor at which the request comes from
	 */
	private int sourceFloor;
	/**This is the floor that the passenger wants to go to
	 */
	private int destinationFloor;
	/**This is the time that the passenger initially made the request
	 */
	private int timeEntered;
	/**This is the constructor for a request
	 * It generates random source and destination floors
	 * @param numOfFloors
	 */
	public Request(int numOfFloors){
		this.sourceFloor = (int)(Math.random()*(numOfFloors+1) + 1);
		this.destinationFloor = (int)(Math.random()*(numOfFloors+1) + 1);
	}
	/**Accessor method for the source floor
	 * @return sourceFloor
	 */
	public int getSourceFloor() {
		return sourceFloor;
	}
	/**Mutator method for source floor
	 * @param sourceFloor
	 */
	public void setSourceFloor(int sourceFloor) {
		this.sourceFloor = sourceFloor;
	}
	/**Accessor method for destination floor
	 * @return destination floor
	 */
	public int getDestinationFloor() {
		return destinationFloor;
	}
	/**Mutator method for destination floor
	 * @param destinationFloor
	 */
	public void setDestinationFloor(int destinationFloor) {
		this.destinationFloor = destinationFloor;
	}
	/**Accessor method for time entered
	 * @return time entered
	 */
	public int getTimeEntered() {
		return timeEntered;
	}
	/**Mutator method for time entered
	 * @param timeEntered
	 */
	public void setTimeEntered(int timeEntered) {
		this.timeEntered = timeEntered;
	}
}
