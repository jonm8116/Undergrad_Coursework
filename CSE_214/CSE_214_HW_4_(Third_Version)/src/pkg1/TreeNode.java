package pkg1;
/**This is the class which defines an object in the TreeNode class 
 * this represents each node in the ternary tree
 * @version 1, August 8, 2016
 */
public class TreeNode {
	/**This is the reference to the right child
	 */
	private TreeNode right;
	/**Reference to the middle child
	 */
	private TreeNode middle;
	/**Reference to the left child
	 */
	private TreeNode left;
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
	public TreeNode(String label, String message, String prompt){
		//Don't set parent label?
		this.label = label;
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
	public void preOrder(TreeNode root){
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
	public TreeNode getRight() {
		return right;
	}
	/**Mutator method for right child
	 * @param right
	 */
	public void setRight(TreeNode right) {
		this.right = right;
	}
	/**Accessor method for middle child
	 * @return middle
	 */
	public TreeNode getMiddle() {
		return middle;
	}
	/**Mutator method for middle child
	 * @param middle
	 */
	public void setMiddle(TreeNode middle) {
		this.middle = middle;
	}
	/**Accessor method for left child
	 * @return left
	 */
	public TreeNode getLeft() {
		return left;
	}
	/**Mutator method for left child
	 * @param left
	 */
	public void setLeft(TreeNode left) {
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
