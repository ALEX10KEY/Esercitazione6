import java.util.List;
import dao.SimpleDAO;
import bean.Group;
import bean.Item;

public class Main {
	
	private List<Item> oggetti;
	private Group[] group;
	private Group[] bestGroup;
	private int bestDiff;
	private long counter;
	private int bound;
	private long startTime;
	
	public static void main(String[] args) {
		Main m = new Main();
		m.run();
	}

	private void run() {
		SimpleDAO d = new SimpleDAO();
		oggetti = d.getObjectList();
		
		group = new Group[2];
		group[0] = new Group();
		group[1] = new Group();
		bestGroup = new Group[2];
		bestGroup[0] = new Group();
		bestGroup[1] = new Group();
		
		kickStart();
		System.out.println("G1: " + bestGroup[0]);
		System.out.println("G2: " + bestGroup[1]);
		//System.exit(0);
		this.startTime = System.nanoTime();
		recPath(0);
		
		System.out.println("BEST SOLUTION ("+ counter + " calls, time: " + ( System.nanoTime() - startTime)/1e9 + " seconds )");
		System.out.println("G1: " + bestGroup[0]);
		System.out.println("G2: " + bestGroup[1]);
		
	}

	protected void kickStart() {
		/* 1° SOLUZIONE:
		 * Carico in memoria una soluzione migliore di partenza ponendoci nel caso maggiore in cui
		 * assegno tutti gli oggetti a un solo gruppo
		 * 
		for(Item i : oggetti) 
			bestGroup[0].addItem(i);
		/*
		 * Calcolo la migliore differenza e definisco un bound
		 *
		bestDiff = bestGroup[0].getTotWeight();
		bound = bestDiff;
		*/
		
		/* 2° SOLUZIONE: soluzione di partenza "greedy", golosa
		 * Carico in memoria una soluzione migliore di partenza ponendoci in un caso più furbo
		 * rispetto al precedente in cui in base al peso dei due gruppi, inserisco gli oggetti dell'elenco 
		 * , ordinati in maniera decrescente in base al peso, laddove il peso totale sia minore
		 */
		for(Item i : oggetti) { 
			if ( bestGroup[0].getTotWeight() < bestGroup[1].getTotWeight() )
				bestGroup[0].addItem(i);
			else
				bestGroup[1].addItem(i);
		}
		/*
		 * Calcolo la migliore differenza dei pesi tra i due gruppi e definisco un bound
		 * pari al massimo dei pesi contenuti nei due gruppi
		 */
		bestDiff = Math.abs(bestGroup[0].getTotWeight()-bestGroup[1].getTotWeight());
		bound = Math.max(bestGroup[0].getTotWeight(), bestGroup[1].getTotWeight());
		
	}

	/**
	 * Procedura ricorsiva per l'individuazione della prima soluzione migliore al problema dato.
	 *  
	 * @param s intero relativo alla posizione dell'elemento della lista di oggetti da aggiungere
	 * @return true nel caso di risultato migliore trovato, altrimenti false. fatto questo per migliorare le prestazioni
	 */
	private boolean recPath(int s) {
		++counter;
		
		if ( bestDiff == 0 )
			return true;
		
		
		if ( Math.max(group[0].getTotWeight(), group[1].getTotWeight()) > bound )
			return false;
		
		if ( s == oggetti.size() ) {
			int diff = Math.abs(group[0].getTotWeight()-group[1].getTotWeight());
			
			if (diff < bestDiff) {
				bestDiff = diff;
				bound = Math.max(group[0].getTotWeight(), group[1].getTotWeight()) ;
				
				bestGroup[0].copyGroup(group[0]);
				bestGroup[1].copyGroup(group[1]);
				
				System.out.println();
				System.out.println("G1: " + group[0]);
				System.out.println("G2: " + group[1]);
			}
			
			if (bestDiff == 0)
				return true;
			else
				return false;
		}
		
		for(int t=0; t<2;++t){
			group[t].addItem(oggetti.get(s));		// step
			if (recPath(s+1) == true)				// call
				return true;
			group[t].removeItem(oggetti.get(s));	// backtrack!!!!
		}
		
		return false;
		
	}
}
