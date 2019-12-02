package earthquakes.searches;

public class EqSearch{

    private int distance;
    private int minmag;

    public int getDistance(){
	return this.distance;
    }
    
    public int getMinmag(){
	return this.minmag;
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
}
