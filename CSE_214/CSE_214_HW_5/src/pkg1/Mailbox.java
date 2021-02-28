package pkg1;
import java.util.Date;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.Serializable;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
/**This is the mailbox class which defines operations to perform operations 
 * on folders and emails
 * It is also the driver class for the program
 * @version 1, August 14, 2016
 */
public class Mailbox implements Serializable{
	/**This is the inbox folder for the mailbox 
	 */
	private Folder inbox;
	/**This is the trash folder for the mailbox
	 */
	private Folder trash;
	/**This is the list that contains all custom folders
	 */
	private ArrayList<Folder> folders;
	/**The static field that defines mailbox
	 */
	public static Mailbox mailbox;
	/**This is the main method which is used to run
	 * the program
	 * @param args
	 */
	public static void main(String[] args){
		Scanner input = new Scanner(System.in);
		boolean isComplete = false;
		
		mailbox = new Mailbox();
		mailbox.inbox = new Folder("inbox");
		mailbox.trash = new Folder("trash");
		mailbox.folders = new ArrayList<>();
		try {
			  //T
			  //If file is found, open it
			  FileInputStream  file = new FileInputStream("mailbox.obj");
			  ObjectInputStream fin  = new ObjectInputStream(file);
			  mailbox = (Mailbox) fin.readObject();
			  file.close();
			} catch(IOException a){
				while(!isComplete){
					//Print out folders
					System.out.println("Mailbox: ");
					System.out.println("------------");
					System.out.println("Inbox\nTrash");
					if(!mailbox.folders.isEmpty()){
						for(int i=0; i<mailbox.folders.size(); i++)
							System.out.println(mailbox.folders.get(i).getName());
						System.out.println();
					}
					System.out.println("A – Add folder");
					System.out.println("R – Remove folder");
					System.out.println("C – Compose email");
					System.out.println("F – Open folder");
					System.out.println("I – Open Inbox");
					System.out.println("T – Open Trash");
					System.out.println("E - Empty Trash");
					System.out.println("Q – Quit");
					String value = input.nextLine();
					try{
						if(value.equals("a")|| value.equals("A")){
							System.out.print("Enter the folder name: ");
							String folderName = input.nextLine();
							Folder newFolder = new Folder(folderName);
							mailbox.addFolder(newFolder);
						}
						else if(value.equals("R")|| value.equals("r")){
							System.out.println("Please enter the name of the folder "
									+ "you want to remove");
							String folderName = input.nextLine();
							//Consider changing method signature in order to 
							//know whether folder was deleted or not
							mailbox.deleteFolder(folderName);
						}
						else if(value.equals("c") || value.equals("C")){
							mailbox.composeEmail();
						}
						else if(value.equals("f")|| value.equals("F")){
							System.out.println("Please enter the folder name: ");
							String folderName = input.nextLine();
							Folder tempFolder = mailbox.getFolder(folderName);
							if(tempFolder != null){
								folderSubMenu(tempFolder);
							} else{
								System.out.println("That folder does not exist");
							}
						}
						else if(value.equals("i")|| value.equals("I")){
							folderSubMenu(mailbox.inbox);
						}
						else if(value.equals("t")|| value.equals("T")){
							folderSubMenu(mailbox.trash);
						}
						else if(value.equals("e")|| value.equals("E")){
							mailbox.trash.getEmails().clear();
						}
						else if(value.equals("q")|| value.equals("Q")){
							writeFile(mailbox);
							isComplete = true;
						}
						else{
							System.out.println("Incorrect input, please re-enter your choice");
						}
					} catch(FolderExistsException ex){
						System.out.println("This folder already exists");
					}
				}
			} catch(ClassNotFoundException c){
				System.out.println("The class was not found");
			}
		while(!isComplete){
			//Print out folders
			System.out.println("Mailbox: ");
			System.out.println("------------");
			System.out.println("Inbox\nTrash");
			if(!mailbox.folders.isEmpty()){
				for(int i=0; i<mailbox.folders.size(); i++)
					System.out.println(mailbox.folders.get(i).getName());
				System.out.println();
			}
			System.out.println("A – Add folder");
			System.out.println("R – Remove folder");
			System.out.println("C – Compose email");
			System.out.println("F – Open folder");
			System.out.println("I – Open Inbox");
			System.out.println("T – Open Trash");
			System.out.println("E - Empty Trash");
			System.out.println("Q – Quit");
			String value = input.nextLine();
			try{
				if(value.equals("a")|| value.equals("A")){
					System.out.print("Enter the folder name: ");
					String folderName = input.nextLine();
					Folder newFolder = new Folder(folderName);
					mailbox.addFolder(newFolder);
				}
				else if(value.equals("R")|| value.equals("r")){
					System.out.println("Please enter the name of the folder "
							+ "you want to remove");
					String folderName = input.nextLine();
					//Consider changing method signature in order to 
					//know whether folder was deleted or not
					mailbox.deleteFolder(folderName);
				}
				else if(value.equals("c") || value.equals("C")){
					mailbox.composeEmail();
				}
				else if(value.equals("f")|| value.equals("F")){
					System.out.println("Please enter the folder name: ");
					String folderName = input.nextLine();
					Folder tempFolder = mailbox.getFolder(folderName);
					boolean skip = true;
					if(tempFolder.getName().equals("")|| tempFolder.getEmails().isEmpty()){
						System.out.println("The folder is empty");
						skip = false;
					}
					if(tempFolder != null){
						if(skip)
							folderSubMenu(tempFolder);
					} else{
						System.out.println("That folder does not exist");
					}
				}
				else if(value.equals("i")|| value.equals("I")){
					folderSubMenu(mailbox.inbox);
				}
				else if(value.equals("t")|| value.equals("T")){
					folderSubMenu(mailbox.trash);
				}
				else if(value.equals("e")|| value.equals("E")){
					mailbox.trash.getEmails().clear();
				}
				else if(value.equals("q")|| value.equals("Q")){
					writeFile(mailbox);
					isComplete = true;
				}
				else{
					System.out.println("Incorrect input, please re-enter your choice");
				}
			} catch(FolderExistsException ex){
				System.out.println("This folder already exists");
			}
		}
	}
	/**This is a method that is called
	 * when opening/viewing a folder
	 * @param subFolder
	 */
	public static void folderSubMenu(Folder subFolder){
		boolean subMenuFinished = false;
		Scanner input = new Scanner(System.in);
		while(!subMenuFinished){
			mailbox.printEmails(subFolder);
			System.out.println("M – Move email");
			System.out.println("D – Delete email");
			System.out.println("V – View email contents");
			System.out.println("SA – Sort by subject line in ascending order");
			System.out.println("SD – Sort by subject line in descending order");
			System.out.println("DA – Sort by date in ascending order");
			System.out.println("DD – Sort by date in descending order");
			System.out.println("R – Return to mailbox");
			
			String value = input.nextLine();
			while(value.equals(""))
				value = input.nextLine();
			if(value.equals("m")|| value.equals("M")){
				System.out.print("Enter the index of the email to move: ");
				//This is the index of the email in the current folder
				//So there will be a list of emails and pick the one for the specific position
				int index = input.nextInt();
				Email refEmail = subFolder.getEmails().remove(index-1);
				System.out.println("Please enter the name of the folder");
				String folderName = "";
				while(folderName.equals(""))
					folderName = input.nextLine();
				try{
					Folder switchFolder = mailbox.getFolder(folderName);
					mailbox.moveEmail(refEmail, switchFolder);
				} catch(NullPointerException ex){
					mailbox.moveEmail(refEmail, mailbox.inbox);
				}
			}
			else if(value.equals("d")|| value.equals("D")){
				System.out.print("Enter the index of the email to delete: ");
				//This is the index of the email in the current folder
				//So there will be a list of emails and pick the one for the specific position
				int index = input.nextInt();
				Email refEmail = subFolder.getEmails().remove(index-1);
				//This just moves the email to the trash
				mailbox.moveEmail(refEmail, mailbox.trash);
			}
			else if(value.equals("v")|| value.equals("V")){
				System.out.println("Enter email index");
				int index = input.nextInt();
				Email refEmail = subFolder.getEmails().get(index-1);
				//Double check that the toString works 
				System.out.println(refEmail.toString());
			}
			else if(value.equals("r") || value.equals("R")){
				return;
			}
			else if(value.equalsIgnoreCase("sa")){
				subFolder.sortBySubjectAscending();
			}
			else if(value.equalsIgnoreCase("sd")){
				subFolder.sortBySubjectDescending();
			}
			else if(value.equalsIgnoreCase("da")){
				subFolder.sortByDateAscending();
			}
			else if(value.equalsIgnoreCase("dd")){
				subFolder.sortByDateDescending();
			}
			else{
				System.out.println("Incorrect choice, please re-enter valid choice");
			}
		}
	}
	/**This is the method that is used to write 
	 * the data to the .obj file
	 * @param mailbox
	 */
	public static void writeFile(Mailbox mailbox) {
		try {
			  FileOutputStream   file = new FileOutputStream("mailbox.obj");
			  ObjectOutputStream fout = new ObjectOutputStream(file);
			  fout.writeObject(mailbox);
			  fout.close();
			} catch(IOException a) { 
				System.out.println("An IO Exception occurred");
			}
	}
//	public static void readFile(Mailbox mailbox){
//		try {
//			  //If file is found, open it
//			  FileInputStream   file = new FileInputStream("mailbox.obj");
//			  ObjectInputStream fin  = new ObjectInputStream(file);
//			  mailbox = (Mailbox) fin.readObject();
//			  file.close();
//			} catch(IOException a){
//				System.out.println("An IO Exception occurred");
//			} catch(ClassNotFoundException c){
//				System.out.println("The class was not found");
//			}
//	}
	/**This method is used to print out all the
	 * emails in a given folder
	 * It calls the email toString method
	 * @param subFolder
	 */
	public void printEmails(Folder subFolder){
		String header = "Index |  	 Time     | Subject";
		String line = "\n-------------------------------------------------";
		System.out.println(header+line);
		for(int i=0; i<subFolder.getEmails().size(); i++){
			if(subFolder.getEmails().get(i)!= null){
				int index = i+1;
				String time = subFolder.getEmails().get(i).dateToString();
				String subject = subFolder.getEmails().get(i).getSubject();
				System.out.println("  "+index+"   |"+"  "+time+" |"+" "+subject+"\n");
			}
		}
	}
	/**This method is used to add a custom folder to the list
	 * of folders 
	 * @param folder
	 * @throws FolderExistsException
	 */
	public void addFolder(Folder folder) throws FolderExistsException{
		if(folder.getName().equals(""))
			System.out.println("Folder must have a name");
		else{
			for(int i=0; i<folders.size(); i++){
				if(folders.get(i).getName().equals(folder.getName())){
					throw new FolderExistsException();
				}
			}
			folders.add(folder);
		}
	}
	/**This method is used to delete a folder
	 * from the list of folders
	 * @param name
	 */
	public void deleteFolder(String name){
		if(name.equals("Inbox")|| name.equals("Trash")){
			System.out.println("Cannot delete Inbox or Trash folder");
		}
		else{
			for(int i=0; i<folders.size(); i++){
				if(folders.get(i).getName().equals(name)){
					folders.remove(i);
					//do you need this
					return;
				}
			}
			System.out.println("The folder was not found");
		}
	}
	/**This method is used to create an email
	 * by taking user info as the input information
	 */
	public void composeEmail(){
		Scanner input = new Scanner(System.in);
		
		System.out.print("Enter the recipient (To): ");
		String to = input.nextLine();
		System.out.print("Enter carbon copy recipients (CC): ");
		String cc = input.nextLine();
		System.out.print("Enter blind carbon copy recipients (BCC): ");
		String bcc = input.nextLine();
		System.out.print("Enter subject line: ");
		String subject = input.nextLine();
		System.out.print("Enter body: ");
		String body = input.nextLine();
		Email newEmail = new Email(to, cc, bcc, subject, body);
		inbox.addEmail(newEmail);
		System.out.println("The email was successfully added to the inbox");
	}
	/**This method is used to delete an email
	 * from a given folder
	 * @param email
	 */
	public void deleteEmail(Email email){
		for(int i=0; i<inbox.getEmails().size(); i++){
			if(inbox.getEmails().get(i).equals(email)){
				Email removedEmail = inbox.removeEmail(i);
				trash.addEmail(removedEmail);
			}
		}
	}
	/**This method is used to clear all
	 * emails fromt the trash
	 */
	public void clearTrash(){
		//Is this entirely correct?
		trash.getEmails().clear();
	}
	/**This method is used to move an email from 
	 * one folder to another
	 * @param email
	 * @param target
	 */
	public void moveEmail(Email email, Folder target){
		boolean value = true;
		if(target.equals(mailbox.trash)){
			target.addEmail(email);
		}
		if(target.equals(mailbox.inbox)){
			target.addEmail(email);
		}
		for(int i=0; i<folders.size(); i++){
			if(folders.get(i).getName().equals(target.getName())){
				folders.get(i).addEmail(email);
			}
		}
	
	}
	/**This method is used to find a folder with a given name
	 * @param name
	 * @return
	 * @throws NullPointerException
	 */
	public Folder getFolder(String name) throws NullPointerException{
		Folder folder = null;
		for(int i=0; i<folders.size(); i++){
			if(folders.get(i).getName().equals(name)){
				folder = folders.get(i);
			}
		}
		if(name.equals("inbox")){
			folder = inbox;
		}
		if(name.equals("trash")){
			folder = trash;
		}
		return folder;
	}
}
