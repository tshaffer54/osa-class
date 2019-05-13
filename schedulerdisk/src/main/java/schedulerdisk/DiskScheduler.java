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
        List<String> req = new ArrayList<>(Arrays.asList(requestQueue.split(",")));
        List<Integer> request = new ArrayList<>();
        for (int index = 0; index < req.size(); index++) {
            request.add(Integer.parseInt(req.get(index)));
        }
        while (request.size() != 0) {
            int start = this.currentCylinder;
            int next = 0;
            for (Integer num : (request)) {
                if (num < this.currentCylinder) {
                    if (num > next) {
                        next = num;
                    }
                } else {
                    int min_idx = request.indexOf(Collections.min(request));
                    if (request.get(min_idx) > start) {
                        next = request.get(min_idx);
                    }

                }
            }
            totalMoves += Math.abs(this.currentCylinder - next);
            request.remove((Integer) next);
            this.currentCylinder = next;
        }
    }

    public void useLOOK(String requestQueue) {
        List<String> req = new ArrayList<>(Arrays.asList(requestQueue.split(",")));
        List<Integer> request = new ArrayList<>();
        for (int index = 0; index < req.size(); index++) {
            request.add(Integer.parseInt(req.get(index)));
        }
        while (request.size() != 0) {
            int start = this.currentCylinder;
            int next = 0;
            for (Integer num : (request)) {
                if (num < this.currentCylinder) {
                    if (num > next) {
                        next = num;
                    }
                } else {
                    int min_idx = request.indexOf(Collections.min(request));
                    if (request.get(min_idx) > start) {
                        next = request.get(min_idx);
                    }

                }
            }
            totalMoves += Math.abs(this.currentCylinder - next);
            request.remove((Integer) next);
            this.currentCylinder = next;
        }
    }

    public void useCLOOK(String requestQueue) {
        List<String> req = new ArrayList<>(Arrays.asList(requestQueue.split(",")));
        List<Integer> request = new ArrayList<>();
        for (int index = 0; index < req.size(); index++) {
            request.add(Integer.parseInt(req.get(index)));
        }
        while (request.size() != 0) {
            int start = this.currentCylinder;
            int change = 0;
            int next = 0;
            for (Integer num : (request)) {
                if (num < this.currentCylinder) {
                    if (num > next) {
                        next = num;
                    }
                } else {
                    int min_idx = request.indexOf(Collections.min(request));
                    if (request.get(min_idx) > start) {
                        change = this.currentCylinder;
                        next = this.cylinders -1;
                    }

                }
            }
            if (next == this.cylinders-1) {
                totalMoves += change;
            } else {
                totalMoves += Math.abs(this.currentCylinder - next);
            }
            request.remove((Integer) next);
            this.currentCylinder = next;
        }
    }

}
