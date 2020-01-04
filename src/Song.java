
public class Song {
    private String name;
    private String artist;
    private String album;
    private int length;

    /**
     * Constructs a song object
     */
    public Song() {}

    /**
     * Gets song name
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets song name
     * @param name name of song
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets artist name
     * @return artist's name
     */

    public String getArtist() {
        return artist;
    }

    /**
     * Sets artist's name
     * @param artist artist's name
     */

    public void setArtist(String artist) {
        this.artist = artist;
    }

    /**
     * Gets album name
     * @return album's name
     */

    public String getAlbum() {
        return album;
    }

    /**
     * Sets album name
     * @param album album's name
     */
    public void setAlbum(String album) {
        this.album = album;
    }

    /**
     * Returns song's length in integer format
     * @return song's length
     */

    public int getLength() {
        return length;
    }

    /**
     * Sets song legnth to an inputted integer
     * @param length song's length
     */
    public void setLength(int length) {
        this.length = length;
    }
    public boolean equals(Object o)
    {
        if (!(o instanceof Song))
            return false;
        Song o1 = (Song) o;
        return name.equals(o1.getName())&&album.equals(o1.getAlbum())&&artist.
                equals(o1.getArtist())&&(length==o1.getLength());

    }

    public String toString()
    {
        return name+" by "+artist;
    }
}
