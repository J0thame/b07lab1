import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;

public class Polynomial{
	//array synthax :[type] name
	private double[] coef;
	private int[] exp;
	// constructor must be the sam name as a class and no return type
	public Polynomial(){
		exp = new int[1];
		exp[0] = 0;
		coef = new double[1]; // Initialize to [0]
		coef[0] = 0;		
	}
	
	public Polynomial(double[] arr_coef, int[] arr_exp){
		exp = new int[arr_exp.length];
		for(int i = 0; i< arr_exp.length; i++){
			exp[i] = arr_exp[i];
		}
		coef = new double[arr_coef.length];// allocating memeory
		for(int i = 0;i<arr_coef.length;i++){
			coef[i] = arr_coef[i];
		}
	}
	// This adds the polynomial with the object together to create a new polynomial
	public Polynomial add(Polynomial p){
	// Find which polynomial has the longer length
	int Mlength = this.coef.length+ p.coef.length;
	double[] coef = new double[Mlength];
	int[] exp = new int[Mlength];
	int current_length = 0;
	for(int i=0;i<this.coef.length;i++){
		coef[i] = this.coef[i];
		exp[i] = this.exp[i];
		current_length++;
	}
	int temp_length = current_length;
	for(int j=0;j<p.coef.length;j++){
		boolean found = false;
		for(int i = 0; i< temp_length; i++){
			if (p.exp[j]== exp[i]){
				coef[i]+= p.coef[j];
				found = true;
			}
        }			
		if(found == false){
		coef[current_length] = p.coef[j];
		exp[current_length] = p.exp[j];
		found = false;
		current_length++;
		}
	}
	double[] rcoef = new double[current_length]; 
	int [] rexp = new int[current_length];
	for(int k = 0; k<current_length; k++){
		rcoef[k]= coef[k];
		rexp[k]= exp[k];
	}
	return new Polynomial(rcoef,rexp);
	}
	
	public double evaluate(double x){
		double sum = 0;
		for(int i=0;i<this.coef.length;i++){
			sum += coef[i]*Math.pow(x,exp[i]);
		}
		return sum;
	}
	
	public boolean hasRoot(double x){
		return evaluate(x) == 0.0;
	}
	public Polynomial multiply(Polynomial p){
		int max_length = p.coef.length * this.coef.length;
		double[] temp_a_coef = new double[max_length];
		int[] temp_a_exp= new int[max_length];
		int count = 0;
		boolean found = false;
		for(int i = 0;i<this.coef.length;i++){
			for(int j = 0; j < p.coef.length;j++){
				found = false;
				int sum_of_exp = this.exp[i] + p.exp[j];
				for(int k = 0; k<count;k++){
					if(temp_a_exp[k] == sum_of_exp){
						temp_a_coef[k] += (this.coef[i] * p.coef[j]);
						found = true;
						break;
					}
				}
				if (found == false){
					temp_a_coef [count] = (this.coef[i] * p.coef[j]);
					temp_a_exp[count] = (this.exp[i] + p.exp[j]);
					count++;
				}
			}
		}			
		double[] a_coef = new double[count];
		int[] a_exp = new int[count];
		for(int j=0; j<count; j++){
			a_coef[j] = temp_a_coef[j];
			a_exp[j] = temp_a_exp[j];
		}
		return new Polynomial(a_coef,a_exp);
	}
	public Polynomial(File f) throws IOException{
		BufferedReader input = new BufferedReader(new FileReader(f));
		String line = input.readLine();
		input.close();
		String[] array = line.split("(?=[+-])");
		double[] temp_a_coef = new double[array.length];
		int[] temp_a_exp= new int[array.length];
		int count = 0;
		for(String i:array){
			if(i.contains("x") && i.length() == 2){
				String[] array_2 = i.split("x");
				temp_a_coef[count] = Double.parseDouble(array_2[0]);
				temp_a_exp[count] = Integer.parseInt(array_2[1]);
				count++;				
            }
			else if(i.contains("x") && i.contains("+") && i.length()==3){
				String[] array_2 = i.split("x");
				temp_a_coef[count] = Double.parseDouble(array_2[0]);
				temp_a_exp[count] = Integer.parseInt(array_2[1]);
				count++;
			}
            else if(i.contains("x") && i.contains("+")&& i.length()==2){
				String[] array_2 = i.split("x");
				temp_a_exp[count] = Integer.parseInt(array_2[0]);
				count++;
			}
			else if(i.contains("x") && i.contains("-")&& i.length()==2){
				String[] array_2 = i.split("x");
				temp_a_exp[count] = Integer.parseInt(array_2[0]);
				count++;
			}
			else if(i.contains("x") && i.contains("-")&& i.length()==3){
				String[] array_2 = i.split("x");
				temp_a_coef[count] = Double.parseDouble(array_2[0]);
				temp_a_exp[count] = Integer.parseInt(array_2[1]);
				count++;			
			}
            else if(i.contains("-x") && i.length()== 2){
				temp_a_coef[count] = -1;
                temp_a_exp[count] = 1;
				count ++;
                
            }
			else if(i.contains("x") && i.length()== 1){
				temp_a_coef[count] = 1;
                temp_a_exp[count] = 1;
				count ++;
                
            }
			else{
				temp_a_coef[count] = Double.parseDouble(i);
                temp_a_exp[count] = 0;
		    }
		double[] a_coef = new double[count];
		int[] a_exp = new int[count];
		for(int j=0;j < count; j++){
			this.coef[j] = temp_a_coef[j];
			this.exp[j] = temp_a_exp[j];
        }
	}
	}
	public void saveToFile(String s) throws IOException{
		FileWriter writer = new FileWriter(s);
		for(int i = 0; i<coef.length; i++){
			if(coef[i] > 0){
				writer.write("+");
				writer.write(String.valueOf(coef[i]));
			}
			if(this.exp[i] > 0){
				writer.write("x");
				writer.write(String.valueOf(exp[i]));
			}
		}
		writer.close();
	}
}
		


			
			