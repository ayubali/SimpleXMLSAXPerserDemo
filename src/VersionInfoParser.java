/**
 * SAXParserHandler.java
 *
 * Date Jun 14, 2015 4:16:01 PM
 * @author Md Ayub Ali Sarker, ayub.ali.sarker@gmail.com
 * 
 **/
package org.stellar.luncher;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.log4j.Logger;
import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class VersionInfoParser extends DefaultHandler {
	private static Logger log = Logger.getLogger(VersionInfoParser.class);

	private String value = null;
	private StringBuilder valueBuilder = new StringBuilder();
	private VersionInfo versionInfo = null;

	public VersionInfo Parse(String versionUrl) {
		log.debug("Parsing version XML from :" + versionUrl);
		try {

			System.setProperty("org.xml.sax.driver",
					"org.xmlpull.v1.sax2.Driver");
			SAXParserFactory factory = SAXParserFactory.newInstance();
			SAXParser saxParser = factory.newSAXParser();
			InputSource source = new InputSource(new FileInputStream(new File(
					versionUrl)));
			source.setEncoding("ISO-8859-1");
			saxParser.parse(source, this);
		} catch (NullPointerException nex) {
			log.error("Parsing error: NullPointerException " + nex.getMessage());
		} catch (IOException e) {
			log.error("Parsing error: IOException " + e.getMessage());
		} catch (Exception e) {
			log.error("Parsing error: " + e.getMessage());
		}
		return versionInfo;
	}

	public void startElement(String uri, String localName, String qName,
			Attributes attributes) throws SAXException {
		valueBuilder.setLength(0);
		if (qName.equalsIgnoreCase("version")) {
			versionInfo = new VersionInfo();
		}
	}

	@Override
	public void endElement(String uri, String localName, String qName)
			throws SAXException {
		if (qName.equalsIgnoreCase("version")) {
			return;
		} else if (qName.equalsIgnoreCase("xmlupdate")) {
			versionInfo.setXmlUpdate(value);

		} else if (qName.equalsIgnoreCase("softwareupdate")) {
			versionInfo.setSoftwareUpdate(value);
		}

	}

	@Override
	public void characters(char[] ch, int start, int length)
			throws SAXException {
		value = StringEscapeUtils.escapeXml10(valueBuilder.append(ch, start,
				length).toString());
	}

}
