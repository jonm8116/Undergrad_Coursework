package pkg1;
import java.io.*;
import java.util.Scanner;
/**This is the tree driver class, which contains the main method to run
 * the program
 * It also contains a create tree method which is used to instantiate the tree
 * @version 1, August 8, 2016
 */
public class TreeDriver {
	/**This is the main method used to run the program
	 * @exception EmptyTreeException
	 * @param args
	 */
	public static void main(String[] args) {
		//Tree tree = createTree();
		Tree tree=null;
		//tree.preOrder();
		boolean isComplete = false;
		Scanner input = new Scanner(System.in);
		while(!isComplete){
			try{
				System.out.println("L - Load a Tree");
				System.out.println("H - Begin a Help Session");
				System.out.println("T - Traverse the Tree in preorder");
				System.out.println("Q - Quit");
				String value = input.nextLine();
				
				while(value.equals(""))
					value = input.nextLine();
				
				if(value.equals("q")|| value.equals("Q")){
					isComplete = true;
				}
				else if(value.equals("L")|| value.equals("l")){
					System.out.print("Please enter the absolute file path>");
					String filePath = input.nextLine();
					tree = createTree(filePath);
				}
				else if(value.equals("t")|| value.equals("T")){
					if(tree==null)
						throw new EmptyTreeException();
					else
						tree.preOrder();
				}
				else if(value.equals("H")|| value.equals("h")){
					if(tree==null)
						throw new EmptyTreeException();
					else
						tree.beginSession();
				}
				else{
					System.out.println("Invalid input, re-enter choice");
				}
				
			} catch(EmptyTreeException ex){
				System.out.println("Please load file. The tree is empty");
			} 
		}
	}
	/**This is the method used to create the tree 
	 * @param filePath
	 * @return
	 */
	public static Tree createTree(String filePath){
		try{
			File givenFile = new File(filePath);
			//File secondFile = new File("pkg1/TechSupport.txt");
			Tree tree = new Tree();
			//System.out.println(givenFile.exists());
			BufferedReader getInfo = new BufferedReader(new FileReader(givenFile));
			tree.readFile(getInfo);
			return tree;
		}
		catch(FileNotFoundException ex){
			System.out.println("The file was not found");
		}
		catch(IOException ex){
			System.out.println("An IO Exception occurred");
		} 
		return null;
	}
}
