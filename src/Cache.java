import java.util.*;

public class Cache {

    private int capacity;
    private Queue<String> queue;
    private HashSet<String> contents;

    public Cache(int capacity) {
        this.capacity = capacity;
        this.queue = new LinkedList<>();
        this.contents = new HashSet<>();
    }

    public boolean contains(String block) {
        return contents.contains(block);
    }

    public void insert(String block) {

        if(contents.contains(block))
            return;

        if(queue.size() >= capacity) {
            String evicted = queue.poll();
            contents.remove(evicted);
        }
        queue.offer(block);
        contents.add(block);
    }
    public String getState() {
        return queue.toString();
    }
}
