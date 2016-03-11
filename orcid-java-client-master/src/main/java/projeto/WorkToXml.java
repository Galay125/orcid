package projeto;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

import org.orcid.jaxb.model.message.OrcidWork;

public class WorkToXml {

	public static void toLocal(OrcidWork w, String code) throws FileNotFoundException, UnsupportedEncodingException{
		PrintWriter writer = new PrintWriter("exports/"+code+".xml", "UTF-8");
		writer.println(w);
		writer.close();
		
	}


}
