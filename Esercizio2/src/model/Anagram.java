package model;

import java.util.*;

import dao.SimpleDAO;

public class Anagram {
	
	private Set<String> words;
	private List<String> result ;
	private int steps;
	
	public Anagram() {
		words = new HashSet<String>();
		result = new LinkedList<String>();
		steps=0;
	}

	public int getSteps() {
		return steps;
	}

	public void load() {
		SimpleDAO dao = new SimpleDAO();
		for (String s : dao.getWordsFromDatabase())
			words.add(s);
	}
	
	public void getAnagram(String word){
		
		//Preparare la prima chiamata ricorsiva
		
		List<String> letters = new LinkedList() ;
		for ( int i=0;i<word.length();i++) {
			letters.add(word.substring(i, i+1));
		}
		
		this.recurse("", letters);
		/*
		//restituire i risultati
		return result ;
		*/
		
	}
	
	/**
	 * Funziona ricorsiva per il calcolo degli anagrammi (v. figura 
	 * sulle slide. Riceve la parte inizilae dell'anagramma che è già
	 * stato definito nei livelli risìcorsivi superiori, e la lista delle
	 * lettere che sono ancora da anagrammare.
	 * Prende una per una tali lettere, le aggiunge alla parola, e 
	 * lancia la ricorsione
	 * Quando non ci sono più lettere da inserire, l'anagramma è
	 * completo e ......
	 * 
	 * @param word prefisso di parola già definito
	 * @param letters lettere ancora da inserire
	 */
	private void recurse( String word, List<String> letters){
		steps++;
		
		
		
		if (letters.size()==0) {
			result.add(word);
		} else {
			
			
			
			for(String letter : letters) {
				
				/*
				SimpleDAO dao = new SimpleDAO();
				if ( !dao.trovaParoleCheInizianoConPrefisso( (word+letter) ) )
					return ;
				*/
				
				List<String> subset = new LinkedList<String>(letters);
				subset.remove(letter);
				recurse( word+letter, subset);
			}
		}	
	}

	public List<String> getResult() {
		
		List<String> risultati = new LinkedList<String>();
		
		for (String s : result) {
			
			if ( !risultati.contains(s) && this.words.contains(s) )
				risultati.add(s) ;
		}
		
		//Collections.sort(risultati);
		
		return risultati;
	}
	
}
