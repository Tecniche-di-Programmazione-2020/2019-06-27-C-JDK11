package it.polito.tdp.crimes.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;

import it.polito.tdp.crimes.db.EventsDao;

public class Model {
	private EventsDao dao;
	private List<String> listOfCategories;
	private List <Tempo> listOfDays;
	private List<String> listOfOffenses;
	private List<Arco> listOfEdges;
	private SimpleWeightedGraph<String, DefaultWeightedEdge> grafo;
	
	public Model() {
		super();
		this.dao = new EventsDao();
		this.listOfCategories = dao.listAllCategories();
		this.listOfDays = dao.listAllDates();
	}
	
	public List<Arco> creaGrafo(String category, Tempo data) {
		grafo = new SimpleWeightedGraph<>(DefaultWeightedEdge.class);
		listOfOffenses=dao.listAllOffenseType(category, data);
		Graphs.addAllVertices(this.grafo, listOfOffenses);
		System.out.println("MODEL: Creato grafo # vertici: "+grafo.vertexSet().size());
		listOfEdges=dao.listArchi(category, data);
		Integer max=0;
		Integer min=listOfEdges.get(0).getPeso();
		for(Arco a: listOfEdges) {
			if(grafo.containsVertex(a.getId1())&&grafo.containsVertex(a.getId2())) {
				Graphs.addEdge(this.grafo, a.getId1(), a.getId2(), a.getPeso());
				if(a.getPeso()>max) {max=a.getPeso();}
				if(a.getPeso()<min) {min=a.getPeso();}
				a.setAdded(true);
			}
			
			
		}
		System.out.println("MODEL: Creato grafo # archi: "+grafo.edgeSet().size());
		List<Arco> stampa= new ArrayList<>();
		double mediana= (max+min)/2.0;
		for(Arco a: listOfEdges) {
			if(a.isAdded()&&a.getPeso()>mediana)stampa.add(a);
			//stampa.add(a);
		}
		
		return stampa;
		
		
	}
	public List<String> getListOfCategories() {
		return listOfCategories;
	}

	public List<Tempo> getListOfDays() {
		Collections.sort(this.listOfDays);
		return this.listOfDays;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
