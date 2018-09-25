import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

class MapSum {
    Map<String, Integer> map;

    /**
     * Initialize your data structure here.
     */
    public MapSum() {
        this.map = new HashMap<>();
    }

    public void insert(String key, int val) {
        map.put(key, val);
    }

    public int sum(String prefix) {
        int sum = 0;
        Iterator<Map.Entry<String, Integer>> it = map.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<String, Integer> e = it.next();
            String key = e.getKey();
            if (key.indexOf(prefix) == 0) sum += e.getValue();
        }
        return sum;
    }
}
