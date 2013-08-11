package com.floriparide.mobfloripaparser.parser;

import com.floriparide.mobfloripaparser.model.ParseResult;
import org.jsoup.nodes.Document;

/**
 * @author mikhail.bragin
 * @since 8/11/13
 */
public interface PageParser<T> {

    ParseResult<T> parse();
}
