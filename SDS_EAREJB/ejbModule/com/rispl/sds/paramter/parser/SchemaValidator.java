package com.rispl.sds.paramter.parser;

import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.helpers.DefaultHandler;

import org.w3c.dom.ls.LSResourceResolver;

public class SchemaValidator {

	protected static class Handler extends DefaultHandler {

		public void error(SAXParseException sAXParseException) throws SAXException {
			throw sAXParseException;
		}

		public void fatalError(SAXParseException sAXParseException) throws SAXException {

		}

		public void warning(org.xml.sax.SAXParseException sAXParseException) throws org.xml.sax.SAXException {
		}

	}

	protected static class Resolver implements LSResourceResolver {

		public org.w3c.dom.ls.LSInput resolveResource(String str, String str1, String str2, String str3, String str4) {
			return null;

		}

	}
}
