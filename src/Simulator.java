import java.io.*;
import java.util.*;

public class Simulator {

    static Cache L1 = new Cache(32);
    static Cache L2 = new Cache(128);
    static Cache L3 = new Cache(512);
    static int latency=0;
    static int ramAccesses = 0;
    static int totalCycles = 0;

    public static void main(String[] args) throws Exception {
        Queue<Task> readyQueue = loadTasks("input_task2.txt");

        while(!readyQueue.isEmpty()) {
            Task currentTask = readyQueue.poll();
            while(currentTask.burstRemaining > 0) {
                totalCycles++;
                String memoryRequest = currentTask.getNextMemoryRequest();
                processMemoryRequest(memoryRequest);
                currentTask.burstRemaining--;
                System.out.println(
                        "\nCycle " + totalCycles +
                                " - Running: " + currentTask.id +
                                " Requesting: " + memoryRequest);
                System.out.println("L1: " + L1.getState());
                System.out.println("L2: " + L2.getState());
                System.out.println("L3: " + L3.getState());
                System.out.println("----------------------------------");
            }
            System.out.println(
                    currentTask.id + " COMPLETED\n");
        }

        System.out.println("\n=====FINAL RESULTS =====");
        System.out.println("Scheduler:FCFS");
        System.out.println("Tasks Completed:15 (as seen in txt file)");
        System.out.println("Total Cycles: " + totalCycles);
        System.out.println("RAM Accesses: " + ramAccesses);
        System.out.println("Latency: "+latency);
        System.out.println("Final L1: " + L1.getState());
        System.out.println("Final L2: " + L2.getState());
        System.out.println("Final L3: " + L3.getState());
    }
    private static void processMemoryRequest(String block) {
        if(L1.contains(block)) {
            System.out.println("L1 HIT (4 cycles)");
            latency +=4;
            return;
        }
        if(L2.contains(block)) {
            System.out.println("L2 HIT (12 cycles)");
            latency += 12;
            L1.insert(block);
            return;
        }
        if(L3.contains(block)) {
            System.out.println("L3 HIT (40 cycles)");
            latency += 40;
            L1.insert(block);
            return;
        }
        System.out.println("MISS ->RAM ACCESS(200 cycles)");
        latency += 200;
        ramAccesses++;
        L1.insert(block);
    }

    private static Queue<Task> loadTasks(String filename)
            throws Exception {
        Queue<Task> tasks = new LinkedList<>();
        BufferedReader br = new BufferedReader(new FileReader(filename));
        String line;
        while((line = br.readLine()) != null) {
            line = line.trim();
            if(line.isEmpty())
                continue;
            String[] parts = line.split("\\s+");
            String id = parts[1];
            int burst = Integer.parseInt(parts[3]);
            List<String> memBlocks=new ArrayList<>();
            for(int i = 5; i < parts.length; i++) {
                memBlocks.add(parts[i]);
            }
            tasks.add(new Task(id, burst, memBlocks));
        }

        br.close();
        return tasks;
    }
}