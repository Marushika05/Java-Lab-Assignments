import java.util.*;

class Song {
    private final String id;
    private final String title;
    private final String artist;

    public Song(String id, String title, String artist) {
        this.id = id;
        this.title = title;
        this.artist = artist;
    }

    public String getId() { return id; }
    public String getTitle() { return title; }
    public String getArtist() { return artist; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Song song)) return false;
        return Objects.equals(id, song.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return title + " - " + artist;
    }
}

class MediaPlayerEngine {
    private final LinkedList<Song> queue;
    private final Deque<Song> history; // LIFO playback history
    private ListIterator<Song> cursor;
    private Song currentSong;

    public MediaPlayerEngine() {
        this.queue = new LinkedList<>();
        this.history = new ArrayDeque<>();
        this.cursor = queue.listIterator();
    }

    /**
     * Inserts a track to the very front for immediate playback.
     * Re-synchronizes iterator cursor.
     */
    public void playNext(Song song) {
        queue.addFirst(song);
        resetCursor();
    }

    public void append(Song song) {
        queue.addLast(song);
        resetCursor();
    }

    /**
     * Traverses forward without index-based get(i) overhead.
     */
    public Song nextTrack() {
        if (cursor.hasNext()) {
            if (currentSong != null) {
                history.push(currentSong);
            }
            currentSong = cursor.next();
            return currentSong;
        }
        return null; // End of queue
    }

    /**
     * Traverses backward through the active playlist.
     */
    public Song previousTrack() {
        if (cursor.hasPrevious()) {
            currentSong = cursor.previous();
            return currentSong;
        }
        return null; // Beginning of queue
    }

    /**
     * Removes duplicates preserving the original insertion order of first occurrences.
     * Achieves O(n) runtime complexity using HashSet lookups.
     */
    public void deduplicateQueue() {
        Set<String> seenIds = new HashSet<>();
        Iterator<Song> it = queue.iterator();

        while (it.hasNext()) {
            Song s = it.next();
            if (!seenIds.add(s.getId())) {
                it.remove();
            }
        }
        resetCursor();
    }

    public Song getRecentlyPlayed() {
        return history.peek();
    }

    public List<Song> getQueueSnapshot() {
        return Collections.unmodifiableList(queue);
    }

    private void resetCursor() {
        this.cursor = queue.listIterator();
        this.currentSong = null;
    }
}
