package ua.com.alevel.graph;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class NodeIndexer {

    private final Map<String, Integer> map = new HashMap<>();
    private int lastIndex = 1;

    public void add(String name) {
        if (map.containsKey(name)) {
            throw new IllegalArgumentException("There are this name in nodes");
        }
        map.put(name, lastIndex);
        lastIndex++;
    }

    public String getName(Integer id) {
        for (String name :
                map.keySet()) {
            if (Objects.equals(map.get(name), id)) {
                return name;
            }
        }
        throw new IllegalArgumentException("No such index");
    }

    public int getIndex(String name) {
        if (!map.containsKey(name)) {
            throw new IllegalArgumentException("No such name");
        }
        return map.get(name);
    }
}
