package application;

import java.util.List;

import model.Anagram;

public class Main {

	public static void main(String[] args) {
		
		Anagram anagram = new Anagram();
		anagram.load();
		
		long tempoAnagrammi = System.nanoTime();
		//anagram.getAnagram("alitare");
		anagram.getAnagram("elapidi");
		tempoAnagrammi = tempoAnagrammi - System.nanoTime();
		
		System.out.println( "INFORMAZIONI PRESTAZIONI\n***************************" );
		System.out.println( "Tempo necessario per anagrammare: " + (tempoAnagrammi/1e9) );
		System.out.println( "Step necessari: " + anagram.getSteps() + "." );
		List<String> words = anagram.getResult();
		
		System.out.println( "\nRISULTATI\n***************************" );
		for ( String w : words ) {
			System.out.println(w);
		}

	}

}
