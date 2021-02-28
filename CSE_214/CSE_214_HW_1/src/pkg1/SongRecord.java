package pkg1;
/** The SongRecord class is used to create song objects
 * It contains the data fields: title, artist, minutes, seconds
 * The method implements Cloneable because the class implements
 * a clone method for SongRecord objects
 * @version 1, July 12 2016
 */
public class SongRecord implements Cloneable {
	/** This class has a title data field because all songs have titles
	 */
	private String title;
	/** This class has a artist data field because all songs have artists
	 * that produce them
	 */
	private String artist;
	/** The minutes data field is just the length in minutes of the song
	 */
	private int minutes;
	/**The seconds data field is how many seconds are left over in the time length
	 */
	private int seconds;
	/**This is the default no-arg constructor.
	 * It takes no parameters and makes a default song that I chose
	 * The song is just from a band I listen to
	 */
	SongRecord(){
		title = "As a butterfly";
		artist = "DBA";
		minutes = 5;
		seconds = 2;
	}
	/** This is the specific constructor 
	 * With this constructor the programmer can initialize 
	 * a song object with these parameters
	 * @param title
	 * @param artist
	 * @param minutes
	 * @param seconds
	 */
	SongRecord(String title, String artist, int minutes, int seconds){
		this.title = title;
		this.artist = artist;
		this.minutes = minutes;
		this.seconds = seconds;
		
		
	}
	/** This is the mutator method of the title data field 
	 * @param title
	 */
	public void setTitle(String title){
		this.title = title;
	}
	/** This is the accessor method of the title data field
	 * @return title
	 */
	public String getTitle(){
		return title;
	}
	/**Mutator method of artist data field
	 * @param artist
	 */
	public void setArtist(String artist){
		this.artist = artist;
	}
	/**Accessor method of artist data field
	 * @return artist
	 */
	public String getArtist(){
		return artist;
	}
	/**Mutator method of minutes data field
	 * @param minutes
	 * @exception IllegalArgumentException
	 */
	public void setMinutes(int minutes){
		try{
			if(minutes > 0)
				this.minutes = minutes;
			else
				throw new IllegalArgumentException("Invalid minutes value");
		}
		catch(IllegalArgumentException ex){
			System.out.println("Invalid minutes value");
		}
			
	}
	/**This is the accessor method for the minutes data field
	 * @return minutes
	 */
	public int getMinutes(){
		return minutes;
	}
	/**This is the mutator method for the seconds data field
	 * @param seconds
	 * @exception IllegalArgumentException 
	 */
	public void setSeconds(int seconds){
		try{
			if(seconds >0 && seconds < 59)
				this.seconds = seconds;
			else 
				throw new IllegalArgumentException("Not a valid value for seconds");
		}
		catch(IllegalArgumentException ex){
			System.out.println("Incorrect seconds value for song");
		}
	}
	/**This is the accessor method for the seconds data field
	 * @return seconds
	 */
	public int getSeconds(){
		return seconds;
	}
	/**This method is mainly used to display data 
	 * about a particular SongRecord object
	 */
	public void displayInfo(){
		System.out.printf("%-21s%-26s%19s%06d", title, artist, minutes, seconds);
	}
	/**This method tests whether two SongRecord objects are equivalent
	 * It makes sure if all the data fields are equal
	 * @param obj the SongRecord object
	 * @return boolean the value of whether the two objects are equal
	 */
	@Override
	public boolean equals(Object obj){
		SongRecord song = (SongRecord)obj;
		boolean result = false;
		if(title.equals(song.title) && artist.equals(song.artist) && minutes == song.minutes && seconds == song.seconds){
			result = true;
		}
		return result;
	}
	/**This method is meant to copy a song object
	 * It overrides the clone method in the Object class
	 * it is used in the clone method in the PlayList class
	 * @exception CloneNotSupportedException
	 * @return Object
	 */
	@Override 
	public Object clone() {
		SongRecord songClone = new SongRecord();
		try{
			songClone = (SongRecord)super.clone();
			String copyArtist = new String(this.artist);
			String copyTitle = new String(this.title);
			songClone.artist = copyArtist;
			songClone.title = copyTitle;
		}
		catch(CloneNotSupportedException ex){
			System.out.println("This clone is not supported");
		}
		return songClone;
	}
//	public static void main(String[] args){
//		SongRecord song1 = new SongRecord("feel it in the air", "t-mass", 3, 42);
//		SongRecord song2 = new SongRecord("whatcha say", "jason derulo", 3, 42);
//		SongRecord song3 = (SongRecord)song1.clone();
//		System.out.println(song1.equals(song3));
//
//	}
}
