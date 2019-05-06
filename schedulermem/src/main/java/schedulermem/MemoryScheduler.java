package schedulermem;

import java.util.*;

/**
 * @author Tristan Shaffer
 */
public class MemoryScheduler {

    private int pageFaultCount;
    private int frameCount;

    public MemoryScheduler(int frames) {
        this.pageFaultCount = 0;
        this.frameCount = frames;
    }

    public int getPageFaultCount() {
        return this.pageFaultCount;
    }

    public void useFIFO(String referenceString) {
        ArrayList<Integer> queue = new ArrayList<>();
        List<String> list;
        int next;
        list = new ArrayList<>(Arrays.asList(referenceString.split(",")));
        while (!list.isEmpty()) {
            if (queue.size() < this.frameCount) {
                next = Integer.parseInt(list.get(0));
                if (queue.contains(next)) {
                    list.remove(0);
                } else {
                    queue.add(next);
                    list.remove(0);
                    this.pageFaultCount ++;
                }
            } else {
                next = Integer.parseInt(list.get(0));
                if (queue.contains(next)) {
                    list.remove(0);
                } else {
                    queue.remove(0);
                    queue.add(next);
                    list.remove(0);
                    this.pageFaultCount ++;
                }
            }
        }
    }

    public void useOPT(String referenceString) {
        ArrayList<Integer> queue = new ArrayList<>();
        List<String> list;
        int next;
        list = new ArrayList<>(Arrays.asList(referenceString.split(",")));
        while (!list.isEmpty()) {
            if (queue.size() < this.frameCount) {
                next = Integer.parseInt(list.get(0));
                if (queue.contains(next)) {
                    list.remove(0);
                } else {
                    queue.add(next);
                    list.remove(0);
                    this.pageFaultCount++;
                }
            } else {
                next = Integer.parseInt(list.get(0));
                if (queue.contains(next)) {
                    list.remove(0);
                } else {
                    list.remove(0);
                    List<Integer> opt = new ArrayList<>();
                    for (Integer x:queue) {
                       int index = list.indexOf(Integer.toString(x));
                       opt.add(index);
                    }
                    if (opt.contains(-1)) {
                        int optIndex = opt.indexOf(-1);
                        queue.remove((optIndex));
                        queue.add(next);
                        this.pageFaultCount++;
                    } else {
                        int optIndex = opt.indexOf(Collections.max(opt));
                        queue.remove(optIndex);
                        queue.add(next);
                        this.pageFaultCount++;
                    }
                }
            }
        }
    }

    public void useLRU(String referenceString) {
        ArrayList<Integer> queue = new ArrayList<>();
        List<String> list;
        int next;
        list = new ArrayList<>(Arrays.asList(referenceString.split(",")));
        while (!list.isEmpty()) {
            if (queue.size() < this.frameCount) {
                next = Integer.parseInt(list.get(0));
                if (queue.contains(next)) {
                    int index = queue.indexOf(next);
                    list.remove(0);
                    queue.remove(index);
                    queue.add(next);

                } else {
                    queue.add(next);
                    list.remove(0);
                    this.pageFaultCount ++;
                }
            } else {
                next = Integer.parseInt(list.get(0));
                if (queue.contains(next)) {
                    list.remove(0);
                    queue.remove((Integer) next);
                    queue.add(next);
                } else {
                    queue.remove(0);
                    queue.add(next);
                    list.remove(0);
                    this.pageFaultCount ++;
                }
            }
        }
    }

}
