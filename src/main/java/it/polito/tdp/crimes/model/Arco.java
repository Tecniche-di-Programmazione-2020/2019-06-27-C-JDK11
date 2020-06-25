package it.polito.tdp.crimes.model;

public class Arco {

	String id1,id2;
	Integer peso;
	boolean added;
	public Arco(String id1, String id2, Integer peso) {
		
		this.id1 = id1;
		this.id2 = id2;
		this.peso = peso;
		this.added=false;
	}
	public String getId1() {
		return id1;
	}
	public String getId2() {
		return id2;
	}
	public Integer getPeso() {
		return peso;
	}
	
	public boolean isAdded() {
		return added;
	}
	public void setAdded(boolean added) {
		this.added = added;
	}
	@Override
	public String toString() {
		return "Crimine 1=" + id1 + ", Crimine 2=" + id2 + ", Distretti=" + peso ;
	}
	
}
