import java.io.BufferedReader;
import java.io.IOException;
import java.util.Scanner;

public class TExtraCredit {
	/**This is the root reference for the ternary tree
	 */
	private TNodeExtraCredit root;
	/**This is a reference to any point in the tree
	 * It is used to assist in operations on the tree
	 */
	private TNodeExtraCredit cursor;
	/**This is the addNode method which is used to add a node
	 * to the ternary tree
	 * @param label
	 * @param prompt
	 * @param message
	 * @param parentLabel
	 * @return boolean - whether the node is added or not
	 */
	public boolean addNode(String label, String prompt, String message, String parentLabel){
		TNodeExtraCredit newNode = new TNodeExtraCredit(label, message, prompt, parentLabel);
		if(root==null){
			root = newNode;
			return true;
		}
		else{
			try{
				TNodeExtraCredit reference = this.findPosition(root, parentLabel);
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
	public TNodeExtraCredit findPosition(TNodeExtraCredit node, String parentLabel){
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
	public void postOrder(){
		root.postOrder(root);
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
	public TNodeExtraCredit getNodeByReference(String label){
		TNodeExtraCredit temp = root;
		TNodeExtraCredit tempNode = null;
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
	public TNodeExtraCredit checkNodes(TNodeExtraCredit node, String label){
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
			System.out.println("B - Go Back");
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
			else if(value.equals("b") || value.equals("B")){
				if(cursor!=root)
					cursor = getNodeByReference(cursor.getParentLabel());
				else 
					System.out.println("Can't go back, at the root");
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
	public TNodeExtraCredit getRoot(){
		return root;
	}
	/**The mutator method for the root
	 * @param root
	 */
	public void setRoot(TNodeExtraCredit root){
		this.root = root;
	}
}
