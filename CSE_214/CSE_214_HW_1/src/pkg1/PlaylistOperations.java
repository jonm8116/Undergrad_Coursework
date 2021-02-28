package pkg1;
import java.util.Scanner;
/**The PlaylistOperations class is the driver class for this project
 * This class is what provides user interaction for creating the playlist
 * @version July 12, 2016 
 */
public class PlaylistOperations {
	/**This is the main method from which all of the logic is defined
	 * Each action decided by the user is divided by
	 * if and else if statements
	 * @param args
	 */
	public static void main(String[] args) {
		/**This playlist is created to hold multiple playlists
		 * It is initialized here and can later be used to define 
		 * multiple playlists
		 */
		PlayList[] arrOfPlayList = new PlayList[10];
		for(int i=0; i<arrOfPlayList.length; i++){
			arrOfPlayList[i] = new PlayList("");
		}
		
		System.out.println("Welcome to Playlist manager!");
		System.out.println("What would you like to name your first playlist?");
		
		Scanner input = new Scanner(System.in);
		String playlistName = input.nextLine();
		//input2.close();
		PlayList list = new PlayList(playlistName);
		arrOfPlayList[0] = list;
		
		boolean isComplete = false;
		while(!isComplete){
			//Scanner input = new Scanner(System.in);
			
			System.out.println("What would you like to do?");
			System.out.println("Add Song:               A  <Title> <Artist> <Minutes> <Seconds> <Position>");
			System.out.println("Get Song:               G  <Position>");
			System.out.println("Remove Song:            R  <Position>");
			System.out.println("Print All Songs:        P");
			System.out.println("Print Songs By Artist:  B  <Artist>");
			System.out.println("Size:                   S");
			System.out.println("New Playlist:           N");
			System.out.println("Change Playlist         V");
			System.out.println("Compare Playlists       E");
			System.out.println("Copy PlayList           C");
			System.out.println("Display Playlists       D");
			System.out.println("Quit:                   Q");
			
			//input.reset();
			String value = input.nextLine();
			//This small loop is called to make sure that 
			//the compiler does not skip the input
			while (value.equals(""))
				value = input.nextLine();
			
			/**The first if statement is used if the
			 * user quits from the playlist manager
			 */
			if(value.equals("q") || value.equals("Q")){
				isComplete = true;
			}
			/**This option allows for users to check 
			 * the size of the playlist
			 */
			else if(value.equals("s") || value.equals("S")){
				System.out.println("The " + list.getName() + " playlist has " + list.size() + " elements");
			}
			/**This option allows for users to add songs
			 * to the playlist
			 */
			else if(value.equals("a") || value.equals("A")){
				System.out.print("Enter the song title: ");
				String songName = input.nextLine();
				System.out.print("Enter the song artist: ");
				String songArtist = input.nextLine();
				System.out.print("Enter the song length (minutes): ");
				int songMin = input.nextInt();
				System.out.print("Enter the song length (seconds): ");
				int songSec = input.nextInt();
				System.out.print("Enter the position in the playlist");
				int position = input.nextInt();
				list.addSong(new SongRecord(songName, songArtist, songMin, songSec), position);
				if(list.getSong(position).getTitle() == songName)
					System.out.println("Song Added: " + songName + " by " + songArtist);
			}
			/**This option allows for users to 
			 * print all the songs from the current playlist
			 */
			else if(value.equals("p") || value.equals("P")){
				if(list.size() == 0){
					System.out.println("There are no songs in the playlist");
				}
				else{
					list.printAllSongs(list);
				}
			}
			/**This option allows for users to remove a song
			 * from a desired point in the playlist
			 */
			else if(value.equals("r") || value.equals("R")){
				System.out.println("Enter the position of the song you want to remove: ");
				int position = input.nextInt();
				if(position<=list.size())
					list.removeSong(position);
				else
					System.out.println("This position is not in the playlist");
			}
			/**This option allows for users to print only the songs
			 * by a give artist
			 */
			else if(value.equals("b") || value.equals("B")){
				System.out.println("Enter the name of the artist: ");
				String artistName = input.nextLine();
				PlayList artistList = list.getSongsByArtist(list, artistName);
				artistList.printAllSongs(artistList);
			}
			/**This option gets a song from a given 
			 * position within the playlist
			 */
			else if(value.equals("g") || value.equals("G")){
				System.out.println("Please enter the position of the song in the playlist: ");
				int songPosition = input.nextInt();
				System.out.print("Song#     Title           Artist          Length\n------------------------------------------------\n");
				System.out.print(songPosition +"     "+ list.getSong(songPosition).getTitle() + "          " +
						list.getSong(songPosition).getArtist() + "  " + list.getSong(songPosition).getMinutes()
						+":"+list.getSong(songPosition).getSeconds()+"\n");
				
			}
			/**This option allows the user to create a new playlist 
			 * (extra credit)
			 */
			else if(value.equals("n") || value.equals("N")){
				System.out.println("What would you like to name your playlist?");
				String name = input.nextLine();
				int index=0;
				for(int i=1; i<arrOfPlayList.length; i++){
					if(arrOfPlayList[i].getName().equals("")){
						arrOfPlayList[i].setName(name);
						index = i;
						break;
					}
					else{
						System.out.println("You have created the max amount of playlists");
					}
				}
				list = arrOfPlayList[index];
			}
			/**This option allows for users
			 * to change the current playlist
			 * (extra credit)
			 */
			else if(value.equals("v") || value.equals("V")){
				System.out.println("Which playlist would you like to switch to?");
				String name = input.nextLine(); //name of playlist
				int count = 0;
				for(int i=0; i<arrOfPlayList.length; i++){
					if(arrOfPlayList[i].getName().equals(name)){
						list = arrOfPlayList[i];
						break;
					}
					else{
						count++;
						if(count == arrOfPlayList.length)
							System.out.println("This playlist doesn't exist");
					}
				}
			}
			/**This option is meant to compare whether 
			 * two different playlists are equal to each other
			 * (extra credit)
			 */
			else if(value.equals("e") || value.equals("E")){
				System.out.println("Choose the playlist to compare");
				String name = input.nextLine();
				PlayList list2 = new PlayList("compare");
				int count = 0;
				for(int i=0; i<arrOfPlayList.length; i++){
					if(arrOfPlayList[i].getName().equals(name)){
						list2 = arrOfPlayList[i];
						break;
					}
					else{
						count++;
						if(count == arrOfPlayList.length)
							System.out.println("This playlist doesn't exist");
					}
				}
				System.out.println("The playlists " + list.getName() +" and " + list2.getName()
								+ " are " + list.equals(list2));
			}
			/**This option is meant to display or print out
			 * all the playlists that have been created
			 * (extra credit)
			 */
			else if(value.equals("d") || value.equals("D")){
					System.out.println("The playlists are: ");
				for(int i=0; i<arrOfPlayList.length; i++){
					System.out.println(arrOfPlayList[i].getName());
					if(arrOfPlayList[i].getName().equals(""))
						break;
				}
			}
			/**This else if is to copy playlist 
			 * and the user can input the name into the new playlist
			 */
			else if(value.equals("c") || value.equals("C")){
				System.out.println("What would you like to name your playlist?");
				String name = input.nextLine();
				int index=0;
				for(int i=0; i<arrOfPlayList.length; i++){
					if(arrOfPlayList[i].getName().equals("")){
						//arrOfPlayList[i].setName(name);
						index = i;
						break;
					}
					else{
						if(arrOfPlayList[i].getSong(i+1).getMinutes()==0)
							System.out.println("You have created the max amount of playlists");
					}
				}
				arrOfPlayList[index] = (PlayList)list.clone();
				arrOfPlayList[index].setName(name);
				list = arrOfPlayList[index];
			}
			/**This else statement is here just in case
			 * the user does not enter a valid option
			 */
			else{
				System.out.println("Enter a correct choice");
			}
		}
	}

}
