import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringEscapeUtils;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 * UserHandler.java
 *
 * Date Jun 14, 2015 3:14:43 PM
 * 
 * @author Md Ayub Ali Sarker, ayub.ali.sarker@gmail.com
 * 
 **/

public class SAXParserHandler extends DefaultHandler {

	private String value = null;
	private StringBuilder valueBuilder = new StringBuilder();
	private Student student = null;
	private List<Student> stList = new ArrayList<Student>();

	@Override
	public void startElement(String uri, String localName, String qName,
			Attributes attributes) throws SAXException {
		valueBuilder.setLength(0);
		if (qName.equalsIgnoreCase("student")) {
			student = new Student();
			student.setRollNumber(attributes.getValue("rollno"));
		}
	}

	@Override
	public void endElement(String uri, String localName, String qName)
			throws SAXException {
		if (qName.equalsIgnoreCase("student")) {
			stList.add(student);
		}
		else if (qName.equalsIgnoreCase("firstname")) {
			student.setFirstName(value);
		}
		else if (qName.equalsIgnoreCase("lastname")) {
			student.setLastName(value);
		}
		else if (qName.equalsIgnoreCase("nickname")) {
			student.setNickName(value);
		}
		else if (qName.equalsIgnoreCase("marks")) {
			student.setNickName(value);
		}


	}

	@Override
	public void characters(char[] ch, int start, int length)
			throws SAXException {
		value = StringEscapeUtils.escapeXml10(valueBuilder.append(ch, start,
				length).toString());
	}
	
	/**
	 * @return the stList
	 */
	public List<Student> getStList() {
		return stList;
	}
}
