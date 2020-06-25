package it.polito.tdp.crimes.model;

import java.time.LocalDateTime;

public class Tempo implements Comparable<Tempo>{

	private LocalDateTime timestamp;
	private Integer anno,giorno,mese,ora,minuto;
	public Tempo(LocalDateTime timestamp) {
		super();
		this.timestamp = timestamp;
		this.anno=timestamp.getYear();
		this.mese=timestamp.getMonthValue();
		this.giorno=timestamp.getDayOfMonth();
		this.ora=timestamp.getHour();
		this.minuto=timestamp.getMinute();
	}
	
	public String toString() {
		return this.giorno+"/"+this.mese+"/"+this.anno;
	}

	public LocalDateTime getTimestamp() {
		return timestamp;
	}

	@Override
	public int compareTo(Tempo o) {
		// TODO Auto-generated method stub
		return this.timestamp.compareTo(o.getTimestamp());
	}
	
	public String searchDB() {
		return anno+"-"+mese+"-"+giorno;
	}
}
