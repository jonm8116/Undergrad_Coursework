
public class TNodeExtraCredit {
	private TNodeExtraCredit right;
	/**Reference to the middle child
	 */
	private TNodeExtraCredit middle;
	/**Reference to the left child
	 */
	private TNodeExtraCredit left;
	/**This is the label of each tree node
	 */
	private String label;
	/**This is the message of each tree node
	 */
	private String message;
	/**This is the prompt of each tree node
	 */
	private String prompt;
	/**This is the parentLabel of each node
	 */
	private String parentLabel;
	/**This is the constructor for the tree node object
	 * @param label
	 * @param message
	 * @param prompt
	 */
	public TNodeExtraCredit(String label, String message, String prompt, String parentLabel){
		//Don't set parent label?
		this.label = label;
		this.parentLabel = parentLabel;
		this.message = message;
		this.prompt = prompt;
		right = null;
		left = null;
		middle = null;
	}
	/**This method determines whether a node is a leaf
	 * @return boolean
	 */
	public boolean isLeaf(){
		// return right == null && left == null && middle == null
		return right==null && left == null && middle == null;
	}
	/**Helper method for preOrder in Tree Class 
	 * @param root
	 */
	public void preOrder(TNodeExtraCredit root){
		//Change place of temp
		System.out.println(root.toString());
		if(root.getLeft()!= null){
			preOrder(root.getLeft());
		}
		if(root.getMiddle()!=null){
			preOrder(root.getMiddle());
		}
		if(root.getRight()!= null){
			preOrder(root.getRight());
		}
	}
	public void postOrder(TNodeExtraCredit root){
		//Change place of temp
		
		if(root.getLeft()!= null){
			preOrder(root.getLeft());
		}
		if(root.getMiddle()!=null){
			preOrder(root.getMiddle());
		}
		if(root.getRight()!= null){
			preOrder(root.getRight());
		}
		System.out.println(root.toString());
	}
	/**This is the toString method for 
	 * the tree node object
	 */
	@Override 
	public String toString(){
		String info = "label: " + label + "\nmessage: " + message + "\nprompt: " + prompt;
		return info;
	}
	/**Accessor method for parent label
	 * @return parent label
	 */
	public String getParentLabel(){
		return this.parentLabel;
	}
	/**Mutator method for parent label
	 * @param parentLabel
	 */
	public void setParentLabel(String parentLabel){
		this.parentLabel = parentLabel;
	}
	/**Accessor method for the right child
	 * @return right
	 */
	public TNodeExtraCredit getRight() {
		return right;
	}
	/**Mutator method for right child
	 * @param right
	 */
	public void setRight(TNodeExtraCredit right) {
		this.right = right;
	}
	/**Accessor method for middle child
	 * @return middle
	 */
	public TNodeExtraCredit getMiddle() {
		return middle;
	}
	/**Mutator method for middle child
	 * @param middle
	 */
	public void setMiddle(TNodeExtraCredit middle) {
		this.middle = middle;
	}
	/**Accessor method for left child
	 * @return left
	 */
	public TNodeExtraCredit getLeft() {
		return left;
	}
	/**Mutator method for left child
	 * @param left
	 */
	public void setLeft(TNodeExtraCredit left) {
		this.left = left;
	}
	/**Accessor method for label
	 * @return label
	 */
	public String getLabel() {
		return label;
	}
	/**Mutator method for label
	 * @param label
	 */
	public void setLabel(String label) {
		this.label = label;
	}
	/**Accessor method for message
	 * @return message
	 */
	public String getMessage() {
		return message;
	}
	/**Mutator method message
	 * @param message
	 */
	public void setMessage(String message) {
		this.message = message;
	}
	/**Accessor method for prompt
	 * @return prompt
	 */
	public String getPrompt() {
		return prompt;
	}
	/**Mutator method for prompt
	 * @param prompt
	 */
	public void setPrompt(String prompt) {
		this.prompt = prompt;
	}
}
