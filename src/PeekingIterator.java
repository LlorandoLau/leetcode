import java.util.Iterator;

class PeekingIterator implements Iterator<Integer> {
    private Integer peek;
    private Iterator<Integer> iterator;

    public PeekingIterator(Iterator<Integer> iterator) {
        // initialize any member here.
        this.iterator = iterator;
    }

    // Returns the next element in the iteration without advancing the iterator.
    public Integer peek() {
        if (peek == null) peek = iterator.next();
        return peek;
    }

    // hasNext() and next() should behave the same as in the Iterator interface.
    // Override them if needed.
    @Override
    public Integer next() {
        if (peek == null) return iterator.next();
        Integer next = peek;
        peek = null;
        return next;
    }

    @Override
    public boolean hasNext() {
        return peek != null || iterator.hasNext();
    }
}