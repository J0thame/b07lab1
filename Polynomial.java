public class Polynomial{
	//array synthax :[type] name
	private double[] coef;
	// constructor must be the sam name as a class
	public Polynomial(){
		coef = new double[1]; // Initialize to [0]
		coef[0] = 0;		
	}
	public Polynomial(double[] array){
		coef = new double[array.length];// allocating memeory
		for(int i = 0;i<array.length;i++){
			coef[i] = array[i];
		}
	}
	
	public Polynomial add(Polynomial p_object){
	// Find which polynomial has the longer length
	int Mlength = Math.max(this.coef.length,p_object.coef.length);
	//create an array
	double[] sum = new double[Mlength];
	
	for(int i = 0;i<this.coef.length;i++){
		sum[i] += this.coef[i];
	}
	
	
	for(int i= 0;i<p_object.coef.length;i++){
		sum[i] += p_object.coef[i];
	}
	return new Polynomial(sum);
	}
	
	public double evaluate(double x){
		double sum = 0;
		double power = 1;
		for(int i=0;i<this.coef.length;i++){
			sum += (coef[i]*power);
			power*= x;
		}
		return sum;
	}
	
	public boolean hasRoot(double x){
		return evaluate(x) == 0.0;
	}
}
	