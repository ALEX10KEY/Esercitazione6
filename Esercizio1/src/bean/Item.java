package bean;

public class Item implements Comparable<Item> {

	protected int id;
	protected Integer weight;

	public Item(int i, int p) {
		super();
		id = i;
		weight = p;
	}

	public String toString() {
		return id + ":" + weight;
	}
	
	public int compareTo(Item arg0) {
		return arg0.weight.compareTo(weight);
	}
	
}
