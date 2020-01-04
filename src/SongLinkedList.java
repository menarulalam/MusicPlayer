
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.util.HashMap;

public class SongLinkedList {
private SongNode head;
private SongNode tail;
private SongNode cursor;
private int size;
private Song songPlaying;
private Clip clip;
private HashMap<String, Integer> histogram;
    /**
     * Constructs an empty SongLinkedList
     */
    public SongLinkedList(){}
    /**
     * Sets song playing
     * @param songPlaying The song object whose audio is playing
     */
    public void setSongPlaying(Song songPlaying) {
        this.songPlaying = songPlaying;
        update(songPlaying.getName());


    }
    private void update(String title){
        histogram.putIfAbsent(title, 0);
        histogram.replace(title, histogram.get(title)+1);

    }

    /**
     * Gets song playing
     * @return song playing
     */
    public Song getSongPlaying()
    {
        return songPlaying;
    }

    /**
     * Stops previous song and plays input song
     * <dl>
     * <dt><b>Postconditions:</b></dt>
     * <dd>Song is playing</dd>
     * </dl>
     * @param song Song to be played
     */
    private void startSongAudio(Song song)
    {
        try {
            try{
            clip.stop();}
            catch (Exception ignored)
            {

            }
            setSongPlaying(song);
            AudioInputStream AIS = AudioSystem.getAudioInputStream(
                    this.getClass().getResource(song.getName()+".wav"));
            clip = AudioSystem.getClip();
            clip.open(AIS);
            clip.start();
        }
        catch (Exception ignored) {}
    }

    /**
     * Gets head
     * @return head
     */
    public SongNode getHead() {
        return head;
    }

    /**
     * Sets head
     * @param head SongNode head
     */
    public void setHead(SongNode head) {
    if(cursor.equals(this.head))
    {
        cursor=head;
    }
    this.head = head;
        if(getSize()==0)
        {
            tail=head;
            size++;
            cursor=head;
        }

    }

    /**
     * Gets tail
     * @return SongNode tail
     */
    public SongNode getTail() {
        return tail;
    }
    public void setTail(SongNode tail) {
    if (cursor.equals(this.tail))
    {
        cursor=tail;
    }
    this.tail=tail;
        if (getSize() == 0) {
head=tail;
size++;
cursor=head;
        }
    }

    /**
     * Gets what the cursor is pointing to
     * @return SongNode cursor's reference
     */
    public SongNode getCursor(){
        return cursor;
    }

    /**
     * Sets the cursor to some node
     * @param cursor node to be set
     */
    public void setCursor(SongNode cursor) {
        this.cursor = cursor;
    }

    /**
     * Returns size of the linked list
     * @return size private instance variable
     */
    public int getSize() {
        return size;
    }

    /**
     * Sets size
     * @param size size
     */
    public void setSize(int size) {
        this.size = size;
    }

    /**
     * Takes in song to be played, searches through linked list for the song,
     * if found calls the audio method otherwise throws an
     * IllegalArgumentException
     * @param name name of song to be played
     * @throws IllegalArgumentException When song is not found
     * <dt><b>Preconditions:</b></dt>
     * <dd>The song matches a song name in the playlist and an actual file
     * is associated with it</dd>
     * </dl>
     * <dt><b>Postconditions:The song is now playing</b></dt>
     * <dd></dd>
     * </dl>
     */
    public void play (String name) throws IllegalArgumentException {
        SongNode temp = getHead();
        if(temp.getData().getName().equals(name)) {
            startSongAudio(temp.getData());
            return;
        }
        while(!temp.equals(getTail()))
        {
            temp=temp.getNext();
            if(temp.getData().getName().equals(name))
            {
                startSongAudio(temp.getData());
                return;
            }
        }
throw new IllegalArgumentException();

    }

    /**
     * Moves the cursor forward if the list is not empty and the cursor is
     * not at the end
     * <dt><b>Preconditions:</b></dt>
     * <dd>The list is not empty</dd>
     * </dl>
     * <dt><b>Postconditions:</b></dt>
     * <dd>The cursor remains at the end or moves once forwards</dd>
     * </dl>
     * @throws NullCursorException if the list is empty
     */
    public void cursorForwards() throws NullCursorException {
        if (cursor==null)
            throw new NullCursorException();
        if (cursor.equals(tail))
        {
            return;
        }
        cursor=cursor.getNext();
    }

    /**
     * Moves the cursor backwards if the list is not empty and the cursor is
     * not at the beginning
     * <dt><b>Preconditions:</b></dt>
     * <dd>The list isn't empty</dd>
     * </dl>
     * <dt><b>Postconditions:</b></dt>
     * <dd>The cursor is at the start or has moved back once</dd>
     * </dl>
     * @throws NullCursorException if the lis tis empty
     */
    public void cursorBackwards() throws NullCursorException
    {
        if (cursor==null)
            throw new NullCursorException();
        if (cursor.equals(head))
        {
            return;
        }
        cursor=cursor.getPrev();
    }

    /**
     * Inserts a song after the cursor position.
     * @param newSong song to be added
     * @throws NullSongException if the new Song is null
     * <dt><b>Preconditions:</b></dt>
     * <dd>The new song has been instantiated</dd>
     * </dl>
     * <dt><b>Postconditions:</b></dt>
     * <dd>The song is added after the new node if valid, the cursor is on
     * the new song, and the order of the playlist preserved </dd>
     * </dl>
     */
    public void insertAfterCursor(Song newSong) throws IllegalArgumentException{
        if(newSong==null)
        {
            throw new IllegalArgumentException();
        }
        SongNode newNode = new SongNode();
        newNode.setData(newSong);

    if (getSize()==0) {
            head=newNode; //If list is empty start the list
            cursor=newNode;
            tail=newNode;

        } else {

            if (cursor.equals(tail)) { //if the cursor is at the end, make
                // a new tail
                newNode.setPrev(cursor);
                newNode.setNext(null);
                cursor.setNext(newNode);
                tail = newNode;
                cursor=newNode;
            } else {
                newNode.setNext(cursor.getNext()); //insert it between otherwise
                cursor.setNext(newNode);
                newNode.getNext().setPrev(newNode);
                newNode.setPrev(cursor);
                cursor=newNode;
            }

        }
    size++;
    }

