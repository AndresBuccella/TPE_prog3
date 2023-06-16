package servicios;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class UnionFind<T> {
	private Map<T, T> parent;
	 
	public UnionFind() {
		this.parent = new HashMap<>();
	}

    public void makeSet(Iterator<T> universe) {

    	while(universe.hasNext()) {
    		T elem = universe.next();
    		parent.put(elem, elem);
    	}
    }
    
    public int numberOfSets() {
    	int count = 0;

        for (T elem : parent.keySet()) {
            if (elem.equals(parent.get(elem))) {
                count++;
            }
        }

        return count;
    }
 
    public T find(T elem) {

        if (parent.get(elem).equals(elem)) {
            return elem;
        }
        return find(parent.get(elem));
    }
 
    public void union(T e1, T e2) {

        T x = find(e1);
        T y = find(e2);
 
        parent.put(x, y);
    }
    
    public void reset() {
    	this.parent.clear();
    }
}

