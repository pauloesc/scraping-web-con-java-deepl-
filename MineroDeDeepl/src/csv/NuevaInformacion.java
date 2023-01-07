package csv;

import java.util.ArrayList;
import java.util.List;

public class NuevaInformacion {

	private String palabra;
	private String QueEs;
	private int CantidadMaximaDeInformacionPorPalabra = 4;
	private int cantidadColumnasCSV = 27;
	private List<Informacion> listsInformacion = new ArrayList<Informacion>();
	public Informacion actualmenteEnUso = null;
	private int cantSignificadosMenosFrecuentes = 5;
	private List<String> menosFrecuente = new ArrayList<String>();
	
	
	
	public Informacion obtenerInformacion() {
		if (CantidadMaximaDeInformacionPorPalabra > listsInformacion.size()) {
			Informacion i = new Informacion();
			listsInformacion.add(i);
			actualmenteEnUso = i;
			return i;
		} else {
			return null;
		}
	}
	
	public void corregirNuevaInformacion() {
		int listsInformacion_size = listsInformacion.size();
		for (int i = 0; i < (CantidadMaximaDeInformacionPorPalabra - listsInformacion_size); i++) {
			Informacion info = new Informacion();
			listsInformacion.add(info);
		}
		int menosFrecuente_size = menosFrecuente.size();
		for (int i = 0; i < (cantSignificadosMenosFrecuentes - menosFrecuente_size); i++) {
			menosFrecuente.add("");
		}
		
		for (Informacion informacion : listsInformacion) {
			informacion.arreglarInformacion();
		}
	}
	

	public void agregarMenosFrecuente( String mf ) {
		if (cantSignificadosMenosFrecuentes > menosFrecuente.size()) {
			menosFrecuente.add(mf);
		}
	}


	public void print() {
		System.out.println("palabra: "+palabra);
		System.out.println("que es: "+QueEs);
		for (Informacion informacion : listsInformacion) {
			informacion.print();
		}
		System.out.println("");
		for (String menosFrec : menosFrecuente) {
			System.out.println("menos frec: "+menosFrec);
		}
		System.out.println("+++++++++++++++++++++++++++");
		System.out.println("");
	} 
	
	
	public void setPalabra(String palabra) {
		this.palabra = palabra;
	}
	
	public void setQueEs(String queEs) {
		QueEs = queEs;
	}
	
	
	public String[] filaDeCSV() {
		String[] ret = new String[this.cantidadColumnasCSV];
		
		ret[0] = palabra;
		ret[1] = QueEs;
		int posicion = 2;
		
		for (Informacion inf : listsInformacion) {
			
			String[] sig_y_frases = inf.ArregloDeInfo();
			for (int i = 0; i < sig_y_frases.length; i++) {
				ret[posicion] = sig_y_frases[i];
				posicion += 1;
				
			}
			
		}
		
		for (String string : menosFrecuente) {
			ret[posicion] = string;
			posicion += 1;
		}
		
		return ret;
	}
	
}
