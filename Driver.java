import java.io.*;
import java.util.Arrays;
public class Driver {

	public static void main(String [] args) {
		
		Polynomial p = new Polynomial();
		System.out.println(p.evaluate(1));
		double [] c_coefficients = {2, 3};
		int [] c_exponents = {0, 1};
		Polynomial p1 = new Polynomial(c_coefficients, c_exponents);
		
		p1.evaluate(1);
		try{
			File f1 = new File("polynomial1.txt");
			Polynomial poly1 = new Polynomial(f1);
			File f2 = new File("polynomial2.txt");
			Polynomial poly2 = new Polynomial(f2);
	        Polynomial a = poly1.add(poly2);
	        Polynomial po = poly1.multiply(poly2);
	        a.saveToFile("sum.txt");

	   	po.saveToFile("Multiplication.txt");
		} catch (IOException e) {
			e.printStackTrace();
		}


		
	}	
}