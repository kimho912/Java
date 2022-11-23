package emporium;

import java.util.HashMap;
import java.util.HashSet;

public class MultiMap<K,V> {
    public void put(K key, V value) {
        if (map.get(key) == null) map.put(key,new HashSet<>());
        // map.put(key) += value; 
    }

    public Object[] get(K key) {
        if (map.get(key) == null) return this.map.get(null).toArray();
        else return this.map.get(key).toArray();
    }

    private HashMap<K,HashSet<V>> map = new HashMap<>();
}
