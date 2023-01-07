package webScraper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import csv.GeneradorDeCSV;
import csv.Informacion;
import csv.NuevaInformacion;


public class programa {

	
	//private String urlExtraerInformacion = "https://www.deepl.com/es/translator#en/es/encounter";
	private GeneradorDeCSV gcsv = new GeneradorDeCSV();
	

	public void extraerInformacion() {

		Document doc = null;

		final File folder = new File("/home/paulo/Escritorio/GeneradorTarjetasAnki/html/");
		List<String> lista = new ArrayList<String>();

		for (final File fileEntry : folder.listFiles()) {

			if (!fileEntry.isDirectory()) {

				try {
					doc = Jsoup.parse(fileEntry, "UTF-8", "");
				} catch (IOException e) {

				}
				extraerInfoPrimera(doc);

			}
		}
		
		gcsv.generarCSV();
		
	}

	private void extraerInfoPrimera(Document doc) {
		Elements secciones_de_informaccion = doc.select("div.lemma.featured");

		for (Element element : secciones_de_informaccion) {
			
			NuevaInformacion nueva_informacion = gcsv.generarNuevaInformacion();
			
			String aux = element.select("div div.line.lemma_desc.source span.tag_lemma a.dictLink").text();
			nueva_informacion.setPalabra(aux);
			
			aux = element.select("div div.line.lemma_desc.source span.tag_lemma span.tag_wordtype").text();
			nueva_informacion.setQueEs(aux);
			
			extraerInfoSegunda(element);
			extraerInformacionMenosFrecuente(element);
		}

	}

	private void extraerInfoSegunda(Element element) {
		Elements seccion_especifica = element.select("div.translation.sortablemg.featured");
		for (Element seccion_esp : seccion_especifica) {
			String significado = seccion_esp.select("a.dictLink.featured").text();

			Informacion inf = gcsv.actualmenteEnUso.obtenerInformacion();
			if( inf != null ) {
				inf.setSignificado(significado);
				extraerInformacionTercero(seccion_esp);
			}  
		}
	}

	private void extraerInformacionTercero(Element element) {
		Elements textos_de_ejemplo = element.select("div.translation.sortablemg.featured div.example_lines div.example.line ");
		for (Element texto_ejemplo : textos_de_ejemplo) {

			String aux1 = texto_ejemplo.select("span.tag_s").text();
			String aux2 =  texto_ejemplo.select("span.tag_t").text();
			gcsv.actualmenteEnUso.actualmenteEnUso.agregarFraseDeEjemplo(aux1, aux2);
			
			
		}
	}

	private void extraerInformacionMenosFrecuente(Element element) {
		Elements significados_menos_frecuentes = element
				.select("div.line.group_line.translation_group_line span.tag_trans a.dictLink");

		for (Element menos_frecuente : significados_menos_frecuentes) {

			// si no quiero mas de 5 elementos
			if (significados_menos_frecuentes.indexOf(menos_frecuente) >= 5) {
				significados_menos_frecuentes.last();
				significados_menos_frecuentes.next();

			} else {
				String aux = menos_frecuente.text();
				gcsv.actualmenteEnUso.agregarMenosFrecuente(aux);
			}
		}
	}

}
