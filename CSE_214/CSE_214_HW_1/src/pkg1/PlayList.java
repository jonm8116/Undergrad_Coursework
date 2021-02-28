package pkg1;

//import java.io.File;
//
//import javax.sound.sampled.AudioInputStream;
//import javax.sound.sampled.AudioSystem;
//import javax.sound.sampled.Clip;

/** This class is meant as the playlist program
 * It defines a PlayList
 * which stores SongRecord objects in an array of size 50 (maximum value)
 * Its data fields are playlistArray, name, and playlistItems
 * It implements Cloneable because it implements Cloneable
 * @version 1, July 12, 2016
 */

public class PlayList implements Cloneable{
	/**This data field is the array which holds the SongRecord objects
	 */
	private SongRecord[] playlistArray;
	/**This data field defines the name of each PlayList
	 */
	private String name;
	/**This data field defines how many elements are in the PlayList
	 * It is also what is returned in the size() method
	 */
	private int playlistItems;
	/**This is the constructor for PlayList
	 * It defines the name of the PlayList and instantiates 
	 * the array, with the intial number of elements (zero)
	 * <p>
	 * Construct an instance of the Playlist class with no SongRecord objects in it.
		Postcondition:
		This Playlist has been initialized to an empty list of SongRecords.
	   </p>
	 * @param name
	 */
	PlayList(String name){
		this.name = name;
		playlistArray = new SongRecord[50];
		playlistItems = 0;
	}
	/**This is the accessor method of the name of the PlayList
	 * @return String the name of the PlayList class
	 */
	public String getName(){
		return name;
	}
	/**This is the mutator method of the PlayList name
	 * @param name the name of the PlayList
	 */
	public void setName(String name){
		this.name = name;
	}
	/**This is the clone method for PlayList objects.
	 * It will return a cloned PlayList.
	 * @exception CloneNotSupportedException
	 * @return Object the cloned PlayList
	 */
	@Override
	public Object clone(){
		PlayList clonedPlayList= new PlayList("clone");
		//try{
			//clonedPlayList = (PlayList)super.clone();
			for(int i=0; i<this.size(); i++){
				//clonedPlayList.addSong(this.getSong(i), i);
				if(playlistArray[i] == null)
					break;
				clonedPlayList.playlistArray[i] = (SongRecord)this.playlistArray[i].clone();
				clonedPlayList.playlistItems++;
			}
//		}
//		catch(CloneNotSupportedException ex){
//			throw new RuntimeException("Clone is not supported");
//		}
		return clonedPlayList;
	}
	/**This method is the equals method for PlayList objects.
	 * It goes through each song in the PlayList to see if 
	 * they are the same and in the same order.
	 * If obj is null or it is not a Playlist object, then the return value is false.
	 * @param obj the PlayList you want to see whether is equal
	 * @return bxxoolean the value of whether the two PlayLists are equal
	 */
	@Override 
	public boolean equals(Object obj){
		//cast the object to type PlayList
		PlayList list = (PlayList)obj;
		if(!(obj instanceof PlayList)){
			return false;
		}
		else if(list.playlistArray[0] == null){
			return false;
		}
		else{
			int count =0;
			//How do you specify between two different PlayList objects
			for(int i=0; i<list.playlistArray.length; i++){
				//The && operator is to check if both PlayLists have a null value
				if(list.playlistArray[i] != null && playlistArray[i] != null){
					if(playlistArray[i].equals(list.playlistArray[i])){
						count++;
					}
				}
			}
			if(list.size() == count){
				return true;
			}
			else{
				return false;
			}
		}
	}
	/**The size method returns how big the PlayList is
	 * or how many elements are in the array.
	 * <p>
	 * Determines the number of SongRecords currently in this Playlist.
	   Preconditions:
	   This SongRecord object has been instantiated.</p>
	 * @return playlistItems 
	 */
	public int size(){
		return playlistItems;
	}
	/**The <strong>addSong</strong> method is used to add a song to the PlayList
	 * It will add a song at any position 
	 * If there is a song at the desired position 
	 * then it will shift all songs from that position
	 * and latter positions to the right.
	 * However it will not add a song if the position
	 * is not in the playlist or if the playlist is full.
	 * <p>
	 * Preconditions:
		This SongRecord object has been instantiated and 
		1 < position < songs_currently_in_playlist + 1.
		The number of SongRecord objects in this Playlist is less than max_songs. 
	   </p>
	 * @exception IllegalArgumentException
	 * @exception FullPlayListException
	 * @param song
	 * @param position
	 */
	public void addSong(SongRecord song, int position){
		try{
			if(position<0 || position>playlistArray.length-1 || position>(size() +1)){
				throw new IllegalArgumentException("Illegal argument used");
			}
			if(song.getSeconds()<0 || song.getSeconds()>59 || song.getMinutes()<0){
				throw new IllegalArgumentException("Invalid song length");
			}
			if(size()==50){
				throw new FullPlayListException(this.name);
			}
			if(playlistArray[position-1] != null){
				for(int i=size()-1; i>=position-1; i--){ //size() -1 & i >= position -1
					playlistArray[i+1] = playlistArray[i];
				}
			}
			playlistArray[position - 1] = song;
			this.playlistItems++;
		}
		catch(IllegalArgumentException ex){
			System.out.println("Position not located in playlist or invalid time length");
		}
		catch(FullPlayListException ex){
			System.out.println("Your " + this.name + " playlist is full");
		}
		
	}

