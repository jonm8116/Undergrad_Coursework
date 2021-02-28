package pkg1;
import java.util.ArrayList;
public class OptimalElevator {
	/**This is the current floor of the elevator 
	 */
	private int currentFloor;
	/**This is the state of the elevator, like whether 
	 * its idle, going to source or going to destination
	 */
	private int elevatorState;
	
	private ArrayList requestList;
	/**This is the constant if the elevator is idle
	 */
	private final int IDLE=0;
	/**This is the constant if the elevator is 
	 * moving to the source
	 */
	private final int TO_SOURCE=1; 
	/**Constant if the elevator is moving 
	 * to the destination
	 */
	private final int TO_DESTINATION=2;	
	/**This is the constructor for the elevator object
	 */
	public OptimalElevator(){
		requestList = new ArrayList<Request>();
		currentFloor = 1;
		elevatorState = IDLE;
	}
	/**Accessor for current floor
	 * @return current floor
	 */
	public int getCurrentFloor() {
		return currentFloor;
	}
	/**Mutator for current floor
	 * @param currentFloor
	 */
	public void setCurrentFloor(int currentFloor) {
		this.currentFloor = currentFloor;
	}
	/**Accessor for elevator state
	 * @return elevator state
	 */
	public int getElevatorState() {
		return elevatorState;
	}
	/**Mutator for elevator state
	 * @param elevatorState
	 */
	public void setElevatorState(int elevatorState) {
		this.elevatorState = elevatorState;
	}
	/**Accessor for the request object
	 * @return request
	 */
//	public Request getRequest() {
//		return request;
//	}
	/**Mutator for request 
	 * @param request
	 */
//	public void setRequest(Request request) {
//		this.request = request;
//	}
}
