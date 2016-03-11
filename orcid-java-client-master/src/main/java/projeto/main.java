package projeto;

import java.awt.Desktop;
import java.io.IOException;
import java.math.BigInteger;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.ConsoleHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import javax.xml.bind.JAXBException;
import javax.xml.parsers.ParserConfigurationException;

import uk.bl.odin.orcid.client.OrcidAccessToken;
import uk.bl.odin.orcid.client.OrcidOAuthClient;
import uk.bl.odin.orcid.client.constants.OrcidApiType;
import uk.bl.odin.orcid.client.constants.OrcidAuthScope;
import org.orcid.jaxb.model.message.Biography;
import org.orcid.jaxb.model.message.Country;
import org.orcid.jaxb.model.message.OrcidActivities;
import org.orcid.jaxb.model.message.OrcidBio;
import org.orcid.jaxb.model.message.OrcidMessage;
import org.orcid.jaxb.model.message.OrcidProfile;
import org.orcid.jaxb.model.message.OrcidWork;
import org.orcid.jaxb.model.message.OrcidWorks;
import org.orcid.jaxb.model.message.Visibility;
import org.orcid.jaxb.model.message.WorkExternalIdentifier;
import org.orcid.jaxb.model.message.WorkExternalIdentifiers;
import org.orcid.jaxb.model.message.WorkTitle;
import org.orcid.jaxb.model.message.WorkContributors;
import org.restlet.resource.ClientResource;
import org.xml.sax.SAXException;

import pt.ptcris.PTCRISync;
import pt.ptcris.handlers.ProgressHandler;

public class main implements ProgressHandler {
		private static Logger logger = Logger.getLogger(main.class.getName());

		private static final String orcidID = "APP-X7DMY3AKDXK34RVS";
		private static final String orcidSecret = "d622a047-deef-4368-a1e8-223101911563";
		private static final String orcidURL = "http://localhost";
		private static final Tokens tk = new Tokens();
		private static final String serviceSourceName = "Mário Santos";

	public static void main(String args[]) throws JAXBException, URISyntaxException, IOException, ParserConfigurationException, SAXException{
		OrcidOAuthClient client = new OrcidOAuthClient(orcidID, orcidSecret, orcidURL, OrcidApiType.SANDBOX);
		
		ConsoleHandler handler = new ConsoleHandler();
		handler.setFormatter(new SimpleFormatter());
		handler.setLevel(Level.ALL);
		logger.setLevel(Level.ALL);
		logger.addHandler(handler);

		List<OrcidWork> works = new LinkedList<OrcidWork>();
		works = PTCRISync.getLocalCRISSourcedORCIDOrcidWorks(client, tk.getTokenR(), serviceSourceName);
		main progressHandler = new main();

		XmlToWork.getWorks("exports/");
		//PTCRISync.export(orcidID, accessToken, works, serviceSourceName, progressHandler);
		//List<OrcidWork> worksToImport = PTCRISync.importOrcidWorks(client, tk.getTokenR(), works, progressHandler);
		//List<Work> worksToUpdate = PTCRISync.importUpdates(orcidID, accessToken, works, progressHandler);
		
		//WorkToXml.toLocal(works.get(0), works.get(0).getPutCode());
	}
	
	@Override
	public void setProgress(int progress) {
		logger.fine("Current progress: " + progress + "%");
	}

	@Override
	public void setCurrentStatus(String message) {
		logger.fine("Task: " + message);
	}

	@Override
	public void sendError(String message) {
		logger.log(Level.SEVERE, "ERROR: " + message);
	}

	@Override
	public void done() {
		logger.fine("Done.");
	}

	
}