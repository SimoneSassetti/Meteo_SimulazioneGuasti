package it.polito.tdp.meteo.bean;

import java.util.*;

import it.polito.tdp.meteo.db.MeteoDAO;

public class Model {
	
	private List<Situazione> listaTot;
	private List<String> citta;
	MeteoDAO dao;
	
	public Model(){
		dao=new MeteoDAO();
	}
	
	public List<Situazione> getSituazioni(){
		if(listaTot==null){
			listaTot=dao.getAllSituazioni();
		}
		return listaTot;
	}
	
	public List<String> getCitta(){
		if(citta==null){
			citta=dao.getCitta();
		}
		return citta;
	}
	
	public List<Situazione> getStatistiche(String citta){
		List<Situazione> lista=new ArrayList<Situazione>();
		List<Situazione> temp=new ArrayList<Situazione>();
		
		for(Situazione s: this.getSituazioni()){
			if(s.getLocalita().compareTo(citta)==0){
				temp.add(s);
			}
		}
		
		int comp=temp.get(0).getTMax();
		for(Situazione s: temp){
			if(s.getLocalita().compareTo(citta)==0){
				if(s.getTMax()>=comp+2){
					lista.add(s);
					comp=s.getTMax();
				}else{
					comp=s.getTMax();
				}
			}
		}
		return lista;
	}

	public int simula(String citta, double p, int tecnici) {
		
		Simulatore sim=new Simulatore(this.getStatistiche(citta),p,tecnici);
		sim.inserisci();
		sim.run();
		return sim.getCosto();
	}
}
