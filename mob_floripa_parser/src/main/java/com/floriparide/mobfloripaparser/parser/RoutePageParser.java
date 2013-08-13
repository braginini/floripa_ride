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



		return null;
	}
}
