package stack;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class MinStack {
    List list;
    int min;
    PriorityQueue<Integer> pq;

    public MinStack() {
        this.list = new ArrayList();
        pq = new PriorityQueue<>();
        min = Integer.MAX_VALUE;
    }

    public void push(int x) {
        list.add(x);
        pq.add(x);
    }

    public void pop() {
        Integer remove = (Integer) list.remove(list.size() - 1);
        pq.remove(remove);
    }

    public int top() {
        return (Integer) list.get(list.size() - 1);
    }

    public int getMin() {
        return pq.peek();
    }
}
