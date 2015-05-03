package bean;

import java.util.HashSet;
import java.util.Set;

public class Group {

	protected int totWeight; //peso totale
	protected Set<Item> items; //insieme di oggetti Item nel gruppo
	
	public Group() {
		items = new HashSet<Item>();
	}
	
	public void addItem(Item i) {
		items.add(i);
		totWeight += i.weight;
	}
	
	public void removeItem(Item i) {
		items.remove(i);
		totWeight -= i.weight;
	}
	
	public String toString() {
		return "Group [totWeight=" + totWeight + ", items=" + items + "]";
	}
	
	public int getTotWeight() {
		return totWeight;
	}
	
	public void copyGroup(Group g) {
		items.clear();
		items.addAll(g.items);
		totWeight = g.totWeight;
	}
	
	
}
