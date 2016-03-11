package pt.ptcris;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.xml.bind.JAXBException;

import org.orcid.jaxb.model.message.OrcidWork;
import org.orcid.jaxb.model.message.OrcidWorks;
import org.orcid.jaxb.model.record_rc2.Work;

import pt.ptcris.handlers.ProgressHandler;
import pt.ptcris.utils.UpdateRecord;
import uk.bl.odin.orcid.client.OrcidAccessToken;
import uk.bl.odin.orcid.client.OrcidOAuthClient;

public class PTCRISync {

	/**
	 * Export a list of OrcidWorks to an ORCID profile.
	 * 
	 * @param orcidID
	 *            The ORCID id of the profile to be updated.
	 * @param accessToken
	 *            The security token that grants update access to the profile.
	 * @param OrcidWorks
	 *            The list of OrcidWorks to be exported (those marked as synced).
	 * @param progressHandler
	 *            The implementation of the ProgressHandler interface responsible for receiving progress updates
	 * @throws JAXBException 
	 * @throws IOException 
	 */
	public static void export(OrcidOAuthClient client, OrcidAccessToken accessToken, List<OrcidWork> OrcidWorks, String clientSourceName, ProgressHandler progressHandler) throws IOException, JAXBException {
		int progress = 0;
		progressHandler.setProgress(progress);
		progressHandler.setCurrentStatus("ORCID_SYNC_EXPORT_STARTED");

		List<OrcidWork> orcidOrcidWorks = getLocalCRISSourcedORCIDOrcidWorks(client, accessToken, clientSourceName);

		List<UpdateRecord> recordsToUpdate = new LinkedList<UpdateRecord>();

		progressHandler.setCurrentStatus("ORCID_SYNC_EXPORT_OrcidWorkS_ITERATION");
		for (int counter = 0; counter != orcidOrcidWorks.size(); counter++) {
			progress = (int) ((double) ((double) counter / orcidOrcidWorks.size()) * 100);
			progressHandler.setProgress(progress);

			List<OrcidWork> matchingOrcidWorks = getOrcidWorksWithSharedUIDs(orcidOrcidWorks.get(counter), OrcidWorks);
			if (matchingOrcidWorks.isEmpty()) {
				deleteOrcidWork(client, accessToken, orcidOrcidWorks.get(counter));
			} else {
				for (OrcidWork OrcidWork : matchingOrcidWorks) {
					recordsToUpdate.add(new UpdateRecord(OrcidWork, orcidOrcidWorks.get(counter)));
					OrcidWorks.remove(OrcidWork);
				}
			}
		}

		progressHandler.setCurrentStatus("ORCID_SYNC_EXPORT_UPDATING_OrcidWorkS");
		for (int counter = 0; counter != recordsToUpdate.size(); counter++) {
			progress = (int) ((double) ((double) counter / recordsToUpdate.size()) * 100);
			progressHandler.setProgress(progress);

			updateOrcidWork(client, accessToken, recordsToUpdate.get(counter));
		}

		progressHandler.setCurrentStatus("ORCID_SYNC_EXPORT_ADDING_OrcidWorkS");
		for (int counter = 0; counter != OrcidWorks.size(); counter++) {
			progress = (int) ((double) ((double) counter / OrcidWorks.size()) * 100);
			progressHandler.setProgress(progress);

			addOrcidWork(client, accessToken, OrcidWorks.get(counter));
		}

		progressHandler.done();
	}

	/**
	 * Discover new OrcidWorks in an ORCID profile.
	 * 
	 * @param orcidID
	 *            The ORCID id of the profile to be searched.
	 * @param accessToken
	 *            The security token that grants update access to the profile.
	 * @param OrcidWorks
	 *            The full list of OrcidWorks in the local profile. In fact, for each OrcidWork only the external identifiers are needed, so the remaining
	 *            attributes may be left null.
	 * @param progressHandler
	 *            The implementation of the ProgressHandler interface responsible for receiving progress updates
	 * @return The list of new OrcidWorks found in the ORCID profile.
	 * @throws JAXBException 
	 * @throws IOException 
	 */
	public static List<OrcidWork> importOrcidWorks(OrcidOAuthClient client, OrcidAccessToken accessToken, List<OrcidWork> OrcidWorks, ProgressHandler progressHandler) throws IOException, JAXBException {
		int progress = 0;
		progressHandler.setProgress(progress);
		progressHandler.setCurrentStatus("ORCID_SYNC_IMPORT_OrcidWorkS_STARTED");

		List<OrcidWork> OrcidWorksToImport = new LinkedList<OrcidWork>();

		List<OrcidWork> orcidOrcidWorks = getAllORCIDOrcidWorks(client, accessToken);

		progressHandler.setCurrentStatus("ORCID_SYNC_IMPORT_OrcidWorkS_ITERATION");
		for (int counter = 0; counter != orcidOrcidWorks.size(); counter++) {
			progress = (int) ((double) ((double) counter / orcidOrcidWorks.size()) * 100);
			progressHandler.setProgress(progress);

			List<OrcidWork> matchingOrcidWorks = getOrcidWorksWithSharedUIDs(orcidOrcidWorks.get(counter), OrcidWorks);
			if (matchingOrcidWorks.isEmpty()) {
				// Aqui é melhor ir ao ORCID sacar o registo completo do OrcidWork
				// Temos que adicionar um métod para isso
				// Se deixarmos assim só vai adicionar informação básica sobre o OrcidWork
				OrcidWorksToImport.add(orcidOrcidWorks.get(counter));
			}
		}

		progressHandler.done();

		return OrcidWorksToImport;
	}

