import java.util.*;
public class Task {
    String id;
    int burstRemaining;
    List<String> memoryBlocks;
    int currentMemoryIndex;
    public Task(String id, int burst, List<String> memoryBlocks) {
        this.id = id;
        this.burstRemaining = burst;
        this.memoryBlocks = memoryBlocks;
        this.currentMemoryIndex = 0;
    }
    public String getNextMemoryRequest() {
        String block = memoryBlocks.get(currentMemoryIndex);
        currentMemoryIndex = (currentMemoryIndex + 1) % memoryBlocks.size();
        return block;
    }
}
