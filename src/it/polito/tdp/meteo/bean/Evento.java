package it.polito.tdp.meteo.bean;

import java.time.LocalDate;

public class Evento implements Comparable<Evento>{
	public enum Type{Guasto,TecnicoLibero};
	
	private Type tipo;
	private LocalDate data;
	private int giorni;
	
	public Evento(Type tipo, LocalDate data, int giorni) {
		super();
		this.tipo = tipo;
		this.data = data;
		this.giorni = giorni;
	}
	public Type getTipo() {
		return tipo;
	}
	public void setTipo(Type tipo) {
		this.tipo = tipo;
	}

	public LocalDate getData() {
		return data;
	}

	public void setData(LocalDate data) {
		this.data = data;
	}

	public int getGiorni() {
		return giorni;
	}

	public void setGiorni(int giorni) {
		this.giorni = giorni;
	}

	@Override
	public int compareTo(Evento e) {
		return this.data.compareTo(e.data);
	}
}
