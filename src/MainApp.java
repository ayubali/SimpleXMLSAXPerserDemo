import java.io.File;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

/**
 * MainApp.java
 *
 * Date Jun 14, 2015 3:36:48 PM
 * 
 * @author Md Ayub Ali Sarker, ayub.ali.sarker@gmail.com
 * 
 **/

public class MainApp {
	public static void main(String[] args) {
		try {
			File file = new File("resource/student.xml");
			SAXParserFactory saxFactory = SAXParserFactory.newInstance();
			SAXParser saxparser = saxFactory.newSAXParser();
			SAXParserHandler  handler = new SAXParserHandler();
			saxparser.parse(file, handler);		
			for (Student student : handler.getStList()) {
				System.out.println("Student: "+ student);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		

	}

}
