package ie.cit.Taxes.domain;

public class Rate implements Component {
	int id;
	
	float band, rate;
	
	public Rate(){
		
	}
	
	public Rate(int id, float band, float rate){
		
		this.id=id;
		this.band = band;
		this.rate = rate;

	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public float getBand() {
		return band;
	}

	public void setBand(float band) {
		this.band = band;
	}

	public float getRate() {
		return rate;
	}

	public void setRate(float rate) {
		this.rate = rate;
	}

	@Override
	public String toString(){
		String transformedBand = Float.toString(this.band);
		if (this.band == -1.0){
			transformedBand = "until the end";
		}else{
			if(this.band == 0.0){
				transformedBand = "Solidarity tax";
			}
		}
		
		return "ID: " + Character.toString((char) (65 + this.id - 1)) + " | band: " + transformedBand + " | rate: " + this.rate + "%.";
	}

}
