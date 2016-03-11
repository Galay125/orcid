package projeto;

import uk.bl.odin.orcid.client.OrcidAccessToken;

public class Tokens {
	
	OrcidAccessToken tokenR;
	OrcidAccessToken tokenU;
	OrcidAccessToken tokenB;
	
	public Tokens(){
		this.tokenR = new OrcidAccessToken();
		tokenR.setAccess_token("553ed24b-e8be-46a2-a96d-271c11738597");
		tokenR.setName("Mário Santos");
		tokenR.setScope("/orcid-profile/read-limited");
		tokenR.setOrcid("0000-0003-3351-0229");
		tokenR.setToken_type("bearer");
		
		this.tokenU = new OrcidAccessToken();
		tokenU.setAccess_token("54a80cb0-cc76-408b-8efe-762484d93be1");
		tokenU.setName("Mário Santos");
		tokenU.setScope("/activities/update");
		tokenU.setOrcid("0000-0003-3351-0229");
		tokenU.setToken_type("bearer");
		
		this.tokenB = new OrcidAccessToken();
		tokenB.setAccess_token("5be70e86-1cf7-4a75-bfda-0965d49b3713");
		tokenB.setName("Mário Santos");
		tokenB.setScope("/orcid-bio/update");
		tokenB.setOrcid("0000-0003-3351-0229");
		tokenB.setToken_type("bearer");
	}
	
	public OrcidAccessToken getTokenR(){
		return this.tokenR;
	}
	
	public OrcidAccessToken getTokenU(){
		return this.tokenU;
	}
	
	public OrcidAccessToken getTokenB(){
		return this.tokenB;
	}
}
