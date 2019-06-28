package null;

import java.util.List;

public class Response{
	private List<GeonamesItem> geonames;

	public void setGeonames(List<GeonamesItem> geonames){
		this.geonames = geonames;
	}

	public List<GeonamesItem> getGeonames(){
		return geonames;
	}

	@Override
 	public String toString(){
		return 
			"Response{" + 
			"geonames = '" + geonames + '\'' + 
			"}";
		}
}