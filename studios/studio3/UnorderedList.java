package studio3;

import java.util.LinkedList;
import java.util.List;

public class UnorderedList<T extends Comparable<T>> implements PriorityQueue<T> {

	public LinkedList<T> list;
	
	public UnorderedList() {
		list = new LinkedList<T>();
	}
	
	@Override
	public boolean isEmpty() {
		boolean i=false;
		if(list.isEmpty()) {
			i=true;
		}
		return i;
	}

	@Override
	public void insert(T thing) {
		list.add(thing);
	}

	@Override
	public T extractMin() {
		if(isEmpty()) {
			return null;
		}
		else {
			list.size();
			T j=list.get(0);
			int q=0;
			for(int i=1; i<list.size(); i++) {
				if(list.get(i).compareTo(j)==-1) {
					j=list.get(i);
					q=i;
				}
			}
			list.remove(q);
			return j;
		}
	}

}
