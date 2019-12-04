package earthquakes.searches;

public class EqSearch{

    private int distance;
    private int minmag;
    private double lat;
    private double lon;
    private String location;

    public int getDistance(){
	return this.distance;
    }
    
    public int getMinmag(){
	return this.minmag;
    }


    public double getLat(){
        return this.lat;
    }

    public double getLon(){
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

    public void setLat(double lat){
        this.lat = lat;
    }

    public void setLon(double lon){
        this.lon = lon;
    }

    public void setLocation(String location){
        this.location = location;
    }
}
