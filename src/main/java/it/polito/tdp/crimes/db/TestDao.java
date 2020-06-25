package it.polito.tdp.crimes.db;

import java.time.LocalDateTime;

import it.polito.tdp.crimes.model.Event;
import it.polito.tdp.crimes.model.Tempo;

public class TestDao {

	public static void main(String[] args) {
		EventsDao dao = new EventsDao();
		for(Tempo t: dao.listAllDates())System.out.println(t);
		
		//for(Event e : dao.listAllEvents())			System.out.println(e);
	}

}
