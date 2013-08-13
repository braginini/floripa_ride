package com.floriparide.mobfloripaparser.parser;

import com.floriparide.mobfloripaparser.model.ParseResult;
import org.jsoup.nodes.Document;

/**
 * @author mikhail.bragin
 * @since 8/11/13
 */
public abstract class PageParser<T> {

	Document document;

	protected PageParser(Document document) {
		this.document = document;
	}

	abstract ParseResult<T> parse();
}
