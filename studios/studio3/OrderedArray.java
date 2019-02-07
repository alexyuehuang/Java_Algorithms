package studio3;

public class OrderedArray<T extends Comparable<T>> implements PriorityQueue<T> {

	public T[] array;
	private int size;
	
	@SuppressWarnings("unchecked")
	public OrderedArray(int maxSize) {
		array = (T[]) new Comparable[maxSize];
		size = 0;
	}
	
	@Override
	public boolean isEmpty() {
		return size==0;
	}

	@Override
	public void insert(T thing) {
		
		if(this.isEmpty()) {
			array[0]=thing;
		}
		int k=0;
		int g=0;
		for(int i=0; i<size; i++) {
			if(array[i].compareTo(thing)==1&&g==0) {
				g=1;
				k=i;
			}
		}
		if(g==0) {
			array[size]=thing;
		}
		else {
			for(int i=size-1; i>=k; i--) {
				array[i+1]=array[i];
			}
			array[k]=thing;
		}
		size++;
		
	}
	
	@Override
	public T extractMin() {
		T a =array[0];
		for(int i=1; i<size; i++) {
			array[i-1]=array[i];
		}
		size--;
		return a;
	}

}
