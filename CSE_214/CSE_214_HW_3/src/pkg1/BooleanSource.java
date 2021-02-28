package pkg1;
/**This is the Boolean Source class
 * This code was based off of the BooleanSource class in 
 * the lecture notes
 * @version 1, July 29, 2016
 */
public class BooleanSource {
	/**This is the probability of the event occurring
	 */
	private double probability;
	/**This is the constructor for Boolean Source
	 * @param p
	 */
	public BooleanSource(double p){
		if(p<0.0 || p>1.0)
			throw new IllegalArgumentException("Invalid probability");
		probability = p;
	}
	/**This method is whether to see if a request arrives 
	 * @return boolean whether a request arrives
	 */
	public boolean requestArrived(){
		return (Math.random() <= probability);
	}
}
