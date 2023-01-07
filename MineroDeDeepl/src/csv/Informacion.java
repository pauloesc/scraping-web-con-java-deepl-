package csv;

import java.util.ArrayList;
import java.util.List;

public class Informacion {

	private String significado = "";
	private int cantidadMaximaFrasesDeEjemplo = 4; //
	private List<String> frases = new ArrayList<String>();

	
	public String getSignificado() {
		return significado;
	}



	public List<String> getFrases() {
		return frases;
	}



	public void arreglarInformacion() {
		int actualTamanio = frases.size();
		for (int i = 0; i < (cantidadMaximaFrasesDeEjemplo - actualTamanio); i++) {
			frases.add(" ");
		}
	}
	
	public void agregarFraseDeEjemplo(String f1, String f2) {
		if (cantidadMaximaFrasesDeEjemplo > frases.size()) {
			frases.add(f1);
			frases.add(f2);			
		}
		
	}

	public void setSignificado(String significado) {
		this.significado = significado;
	}


	public void print() {
		System.out.println("");
		System.out.println("significado: "+significado);
		for (String stringg : frases) {
			System.out.println("frases: "+stringg);
		}
	}


	public String[] ArregloDeInfo() {
		
		String[] ret = new String[1 + cantidadMaximaFrasesDeEjemplo];
		ret[0] = significado;
		int posicion = 1;
		
		for (String f : frases) {
			ret[posicion] = f;
			posicion+=1;
		}
		
		return ret;
	}
	
	
	
}
