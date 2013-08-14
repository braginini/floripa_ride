package com.floriparide.mobfloripaparser.parser;

import com.floriparide.mobfloripaparser.model.ParseResult;
import com.floriparide.mobfloripaparser.model.Trip;

import org.jsoup.nodes.Document;

import java.util.List;

/**
 * Created by Mikhail Bragin
 */
public class RoutePageParser extends PageParser<List<Trip>> {

	public RoutePageParser(Document document) {
		super(document);
	}

	@Override
	public ParseResult<List<Trip>> parse() {

		Integer time = null;

		Float priceCard = null;

		Float price = null;

		Integer length = null;

		//works on week
		//document.getElementsByClass("conteudo").get(0).getElementsByTag("table").get(2).getElementsContainingOwnText("Dia da semana")
		/**
		 * todo get elemets under first "conteudo"
		 * todo get all element with tag "table"
		 * todo check element on index 2 for these text entries : Dia da semana or Sábado or Domingo
		 * todo if matches at least one then depends on match create an instance of Calendar
		 * todo then check for match Ida or Volta and choose 0 or 1 (direction)
		 * todo then next table should contains the times - extract times
		 * todo then next table should be the Volta in case if before was Ida
		 * todo check for Dia da semana or Sábado or Domingo and Volta/Ida
		 * todo next table should contain times
		 */
		//works on saturday
		//works on sunday




		return null;
	}
}
