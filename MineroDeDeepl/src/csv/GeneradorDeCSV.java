package csv;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.opencsv.CSVWriter;

public class GeneradorDeCSV {

	private List<NuevaInformacion> lista_NuevaInformacion = new ArrayList<NuevaInformacion>();
	public NuevaInformacion actualmenteEnUso = null;
	
	
	public NuevaInformacion generarNuevaInformacion() {
		NuevaInformacion ni = new NuevaInformacion();
		lista_NuevaInformacion.add(ni);
		actualmenteEnUso = ni;
		return ni;
	}

	
	public void print() {
		for (NuevaInformacion nuevaInformacion : lista_NuevaInformacion) {
			nuevaInformacion.print();
			System.out.println("");
		}
	}

	
	public void generarCSV() {

		//deadecuar el tamanio de los arrays con strigs vacios
		for (NuevaInformacion nuevaInformacion : lista_NuevaInformacion) {
			nuevaInformacion.corregirNuevaInformacion();
		}

		//aca poner los datos finales que contendra el csv
		List<String[]> filasDelCSV = new ArrayList<String[]>();
		
		String[] cabeceraDelCSV = new String[27];
		cabeceraDelCSV[0] = "palabra en cuestion";
		cabeceraDelCSV[1] = "que es (adj, ver, sus, conj)";
		cabeceraDelCSV[2] = "primer significado";
		cabeceraDelCSV[3] = "primer_significado_frase_en(1)";
		cabeceraDelCSV[4] = "primer_significado_frase_es(1)";
		cabeceraDelCSV[5] = "primer_significado_frase_en(2)";
		cabeceraDelCSV[6] = "primer_significado_frase_es(2)";
		cabeceraDelCSV[7] = "segundo_significado";
		cabeceraDelCSV[8] = "segundo_significado_frase_en(1)";
		cabeceraDelCSV[9] = "segundo_significado_frase_es(1)";
		cabeceraDelCSV[10] = "segundo_significado_frase_en(2)";
		cabeceraDelCSV[11] = "segundo_significado_frase_es(2)";
		cabeceraDelCSV[12] = "tercer_significado";
		cabeceraDelCSV[13] = "tercer_significado_frase_en(1)";
		cabeceraDelCSV[14] = "tercer_significado_frase_es(1)";
		cabeceraDelCSV[15] = "tercer_significado_frase_en(2)";
		cabeceraDelCSV[16] = "tercer_significado_frase_es(2)";
		cabeceraDelCSV[17] = "cuarto_significado";
		cabeceraDelCSV[18] = "cuarto_significado_frase_en(1)";
		cabeceraDelCSV[19] = "cuarto_significado_frase_es(1)";
		cabeceraDelCSV[20] = "cuarto_significado_frase_en(2)";
		cabeceraDelCSV[21] = "cuarto_significado_frase_es(2)";
		cabeceraDelCSV[22] = "menosFrecuente1";
		cabeceraDelCSV[23] = "menosFrecuente2";
		cabeceraDelCSV[24] = "menosFrecuente3";
		cabeceraDelCSV[25] = "menosFrecuente4";
		cabeceraDelCSV[26] = "menosFrecuente5";

		filasDelCSV.add(cabeceraDelCSV);
		
		for (NuevaInformacion nuevaInformacion : lista_NuevaInformacion) {
			String[] S = nuevaInformacion.filaDeCSV();
			filasDelCSV.add(S);
		}

		try (CSVWriter writer = new CSVWriter(new FileWriter("/home/paulo/Escritorio/GeneradorTarjetasAnki/CSV_GENERADO/G.csv"))) {
			writer.writeAll(filasDelCSV);
		} catch (IOException e) {
			System.out.println("error... archivo generdor de csv");
		}

	}

}
