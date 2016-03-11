package pt.ptcris.utils;

import org.orcid.jaxb.model.message.OrcidWork;
import org.orcid.jaxb.model.record_rc2.Work;

public class UpdateRecord {

	private OrcidWork localOrcidWork;
	private OrcidWork remoteOrcidWork;

	public UpdateRecord(OrcidWork localOrcidWork, OrcidWork remoteOrcidWork) {
		this.localOrcidWork = localOrcidWork;
		this.remoteOrcidWork = remoteOrcidWork;
	}

	public OrcidWork getLocalOrcidWork() {
		return localOrcidWork;
	}

	public void setLocalOrcidWork(OrcidWork localOrcidWork) {
		this.localOrcidWork = localOrcidWork;
	}

	public OrcidWork getRemoteOrcidWork() {
		return remoteOrcidWork;
	}

	public void setRemoteOrcidWork(OrcidWork remoteOrcidWork) {
		this.remoteOrcidWork = remoteOrcidWork;
	}
}
