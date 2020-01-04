
import java.util.Scanner;
public class Player {
    public static final String menu = "Menu:\n"+
            "(A) Add Song to Playlist\n"+
            "(F) Go to Next Song\n" +
            "(B) Go to Previous Song\n" +
            "(R) Remove Song from Playlist\n" +
            "(L) Play a Song\n" +
            "(C) Clear the Playlist\n" +
            "(S) Shuffle Playlist\n" +
            "(Z) Random Song\n" +
            "(P) Print Playlist\n" +
            "(T) Get the total amount of songs in the playlist\n" +
            "(Q) Exit the playlist\n"+
            "Enter an option: ";

    public static void printMenu()
    {
        System.out.println(menu);
    }

    /**
     * Asks for user input and adds song based on it
     * @param playlist playlist
     */
    public static void A(SongLinkedList playlist)
    {
        Scanner sc= new Scanner(System.in);
        System.out.println("Enter the song title: ");
        String name= sc.nextLine();
        if(name.isBlank())
        {
            System.out.println("Invalid name");
            return;
        }
        System.out.println("Enter the artist(s) of the song: ");
        String artists = sc.nextLine();
        if(artists.isBlank())
        {
            System.out.println("Invalid name(s)");
            return;
        }
        System.out.println("Enter album: ");
        String album = sc.nextLine();
        if(album.isBlank())
        {
            System.out.println("Invalid name");
            return;
        }
        int length=0;
        System.out.println("Enter the length of your song (in seconds): ");
        try{
            length = Integer.parseInt(sc.nextLine());
        }
        catch (Exception e)
        {
            System.out.println("Invalid length");
            return;
        }
        Song newSong = new Song();
        newSong.setAlbum(album);
        newSong.setArtist(artists);
        newSong.setLength(length);
        newSong.setName(name);
        try {
            playlist.insertAfterCursor(newSong);
        }
        catch (Exception ignored){}
        System.out.println(newSong.toString() + " is added to your " +
                "playlist.");
    }

    /**
     * calls cursorForward() on playlist
     * @param playlist playlist
     */
    public static void F(SongLinkedList playlist)
    {try {
        if (playlist.getCursor() != playlist.getTail()) {
            playlist.cursorForwards();
            System.out.println("Cursor moved to next song.");
        } else {
            System.out.println("Cursor is already at the end of the playlist.");
        }
    }
    catch (NullCursorException e)
    {
        System.out.println("The playlist is empty.");
    }
    }

    /**
     * Calls cursorBackward on playlist
     * @param playlist playlist
     */
    public static void B(SongLinkedList playlist)
    {
        try {
            if (playlist.getCursor() != playlist.getHead()) {
                playlist.cursorBackwards();
                System.out.println("Cursor moved to previous song.");
            } else {
                System.out.println("Cursor is already at the beginning of the " +
                        "playlist.");
            }
        }
        catch (NullCursorException e)
        {
            System.out.println("The playlist is empty.");
        }
    }

    /**
     * calls removeCursor on playlist
     * @param playlist playlist
     */
    public static void R(SongLinkedList playlist)
    {
        if(playlist.getCursor()!=null) {
            System.out.println(playlist.getCursor().getData().toString() + " " +
                    "was removed from your playlist.");
            try {
                playlist.removeCursor();
            } catch (Exception ignored)
            {

            }
        }
        else
        {
            System.out.println("There are no songs in this playlist.");
        }

    }

    /**
     * Prints playlist
     * @param playlist playlist
     */
    public static void P(SongLinkedList playlist)
    {
        playlist.printPlaylist();
    }

    /**
     * Asks user for song name and plays selected song if found
     * @param playlist playlist
     */
    public static void L(SongLinkedList playlist)
    {
        System.out.println("Enter the name of the song you want to play: ");
        Scanner sc = new Scanner(System.in);
        String name = sc.nextLine();
        if(name.isBlank())
        {
            System.out.println("Invalid name");
            return;
        }
        try
        {
            playlist.play(name);
        }
        catch (IllegalArgumentException e)
        {
            System.out.println("'"+name+"'"+" was not found.");
        }
        System.out.println(playlist.getSongPlaying().toString()+" is playing.");
    }

    /**
     * Clears the playlist
     * @param playlist playlist
     */
    public static void C(SongLinkedList playlist)
    {
        playlist.deleteAll();
        System.out.println("Playlist cleared.");
    }

    /**
     * Calls shuffle on playlist
     * @param playlist playlist
     */
    public static void S(SongLinkedList playlist)
    {
        if(playlist.getSize()==0)
        {
            System.out.println("The list is empty.");
            return;
        }
        playlist.shuffle();
        System.out.println("The playlist has been shuffled.");
    }

    /**
     * Plays a random song on the playlist
     * @param playlist playlist
     */
    public static void Z(SongLinkedList playlist) {
            try {
                playlist.random();
                System.out.println("Playing a random song...");
                if (playlist.getSize() != 0) {
                    System.out.println(playlist.getSongPlaying().toString() + " is " +
                            "playing.");
                } else {
                    System.out.println("The playlist is empty.");
                }
            }
            catch(Exception NullPointerException)
        {
            System.out.println("There are no songs in the playlist.");

        }


    }

    /**
     * Returns the total size of the playlist
     * @param playlist playlist
     */
    public static void T(SongLinkedList playlist)
    {
        System.out.println("There are "+playlist.getSize()+" song(s) in the " +
                "playlist.");
    }

    public static void main(String[] args)
    {
        Scanner sc= new Scanner(System.in);
        String input ="";
        SongLinkedList playlist = new SongLinkedList();
        while(!input.equals("Q"))
        {
            printMenu();
            input=sc.nextLine().toUpperCase();
            try {
                switch (input) {
                    case "A":
                        A(playlist);
                        break;
                    case "F":
                        F(playlist);
                        break;
                    case "B":
                        B(playlist);
                        break;
                    case "R":
                        R(playlist);
                        break;
                    case "L":
                        L(playlist);
                        break;
                    case "C":
                        C(playlist);
                        break;
                    case "Z":
                        Z(playlist);
                        break;
                    case "T":
                        T(playlist);
                        break;
                    case "S":
                        S(playlist);
                        break;
                    case "P":
                        P(playlist);
                        break;
                    case "Q":
                        break;
                    default:
                        System.out.println("Invalid command.");
                        break;
                }
            }
            catch (Exception e)
            {
                System.out.println("Some exception occurred");
                e.printStackTrace();
            }


        }
System.out.println("Program terminated.");
    }
}
