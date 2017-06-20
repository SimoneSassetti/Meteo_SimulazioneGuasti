package it.polito.tdp.meteo.bean;

import java.util.List;
import java.util.PriorityQueue;
import java.util.Random;

import it.polito.tdp.meteo.bean.Evento.Type;

public class Simulatore {
	
	private List<Situazione> lista;
	private double prob;
	private int tecnici;
	
	private int costoTecnici=20000;
	private int costoProf=2000;
	private int costoComplessivo;
	
	private PriorityQueue<Evento> coda;
	
	public Simulatore(List<Situazione> statistiche, double p, int tecnici) {
		lista=statistiche;
		prob=p;
		this.tecnici=tecnici;
		
		coda=new PriorityQueue<Evento>();
		costoComplessivo=costoTecnici*tecnici;
	}
	
	public void inserisci(){
		for(Situazione s: lista){
			Random r=new Random();
			double p=r.nextDouble();
			if(p<=prob){
				int giorni=r.nextInt(9)+2;
				Evento e=new Evento(Type.Guasto,s.getData(),giorni);
				coda.add(e);
			}
		}
	}
	
	public void run(){
		int tecniciDisponibili=tecnici;
		
		while(!coda.isEmpty()){
			
			Evento e =coda.poll();
			
			switch(e.getTipo()){
			
			case Guasto:
			
				if(tecniciDisponibili==0){
					costoComplessivo+=costoProf;
				}else{
					tecniciDisponibili--;
					Evento tecnico=new Evento(Type.TecnicoLibero,e.getData().plusDays(e.getGiorni()),0);
					coda.add(tecnico);
				}
				break;
			
			case TecnicoLibero:
				tecniciDisponibili++;
				break;
			}
		}
	}
	
	public int getCosto(){
		return costoComplessivo;
	}
}