    /**
     * Removes the song at the cursor
     * @return song referenced by cursor
     * @throws NullCursorException if the list is empty
     * <dt><b>Preconditions:</b></dt>
     * <dd>The cursor is not null</dd>
     * </dl>
     * <dt><b>Postconditions:</b></dt>
     * <dd>The cursor pointed song if it exists is removed and the cursor
     * points to next node or previous node if next doesn't exist</dd>
     * </dl>
     */
    public Song removeCursor() throws NullCursorException {
        Song returnedSong=cursor.getData();
        if (size == 1)
        {
            head=null; cursor=null; tail=null; size--;
            return returnedSong;
        }

        if(size!=0) {
            if(cursor.equals(head)&&head.equals(tail))
            {
                head=null;
                tail=null;
                cursor=null;
                size--;
            }
            else if (cursor.equals(tail)) {
                tail = cursor.getPrev();
                tail.setNext(null);
                cursor = tail;
                size--;
            }
            else if(cursor.equals(head))
            {
                head=cursor.getNext();
                head.setPrev(null);
                cursor=head;
                size--;
            }
            else {
                SongNode prev = cursor.getPrev();
                SongNode next = cursor.getNext();
                prev.setNext(next);
                next.setPrev(prev);
                cursor = next;
                size--;
            }
            return returnedSong;
        }
        throw new NullCursorException();
    }
    private int generateRandomIndex()
    {
        return (int)Math.floor(getSize()*Math.random());
    }
    public void insert(SongNode newNode) {
        if (size!=0) {
            tail.setNext(newNode);
            newNode.setPrev(tail);
            newNode.setNext(null);
            tail = newNode;
            size++;
        }
        else
        {
            tail=newNode;
            head=newNode;
            cursor=head;
            size++;
        }
    }

    /**
     * Only to be used as a helper method for shuffle (warning: does not
     * modify the cursor)
     * @param index index of element to be removed
     * @return Song to be removed
     */
    public Song remove(int index)
    {
        SongNode terminalNode = head;
        Song removedSong = new Song();
        if(size!=0) {

            for (int i = 0; i <= index&&terminalNode!=tail; i++) {
                terminalNode = terminalNode.getNext();
            }
             removedSong = terminalNode.getData();
            if(head==tail&&terminalNode==tail)
            {
                head=null; tail=null;
            }
            else if (terminalNode == tail) {
                tail = terminalNode.getPrev();
                tail.setNext(null);

            } else if (terminalNode == head) {
                head = terminalNode.getNext();
                head.setPrev(null);

            } else {
                terminalNode.getPrev().setNext(terminalNode.getNext());
                terminalNode.getNext().setPrev(terminalNode.getPrev());
            }
            size--;
        }

                return removedSong;
    }

    /**
     * Gets a random song and plays it
     * @return Random song
     * <dt><b>Postconditions:</b></dt>
     * <dd>The randomly selected song is now playing</dd>
     * </dl>
     */
    public Song random() {
        if (getSize() != 0) {
            int index = generateRandomIndex();
            SongNode temp = head;
            for (int i = 0; i < index; i++) {
                temp = temp.getNext();
            }
            songPlaying = temp.getData();
            startSongAudio(temp.getData());
            return temp.getData();
        }
        return null;
    }

    /**
     * Randomly shuffles the playlist
     * <dt><b>Postconditions:</b></dt>
     * <dd>Cursor references same song and order is shufffled</dd>
     * </dl>
     */
    public void shuffle() {
    if (getSize() != 0) {
        SongLinkedList shuffled = new SongLinkedList();
        Song oldCursorSong = cursor.getData();
        int oldSize = size;
        while (getSize() != 0) {
            SongNode random = new SongNode();
            random.setData(this.remove(generateRandomIndex()));
            shuffled.insert(random);
            if (shuffled.getTail().getData().equals(oldCursorSong)) {
                cursor = shuffled.getTail();
            }
        }
        this.head = shuffled.head;
        this.tail = shuffled.tail;
        size = oldSize;
    }
}

    /**
     * Deletes entire playlist
     * <dt><b>Postconditions:</b></dt>
     * <dd>All songs have been removed</dd>
     * </dl>
     */
    public void deleteAll()
{
    head=null; tail=null; cursor=null; size=0;
}

    /**
     * Returns a formatted version of the playlist
     * @return the playlist string formatted
     */
    public String toString() {
    String output = "Playlist: \n Song                     | Artist            " +
            "     " +
            "  " +
            "| Album                    | Length (s)\n" +
            "---------------------------------------------------------------" +
            "-----------------------------\n";
   if(getSize()!=0){
    SongNode temp = getHead();
    System.out.println(temp);
    System.out.println(temp.getData());
    for(int i=0; i<size; i++) {
        Song song = temp.getData();
        System.out.println(temp.getData());
        output += String.format("%-27s%-27s%-26s%5d", song.getName(),
                song.getArtist()
                , song.getAlbum(), song.getLength());
        if (temp==getCursor())
            output+=" <-";
        output+="\n";
        temp = temp.getNext();
    }}
    return output;
}
/**
 * Prints toString()
 *
 */
public void printPlaylist()
{
    System.out.print(toString());
}
}
