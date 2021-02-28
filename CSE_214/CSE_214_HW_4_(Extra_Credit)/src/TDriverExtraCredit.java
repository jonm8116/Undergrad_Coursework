import java.io.*;
import java.util.Scanner;
/**The new feature implemented shows users how to create new text files
 * for the system
 * This shows them how to use the decision-making system in more ways than
 * what is just presented to them
 *
 */
public class TDriverExtraCredit {

	public static void main(String[] args) {
		TExtraCredit tree=null;
		//tree.preOrder();
		boolean isComplete = false;
		Scanner input = new Scanner(System.in);
		while(!isComplete){
			try{
				System.out.println("L - Load a Tree");
				System.out.println("H - Begin a Help Session");
				System.out.println("T - Traverse the Tree in preorder");
				System.out.println("N - How to make a new file");
				System.out.println("P - Post order");
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
					tree.beginSession();
				}
				else if(value.equals("n") || value.equals("N")){
					System.out.println("In order to make a new text file");
					System.out.println("You must adhere to a few rules");
					boolean finish=false;
					int count=1;
					while(!finish){
						System.out.println("Press " + count +" to continue");
						String skip = input.nextLine();
						if(skip.equals("1")){
							System.out.println("First, every node in the tree is made of three parts"
									+ "\n1 label \n2 message \n3 prompt");
							System.out.println("So you want to decide what you want each of your nodes "
									+ "in your system to be");
						}
						else if(skip.equals("2")){
							System.out.println("Each line in the tree defines a different part of the node");
							System.out.println("The order for each line for the nodes is "
									+ "\n1 label \n2 prompt \n3 message");
							System.out.println("So type out the all the nodes in chronological order "
									+ "in your text file");
						}
						else if(skip.equals("3")){
							System.out.println("After each level in the tree is completed");
							System.out.println("You should have a line which defines the number of"
									+ " children for the left most child on that level");
							System.out.println("An example would be \n(parent) (number of children)");
							System.out.println("4         3");
						}
						else if(skip.equals("4")){
							System.out.println("Another example would be");
							System.out.println("<label>\n<prompt>\n<message>");
							System.out.println("label1 <number-of-children>");
						}
						else if(skip.equals("5")){
							System.out.println("Makes sense? (Y/N)");
							String guess = input.nextLine();
							if(guess.equals("y") || guess.equals("Y")){
								finish = true;
							}
							else if(guess.equals("n")|| guess.equals("N")){
								count = 1;
								skip = "1";
							} else{
								System.out.println("That's not a yes or no. Please re-enter");
							}
						}
						count++;
					}
					System.out.println("Now go out and make your new system");
				}
				else if(value.equals("p")|| value.equals("P")){
					if(tree==null)
						throw new EmptyTreeException();
					else
						tree.postOrder();
				}
				else{
					System.out.println("Invalid Input re-enter choice");
				}
			} catch(EmptyTreeException ex){
				System.out.println("Please load file. The tree is empty");
			}
		}
	}
	
	public static TExtraCredit createTree(String filePath){
		try{
			File givenFile = new File(filePath);
			//File secondFile = new File("pkg1/TechSupport.txt");
			TExtraCredit tree = new TExtraCredit();
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


