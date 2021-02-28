package pkg1;
import java.util.Scanner;
import java.io.*;
/**This is the Tree class and it is used to create a ternary tree
 * This class also contains methods to perform operations on a tree
 * @version 1, August 8, 2016
 */
public class Tree {
	/**This is the root reference for the ternary tree
	 */
	private TreeNode root;
	/**This is a reference to any point in the tree
	 * It is used to assist in operations on the tree
	 */
	private TreeNode cursor;
	/**This is the addNode method which is used to add a node
	 * to the ternary tree
	 * @param label
	 * @param prompt
	 * @param message
	 * @param parentLabel
	 * @return boolean - whether the node is added or not
	 */
	public boolean addNode(String label, String prompt, String message, String parentLabel){
		TreeNode newNode = new TreeNode(label, message, prompt);
		if(root==null){
			root = newNode;
			return true;
		}
		else{
			try{
			TreeNode reference = this.findPosition(root, parentLabel);
				if(reference!= null){
					if(reference.getLeft()==null){
						reference.setLeft(newNode);
						return true;
					}
					else if(reference.getMiddle()==null){
						reference.setMiddle(newNode);
						return true;
					}
					else if(reference.getRight()== null){
						reference.setRight(newNode);
						return true;
					}
				}
			} catch(NullPointerException ex){
				System.out.println("Null Pointer Exception");
			}
		}
		return false;
	}
	/**This is a helper method that is used to find the correct 
	 * parent in the tree for the node that is being added
	 * @param node
	 * @param parentLabel
	 * @return TreeNode
	 */
	public TreeNode findPosition(TreeNode node, String parentLabel){
		if(node.getLabel().equals(parentLabel)){
			cursor = node;
			return cursor;
		}
		if(node.getLeft()!=null){
			findPosition(node.getLeft(), parentLabel);
		}
		if(node.getMiddle()!= null){
			findPosition(node.getMiddle(), parentLabel);
		}
		if(node.getRight()!= null){
			findPosition(node.getRight(), parentLabel);
		}
		return cursor;
	}
	/**This is the preOrder method for the tree
	 * It calls a preOrder method from the treeNode class
	 */
	public void preOrder(){
		root.preOrder(root);
	}
	/**This is the read file method for the tree class
	 * It reads lines from a text file and uses the information 
	 * given to create nodes for the tree and then it calls the
	 * addNode method from within this method
	 * @param br
	 * @throws IOException
	 */
	public void readFile(BufferedReader br) throws IOException{
		boolean value = false;
		int count = 1;
		String numChild = "1";
		String numChilds = "1";
		String parentLabel = null;
		while(br!=null){
			while(count<=Integer.parseInt(numChilds)){
				String label = br.readLine().trim();
				String prompt = br.readLine().trim();
				String message = br.readLine().trim();
				addNode(label, prompt, message, parentLabel);
				count++;
			}
			try{
				numChild = br.readLine().trim();
				String[] arr = numChild.split(" ");
				parentLabel = arr[0];
				numChilds = arr[1];
				count = 1;
			} catch(NullPointerException ex){
				break;
			}
		}
		//closes the buffered reader 
		br.close();
		//Loop through file reading for how many children are created
	}
	/**This method returns a TreeNode that has the specified label 
	 * @param label
	 * @return TreeNode
	 */
	public TreeNode getNodeByReference(String label){
		TreeNode temp = root;
		TreeNode tempNode = null;
		try{
			tempNode = checkNodes(temp, label);
		} catch(NullPointerException ex){
			System.out.println("There is no node with that label");
		}
		return tempNode;
	}
	/**This is a helper method for the get node by reference method
	 * it recursively traverses through the tree to find the reference
	 * @param node
	 * @param label
	 * @return TreeNode
	 */
	public TreeNode checkNodes(TreeNode node, String label){
		if(node.getLabel().equals(label)){
			cursor = node;
			return node;
		}
		if(node.getLeft()!= null){
			checkNodes(node.getLeft(), label);
		}
		if(node.getMiddle()!= null){
			checkNodes(node.getMiddle(), label);
		}
		if(node.getRight()!= null){
			checkNodes(node.getRight(), label);
		}
		return cursor;
	}
	/**This method begins the help session for the tree
	 * and takes in user input
	 * @throws EmptyTreeException 
	 */
	public void beginSession(){
		Scanner input = new Scanner(System.in);
		cursor = root;
		System.out.println("Help Session starting...");
		while(!cursor.isLeaf()){
			System.out.println(cursor.getMessage());
			if(cursor.getLeft()!= null)
				System.out.println("1: " + cursor.getLeft().getPrompt());
			if(cursor.getMiddle()!= null)
				System.out.println("2: " + cursor.getMiddle().getPrompt());
			if(cursor.getRight() != null)
				System.out.println("3: " + cursor.getRight().getPrompt());
			String value = input.nextLine();
			if(value.equals("1")){
				cursor = cursor.getLeft();
			}
			else if(value.equals("2")){
				cursor = cursor.getMiddle();
			}
			else if(value.equals("3")){
				cursor = cursor.getRight();
			}
			else{
				System.out.println("Re-enter input");
			}
		}
		//cursor.getPrompt()+"\n"+
		System.out.println(cursor.getMessage());
		System.out.println("Thank you for using our automated system");
	}
	/**The accessor method for the root
	 * @return root
	 */
	public TreeNode getRoot(){
		return root;
	}
	/**The mutator method for the root
	 * @param root
	 */
	public void setRoot(TreeNode root){
		this.root = root;
	}
}
