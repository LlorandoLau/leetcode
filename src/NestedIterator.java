import java.util.Iterator;
import java.util.List;

public class NestedIterator implements Iterator<Integer> {
    List<NestedInteger> nestedList;

    public NestedIterator(List<NestedInteger> nestedList) {
        this.nestedList = nestedList;
    }

    @Override
    public Integer next() {
        return 1;
    }

    @Override
    public boolean hasNext() {
        return false;
    }
}
