import java.util.Stack;

public class MyStack {
    Stack<Integer> stack;

    /**
     * Initialize your data structure here.
     */
    public MyStack() {
        stack = new Stack<>();
    }

    /**
     * Push element x onto stack.
     */
    public void push(int x) {
        stack.push(x);
    }

    /**
     * Removes the element on top of the stack and returns that element.
     */
    public int pop() {
        return stack.pop();
    }

    /**
     * Get the top element.
     */
    public int top() {
        return stack.pop();
    }

    /**
     * Returns whether the stack is empty.
     */
    public boolean empty() {
        return stack.isEmpty();
    }
}
