
public class SongNode {
    private SongNode prev;
    private SongNode next;
    private Song data;

    /**
     * Constructs the SongNode object
     */
    public SongNode(){};

    /**
     * gets previous node
     * @return previous node
     */
    public SongNode getPrev() {
        return prev;
    }

    /**
     * Sets the previous node
     * @param prev previous node
     */
    public void setPrev(SongNode prev) {
        this.prev = prev;
    }

    /**
     * gets next Node
     * @return the next node
     */

    public SongNode getNext() {
        return next;
    }

    /**
     * Sets the next node
     * @param next the next node
     */
    public void setNext(SongNode next) {
        this.next = next;
    }

    /**
     * Gets the song stored in the node
     * @return the Song object stored in the node
     */
    public Song getData() {
        return data;
    }

    /**
     * Sets the data instance variable to the given song
     * @param data Song to be set
     */
    public void setData(Song data) {
        this.data = data;
    }
}