	/**
	 * Discover updates to existing OrcidWorks in an ORCID profile.
	 * 
	 * @param orcidID
	 *            The ORCID id of the profile to be searched.
	 * @param accessToken
	 *            The security token that grants update access to the profile.
	 * @param OrcidWorks
	 *            The list of OrcidWorks for which we wish to discover updates (those marked as synced). For the moment, only new external identifiers will
	 *            be found, so, for each OrcidWork only the external identifiers are needed, so the remaining attributes may be left null. Also the putcode
	 *            attribute should be used to store the local key of each OrcidWork.
	 * @param progressHandler
	 *            The implementation of the ProgressHandler interface responsible for receiving progress updates
	 * @return The list of updated OrcidWorks. Only the OrcidWorks that have changes are returned. Also, for each of them, only the attributes that changed are
	 *         set. For the moment, only new external identifiers will be returned.
	 * @throws JAXBException 
	 * @throws IOException 
	 */
	public static List<OrcidWork> importUpdates(OrcidOAuthClient client, OrcidAccessToken accessToken, List<OrcidWork> OrcidWorks, ProgressHandler progressHandler) throws IOException, JAXBException {
		int progress = 0;
		progressHandler.setProgress(progress);
		progressHandler.setCurrentStatus("ORCID_SYNC_IMPORT_UPDATES_STARTED");

		List<OrcidWork> OrcidWorksToUpdate = new LinkedList<OrcidWork>();

		List<OrcidWork> orcidOrcidWorks = getAllORCIDOrcidWorks(client, accessToken);

		progressHandler.setCurrentStatus("ORCID_SYNC_IMPORT_UPDATES_ITERATION");
		for (int counter = 0; counter != orcidOrcidWorks.size(); counter++) {
			progress = (int) ((double) ((double) counter / orcidOrcidWorks.size()) * 100);
			progressHandler.setProgress(progress);

			List<OrcidWork> matchingOrcidWorks = getOrcidWorksWithSharedUIDs(orcidOrcidWorks.get(counter), OrcidWorks);
			if (!matchingOrcidWorks.isEmpty()) {
				for (OrcidWork OrcidWork : matchingOrcidWorks) {
					if (!isAlreadyUpToDate(OrcidWork, orcidOrcidWorks.get(counter))) {
						OrcidWorksToUpdate.add(orcidOrcidWorks.get(counter));
					}
				}
			}
		}

		progressHandler.done();

		return OrcidWorksToUpdate;
	}

	/**
	 * Retrieves the entire set of OrcidWorks in the ORCID profile whose source is the local CRIS service
	 * 
	 * @param orcidID
	 *            The ORCID id of the profile to be searched
	 * @param orcidAccessToken
	 *            The security token that grants update access to the profile
	 * @param serviceSourceName
	 *            The source name of the local CRIS service
	 * @return The set of OrcidWorks in the ORCID profile whose source is the local CRIS service
	 * @throws JAXBException 
	 * @throws IOException 
	 */
	public static List<OrcidWork> getLocalCRISSourcedORCIDOrcidWorks(OrcidOAuthClient client, OrcidAccessToken token, String serviceSourceName) throws IOException, JAXBException {
		// TODO Contact the ORCID API and retrieve the OrcidWorks which source name is the one given by serviceSourceName
		List<OrcidWork> ow = new LinkedList<OrcidWork>();
		OrcidWorks wks = client.getProfile(token).getOrcidActivities().getOrcidWorks();
		for (OrcidWork w : wks.getOrcidWork()) {
			if(w.getSource().getSourceName().getContent().equals(serviceSourceName))
				ow.add(w);
		}
		return ow;
	}

