package projeto;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.LinkedList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.orcid.jaxb.model.message.OrcidWork;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

public class XmlToWork {

	public static void getWorks(String path) throws ParserConfigurationException, SAXException, IOException{
		
		List<OrcidWork> works = new LinkedList<OrcidWork>();
		
		File folder = new File(path);
		File[] listOfFiles = folder.listFiles();
		
		for(int i=0; i < listOfFiles.length; i++){
			if(listOfFiles[i].isFile()){
				works.add(toWork(path+listOfFiles[i].getName()));
			}
		}
		
	}
	
	private static OrcidWork toWork(String file){
		File xml = new File(file);
		/*DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		Document doc = dBuilder.parse(xml);
	
		Element root = doc.getDocumentElement();
		OrcidWork w = new OrcidWork();
		System.out.println(file);*/
		
		return new OrcidWork();
	}
}