	/**This method is used to remove a song from the PlayList at the
	 * desired position
	 * The position is the position in the PlayList, not the array
	 * It will remove the song at the desired PlayList and then
	 * will shift all the songs to the left so that there are no gaps
	 * in the playlist
	 * <p>
	 * Preconditions: This SongRecord object has been instantiated and 
		1 < position < songs_currently_in_playlist. 
	   Postcondition: The SongRecord at the desired position in the Playlist 
	   has been removed. All SongRecords that were originally in positions 
	   greater than or equal to position are moved forward one position. 
	   (Ex: If there are 5 songs in a Playlist, positions 1-5, and you remove 
	   the SongRecord at position 4, the SongRecord that was at position 5 will be moved to position 4).
	   </p> 
	 * @param position
	 */
	public void removeSong(int position){
		this.playlistArray[position - 1] = null;
		for(int i= position-1; i<size(); i++){
			this.playlistArray[i] = this.playlistArray[i+1];
		}
//		int count = 0;
//		for(int i=0; i<playlistArray.length; i++){
//			if(playlistArray[i] != null){
//				count++;
//				if(count == position){
//					playlistArray[i] = null;
//					break;
//				}
//			}
//		}
		playlistItems--;
	}
	/** This method is used to get a song from a given position in the PlayList
	 * similar to the remove method but this is an accessor method
	 * <p>
	 * Preconditions:
	   This Playlist object has been instantiated 
	   and 1 < position < songs_currently_in_playlist.
	   </p>
	 * @param position
	 * @return SongRecord
	 */
	public SongRecord getSong(int position){
		SongRecord song = new SongRecord();
		try{
			if(position < 1 || position > size()){
				throw new IllegalArgumentException("Position is not within valid range");
			}
			else{
				song = this.playlistArray[position -1];
			}
		}
		catch(IllegalArgumentException ex){
			System.out.println("Choose another position in playlist");
		}
		return song;
	}
	/**This method is used to generate a new playlist
	 * the method finds all song objects that have the same artist
	 * <p>
	 * Preconditions:
	   The Playlist referred to by originalList has been instantiated.
	   </p>
	 * @param originalList
	 * @param artist
	 * @return PlayList
	 */
	public static PlayList getSongsByArtist(PlayList originalList, String artist){
		
		PlayList newList = new PlayList(artist);
		for(int i=0; i<originalList.size(); i++){
		
			if(artist.equals(originalList.playlistArray[i].getArtist())){
				newList.playlistArray[newList.size()] = originalList.playlistArray[i];
				newList.playlistItems++;
			}	
		}
		return newList;
	}
	/**This is the toString method for PlayList
	 * It will print out a formatted table of 
	 * all the songs within the playlist
	 * It prints out the artist, title, time and song number
	 * in the playlist.
	 * @return String the formatted table
	 */
	@Override 
	public String toString(){
		int count = 0;
		String body = ""; 
		String header = "Song#   Title                Artist                   "
				+ "Length\n-----------------------------------------------------------\n";
		//This first loop is just to find the positions					   "      3:2"	
		for(int i=0; i<this.playlistArray.length; i++){
			if(playlistArray[i] != null){
				count++;
				String time = this.playlistArray[i].getMinutes()+":"+
							  this.playlistArray[i].getSeconds();
				//fix this 
				//its not right
//				String.format("%s", this.playlistArray[i].getTitle() + " ") + 
//				String.format("%s",this.playlistArray[i].getArtist()) + "          " + this.playlistArray[i].getMinutes()+":"
//				+this.playlistArray[i].getSeconds()+"\n"
				body += count + "       " + String.format("%-21s%-25s%-300s", 
						this.playlistArray[i].getTitle(), this.playlistArray[i].getArtist(),
						time)+"\n";
			}
		}
		return header+body;
	}
	/**This is the printAllSongs method it is used
	 * to print out all the songs in the PlayList
	 * It calls the toString method from this class
	 * <p>
	 * Preconditions:
		This SongRecord object has been instantiated.
		Postcondition:
		A neatly formatted table of each SongRecord in the Playlist 
		on its own line with its position number has been displayed to the user.
		</p>
	 * @param arr
	 */
	public static void printAllSongs(PlayList arr){
//		for(int i=0; i<arr.size(); i++){
//			//Is this making the code more complex??
//			if(arr.playlistArray[i]!= null)
//				System.out.println(arr.playlistArray[i].getTitle());
//		}
		String listOfSongs = arr.toString();
		System.out.println(listOfSongs);
	}
//	public void playSong(){
//		 try {
//		        AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("C:/Users/John/Desktop/Music_Files/Radioactive-Imagine_Dragons.wav").getAbsoluteFile());
//		        Clip clip = AudioSystem.getClip();
//		        clip.open(audioInputStream);
//		        clip.start();
//		    } catch(Exception ex) {
//		        System.out.println("Error with playing sound.");
//		        ex.printStackTrace();
//		    }
//	}

}
