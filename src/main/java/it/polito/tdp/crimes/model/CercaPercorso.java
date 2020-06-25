package it.polito.tdp.crimes.model;

import java.util.List;

import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;

public class CercaPercorso {
	
	Arco arco;
	String origine;
	String destinazione;
	private SimpleWeightedGraph<String, DefaultWeightedEdge> grafo;
	private List<String> sequenza;
	private int peso;
	public CercaPercorso(Arco arco,SimpleWeightedGraph<String, DefaultWeightedEdge> grafo) {
		super();
		this.arco = arco;
		this.origine = arco.getId1();
		this.destinazione = arco.getId2();
		this.grafo = grafo;
	}
	 public void inizializza() {
		 peso=0;
		 sequenza.add(origine);
		 
	 }
	 public void recursive(int livello) {
		 if(livello>=grafo.edgeSet().size()) {
			 return;
		 }
		 
		 for(String s:Graphs.neighborListOf(grafo, sequenza.get(sequenza.size()-1))) {
			 if(!sequenza.contains(s)) {
				 sequenza.add(s);
			 }
		 }
		 
		 
	 }
}
