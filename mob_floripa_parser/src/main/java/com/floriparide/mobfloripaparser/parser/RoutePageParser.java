package com.floriparide.mobfloripaparser.parser;

import com.floriparide.mobfloripaparser.model.Calendar;
import com.floriparide.mobfloripaparser.model.ParseResult;
import com.floriparide.mobfloripaparser.model.Route;
import com.floriparide.mobfloripaparser.model.Trip;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Mikhail Bragin
 */
public class RoutePageParser extends PageParser<List<Trip>> {

	Route route;

	public RoutePageParser(Document document, Route route) {
		super(document);
		this.route = route;
	}

	@Override
	public ParseResult<List<Trip>> parse() {

		List<Trip> trips = new ArrayList<>();

		Integer time = null;

		Float priceCard = null;

		Float price = null;

		Float length = null;

		Element contentEl = document.getElementsByClass("conteudo").get(0);

		Elements tableEls = contentEl.getElementsByTag("table");

		//if we have no at least 4 tables - we have no time table
		if (tableEls.size() < 4)
			return new TripListParseResult(Collections.<Trip>emptyList());  //todo log warn here

		//remove unnecessary tables
		for (int i = 0; i < 2; i ++)
			tableEls.remove(0);

		//get the first table with the time description under index 2
		trips.addAll(getTripsFromContentBlock(tableEls, 0));

		//go to next content block
		if (tableEls.size() > 3) {
			trips.addAll(getTripsFromContentBlock(tableEls, 2));
		}

		if (tableEls.size() > 5) {
			trips.addAll(getTripsFromContentBlock(tableEls, 4));
		}

		if (tableEls.size() > 7) {
			trips.addAll(getTripsFromContentBlock(tableEls, 6));
		}

		if (tableEls.size() > 9) {
			trips.addAll(getTripsFromContentBlock(tableEls, 8));
		}

		if (tableEls.size() > 11) {
			trips.addAll(getTripsFromContentBlock(tableEls, 10));
		}

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


		return new TripListParseResult(trips);
	}

	private List<Trip> getTripsFromContentBlock(Elements tableEls, int index) {

		List<Trip>  trips = new ArrayList<>();

		Element contentTable = tableEls.get(index);

		Calendar.CalendarType calendarType = chooseCalendarType(contentTable);
		if (calendarType == null)
			return Collections.emptyList(); //todo log warn here

		Trip.TripDirection direction = chooseDirection(contentTable);
		if (direction == null)
			return Collections.emptyList(); //todo log warn here

		//get the times
		contentTable = tableEls.get(++index);

		Elements timeEls = contentTable.getElementsByAttributeValue("bgcolor", "#ececec");

		timeEls .addAll(contentTable.getElementsByAttributeValue("bgcolor", "#13a148"));

		for (Element element : timeEls) {
			String startTime = (element.getElementsByTag("p").get(0).text());
			trips.add(new Trip(
					route.getId(),
					(long) calendarType.getId(),
					direction,
					startTime));
		}


		return trips;
	}

	private Trip.TripDirection chooseDirection(Element contentTable) {

		if (!contentTable.getElementsMatchingOwnText("Ida").isEmpty())
			return Trip.TripDirection.INBOUND;

		if (!contentTable.getElementsMatchingOwnText("Volta").isEmpty())
			return Trip.TripDirection.OUTBOUND;

		return null;
	}

	private Calendar.CalendarType chooseCalendarType(Element contentTable) {

		if (!contentTable.getElementsMatchingOwnText(Calendar.CalendarType.WEEK.getPtName()).isEmpty())
			return Calendar.CalendarType.WEEK;

		if (!contentTable.getElementsMatchingOwnText(Calendar.CalendarType.SATURDAY.getPtName()).isEmpty())
			return Calendar.CalendarType.SATURDAY;

		if (!contentTable.getElementsMatchingOwnText(Calendar.CalendarType.SUNDAY.getPtName()).isEmpty())
			return Calendar.CalendarType.SUNDAY;

		return null;
	}

	private class TripListParseResult implements ParseResult<List<Trip>> {

		List<Trip> trips;

		private TripListParseResult(List<Trip> trips) {
			this.trips = trips;
		}

		@Override
		public List<Trip> result() {
			return trips;
		}

		@Override
		public void setResult(List<Trip> result) {
			this.trips = result;
		}
	}
}
