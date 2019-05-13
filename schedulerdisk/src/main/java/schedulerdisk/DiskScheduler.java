package schedulerdisk;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;

/**
 *
 * @author [name]
 */
public class DiskScheduler {

    private final int cylinders;
    private int currentCylinder;
    private final int previousCylinder;
    private int totalMoves;

    public DiskScheduler(int cylinders, int currentCylinder, int previousCylinder) {
        this.cylinders = cylinders;
        this.currentCylinder = currentCylinder;
        this.previousCylinder = previousCylinder;
        this.totalMoves = 0;
    }

    public int getTotalMoves() {

        return this.totalMoves;
    }

    public void useFCFS(String requestQueue) {
        List<String> request = new ArrayList<>(Arrays.asList(requestQueue.split(",")));
        while (request.size() != 0) {
            var next = Integer.parseInt(request.get(0));
            totalMoves += Math.abs(next - this.currentCylinder);
            request.remove(0);
            this.currentCylinder = next;
        }
    }

    public void useSSTF(String requestQueue) {
        List<String> request = new ArrayList<>(Arrays.asList(requestQueue.split(",")));
        System.out.println(request);
    }

    public void useLOOK(String requestQueue) {
        System.out.println("current: " + this.currentCylinder);
        System.out.println("previous: " + this.previousCylinder);
        List<String> request = new ArrayList<>(Arrays.asList(requestQueue.split(",")));
        for (IntStream.range(0, request.size())) {
            
        }
    }

    public void useCLOOK(String requestQueue) {

        throw new UnsupportedOperationException();
    }

}
