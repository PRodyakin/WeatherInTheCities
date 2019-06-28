package null;

public class GeonamesItem{
	private String adminCode1;
	private String lng;
	private String distance;
	private int geonameId;
	private String toponymName;
	private String countryId;
	private String fcl;
	private int population;
	private String countryCode;
	private String name;
	private String fclName;
	private AdminCodes1 adminCodes1;
	private String countryName;
	private String fcodeName;
	private String adminName1;
	private String lat;
	private String fcode;

	public void setAdminCode1(String adminCode1){
		this.adminCode1 = adminCode1;
	}

	public String getAdminCode1(){
		return adminCode1;
	}

	public void setLng(String lng){
		this.lng = lng;
	}

	public String getLng(){
		return lng;
	}

	public void setDistance(String distance){
		this.distance = distance;
	}

	public String getDistance(){
		return distance;
	}

	public void setGeonameId(int geonameId){
		this.geonameId = geonameId;
	}

	public int getGeonameId(){
		return geonameId;
	}

	public void setToponymName(String toponymName){
		this.toponymName = toponymName;
	}

	public String getToponymName(){
		return toponymName;
	}

	public void setCountryId(String countryId){
		this.countryId = countryId;
	}

	public String getCountryId(){
		return countryId;
	}

	public void setFcl(String fcl){
		this.fcl = fcl;
	}

	public String getFcl(){
		return fcl;
	}

	public void setPopulation(int population){
		this.population = population;
	}

	public int getPopulation(){
		return population;
	}

	public void setCountryCode(String countryCode){
		this.countryCode = countryCode;
	}

	public String getCountryCode(){
		return countryCode;
	}

	public void setName(String name){
		this.name = name;
	}

	public String getName(){
		return name;
	}

	public void setFclName(String fclName){
		this.fclName = fclName;
	}

	public String getFclName(){
		return fclName;
	}

	public void setAdminCodes1(AdminCodes1 adminCodes1){
		this.adminCodes1 = adminCodes1;
	}

	public AdminCodes1 getAdminCodes1(){
		return adminCodes1;
	}

	public void setCountryName(String countryName){
		this.countryName = countryName;
	}

	public String getCountryName(){
		return countryName;
	}

	public void setFcodeName(String fcodeName){
		this.fcodeName = fcodeName;
	}

	public String getFcodeName(){
		return fcodeName;
	}

	public void setAdminName1(String adminName1){
		this.adminName1 = adminName1;
	}

	public String getAdminName1(){
		return adminName1;
	}

	public void setLat(String lat){
		this.lat = lat;
	}

	public String getLat(){
		return lat;
	}

	public void setFcode(String fcode){
		this.fcode = fcode;
	}

	public String getFcode(){
		return fcode;
	}

	@Override
 	public String toString(){
		return 
			"GeonamesItem{" + 
			"adminCode1 = '" + adminCode1 + '\'' + 
			",lng = '" + lng + '\'' + 
			",distance = '" + distance + '\'' + 
			",geonameId = '" + geonameId + '\'' + 
			",toponymName = '" + toponymName + '\'' + 
			",countryId = '" + countryId + '\'' + 
			",fcl = '" + fcl + '\'' + 
			",population = '" + population + '\'' + 
			",countryCode = '" + countryCode + '\'' + 
			",name = '" + name + '\'' + 
			",fclName = '" + fclName + '\'' + 
			",adminCodes1 = '" + adminCodes1 + '\'' + 
			",countryName = '" + countryName + '\'' + 
			",fcodeName = '" + fcodeName + '\'' + 
			",adminName1 = '" + adminName1 + '\'' + 
			",lat = '" + lat + '\'' + 
			",fcode = '" + fcode + '\'' + 
			"}";
		}
}
