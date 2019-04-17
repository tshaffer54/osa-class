package schedulerproj;
/**
 * @author yasiro01
 */
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * Process scheduler
 * 
 * readyQueue is a list of processes ready for execution
 * rrQuantum is the time quantum used by round-robin algorithm
 * add() and clear() are wrappers around ArrayList methods
 */
public class ProcessScheduler {
    private final ArrayList<SimpleProcess> readyQueue;
    private final int rrQuantum;

    public ProcessScheduler() {
        this.readyQueue = new ArrayList<>();
        this.rrQuantum = 4;
    }

    public void add(SimpleProcess newProcess) {
        this.readyQueue.add(newProcess);
    }

    public void clear() {
        this.readyQueue.clear();
    }

    /**
     * FCFS scheduling algorithm implementation
     * 
     * @return average waiting time for all processes
     */
    public double useFirstComeFirstServe() {
        int i = 0;
        int processTotal = 0;
        int BurstTotal = 0;
        while (i < (this.readyQueue.size()-1)) {
            var process = this.readyQueue.get(i);
            int processBurst = process.getNextBurst();
            processTotal = processTotal + processBurst;
            BurstTotal = processTotal + BurstTotal;
            i++;
        }
        double processAverage = BurstTotal/this.readyQueue.size();
        return processAverage;
    }

    /**
     * SJF scheduling algorithm implementation
     * 
     * @return average waiting time for all processes
     */
    public double useShortestJobFirst() {
        int size = this.readyQueue.size();
        int i = 0;
        int BurstTotal = 0;
        PriorityQueue<Integer> Burst = new PriorityQueue<>();
        while (i != (this.readyQueue.size())) {
            var process = this.readyQueue.get(i);
            var processBurst = process.getNextBurst();
            Burst.add(processBurst);
        }
        while (Burst.size() != 1) {
            int Burstint = Burst.poll();
            BurstTotal = BurstTotal + Burstint;
        }
        
        double processAverage = BurstTotal/this.readyQueue.size();
        return processAverage;
    }

    /**
     * Priority scheduling algorithm implementation
     * 
     * @return average waiting time for all processes
     */
    public double usePriorityScheduling() {
        int size = this.readyQueue.size();
        int i = 0;
        throw new UnsupportedOperationException();
    }

    /**
     * Round-Robin scheduling algorithm implementation
     * 
     * @return average waiting time for all processes
     */
    public double useRoundRobin() {
        throw new UnsupportedOperationException();
    }
}
