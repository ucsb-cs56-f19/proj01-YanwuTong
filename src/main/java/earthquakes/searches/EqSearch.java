package earthquakes.searches;

public class EqSearch{

    private int distance;
    private int minmag;
    private long lat;
    private long lon;
    private String location;

    public int getDistance(){
	return this.distance;
    }
    
    public int getMinmag(){
	return this.minmag;
    }


    public long getLat(){
        return this.lat;
    }

    public long getlon(){
        return this.lon;
    }

    public String getLocation(){
        return this.location;
    }

    public EqSearch(){
	this.distance = 0;
	this.minmag = 0;
    }
    
    public void setDistance(int dis){
	this.distance = dis;
    }
    
    public void setMinmag(int min){
	this.minmag = min;
    }

    public void setLat(long lat){
        this.lat = lat;
    }

    public void setLon(long lon){
        this.lon = lon;
    }

    public void setLocation(String location){
        this.location = location;
    }
}
