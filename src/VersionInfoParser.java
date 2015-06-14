/**
 * SAXParserHandler.java
 *
 * Date Jun 14, 2015 4:16:01 PM
 * @author Md Ayub Ali Sarker, ayub.ali.sarker@gmail.com
 * 
 **/
package org.stellar.luncher;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

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
	private static VersionInfoParser versionParser = new VersionInfoParser();

	/**
	 * @return the versionParser
	 */
	public static VersionInfoParser getVersionParser() {
		return versionParser;
	}

	public VersionInfo Parse(String versionUrl) {
		log.debug("Parsing version XML from :" + versionUrl);
		try {

			System.setProperty("org.xml.sax.driver",
					"org.xmlpull.v1.sax2.Driver");
			SAXParserFactory factory = SAXParserFactory.newInstance();
			SAXParser saxParser = factory.newSAXParser();

			URL xmlUrl = new URL(versionUrl);
			HttpURLConnection urlConnection = (HttpURLConnection) xmlUrl
					.openConnection();
			urlConnection.connect();
			InputStream inputStream = urlConnection.getInputStream();

			InputSource source = new InputSource(inputStream);
			source.setEncoding("ISO-8859-1");
			saxParser.parse(source, this);
		} catch (NullPointerException ex) {
			log.error("Parsing error: NullPointerException " + ex.getMessage());
		} catch (IOException ex) {
			log.error("Parsing error: IOException " + ex.getMessage());
		} catch (Exception ex) {
			log.error("Parsing error: " + ex.getMessage());
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