	/**
	 * Retrieves the set of productions (from OrcidWorks) that share some UIDs with OrcidWork
	 * 
	 * @param OrcidWork
	 *            The OrcidWork to compare with the list of OrcidWorks
	 * @param OrcidWorks
	 *            The set of OrcidWorks to search for productions with shared UIDs
	 * @return The set of OrcidWorks with matching UIDs
	 */
	private static List<OrcidWork> getOrcidWorksWithSharedUIDs(OrcidWork OrcidWork, List<OrcidWork> OrcidWorks) {
		// TODO Iterate through OrcidWorks and compare UIDs. If any UIDs match, return it
		// If the OrcidWork has no UIDs it should return an empry list
		return new LinkedList<OrcidWork>();
	}

	/**
	 * Delete a OrcidWork from the ORCID profile
	 * 
	 * @param client
	 *            The ORCID id of the profile to be searched
	 * @param accessToken
	 *            The security token that grants update access to the profile
	 * @param OrcidWork
	 *            The OrcidWork to be deleted
	 */
	private static void deleteOrcidWork(OrcidOAuthClient client, OrcidAccessToken accessToken, OrcidWork OrcidWork) {
		// TODO Contact the ORCID API and delete the OrcidWork from the ORCID profile
		// NOTE: according to the ORCID API, to delete a OrcidWork, one must provide the entire list of OrcidWorks in the ORCID profile minus the OrcidWork(s) that
		// should be deleted. This means that this operation must be done in three steps: first, retrieve the entire set of OrcidWorks; second, remove the
		// OrcidWork to be deleted from the list of OrcidWorks; and three, send the updated list to the ORCID API.
	}

	/**
	 * Update a OrcidWork in the ORCID profile
	 * 
	 * @param client
	 *            The ORCID id of the profile to be searched
	 * @param accessToken
	 *            The security token that grants update access to the profile
	 * @param updateRecord
	 *            The updateRecord that contains both the local and remote OrcidWorks (the remote OrcidWork is updated based on the data in the local OrcidWork)
	 */
	private static void updateOrcidWork(OrcidOAuthClient client, OrcidAccessToken accessToken, UpdateRecord updateRecord) {
		// TODO Contact the ORCID API and update the OrcidWork on the ORCID profile
		// NOTE: according to the ORCID API, to update a OrcidWork, one must provide the entire list of OrcidWorks in the ORCID profile including the OrcidWork(s)
		// that should be updated. This means that this operation must be done in three steps: first, retrieve the entire set of OrcidWorks; second,
		// replace the OrcidWork to be updated with the new record in the list of OrcidWorks; and three, send the updated list to the ORCID API.
	}

	/**
	 * Add a OrcidWork to the ORCID profile
	 * 
	 * @param orcidID
	 *            The ORCID id of the profile to be searched
	 * @param accessToken
	 *            The security token that grants update access to the profile
	 * @param OrcidWork
	 *            The OrcidWork to be added to the ORCID profile
	 */
	private static void addOrcidWork(OrcidOAuthClient client, OrcidAccessToken accessToken, OrcidWork OrcidWork) {
		// TODO Contact the ORCID API and add the OrcidWork in the ORCID profile
	}

	/**
	 * Retrieves the entire set of OrcidWorks in the ORCID profile
	 * 
	 * @param orcidID
	 *            The ORCID id of the profile to be searched
	 * @param accessToken
	 *            The security token that grants update access to the profile
	 * @return The set of OrcidWorks in the ORCID profile
	 * @throws JAXBException 
	 * @throws IOException 
	 */
	private static List<OrcidWork> getAllORCIDOrcidWorks(OrcidOAuthClient client, OrcidAccessToken token) throws IOException, JAXBException {
		// TODO Contact the ORCID API and retrieve all OrcidWorks in the ORCID profile
		OrcidWorks wks = client.getProfile(token).getOrcidActivities().getOrcidWorks();
		return wks.getOrcidWork();
	}

	/**
	 * Checks if localOrcidWork is already up to date on the information from remoteOrcidWork, i.e., localOrcidWork already has the same UIDs as remoteOrcidWork
	 * 
	 * @param localOrcidWork
	 *            The local OrcidWork to check if it is up to date
	 * @param remoteOrcidWork
	 *            The remote OrcidWork to use when checking if the local OrcidWork is up to date
	 * @return true if all the UIDs between the two OrcidWorks are the same, false otherwise
	 */
	private static boolean isAlreadyUpToDate(OrcidWork localOrcidWork, OrcidWork remoteOrcidWork) {
		// TODO Compare the two records to check if they are equal (when it comes to matching UIDs)
		return false;
	}
}
