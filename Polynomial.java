import java.util.Arrays;
import java.io.File;
import java.io.BufferedReader;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.FileWriter;
public class Polynomial {
		double[] coefficients;
		int[] exponents;
 			
			//methods
			public Polynomial() {
				coefficients = null;
				exponents = null;
			}
			public Polynomial(double[] mycoefficients, int[] myexponents) 
			   {
				int a=0;
				a = myexponents.length;
				coefficients = new double [a];
				exponents = new int[a];
				for(int i=0;i<a;i++) 
				{
					coefficients[i] = mycoefficients[i];
					exponents[i] = myexponents[i];
				}
			}
			public Polynomial(File f) throws FileNotFoundException
			{
				if(f.length() == 0)
				{
					this.coefficients = null;
					this.exponents = null;
				}
				else
				{
				Scanner newScanner = new Scanner(f); 
				
		        String s = newScanner.nextLine();
                         int l =0;
                         int i =0;
                        if(s.startsWith("-"))
                        {
                          l=-1;
                          i = 1; 
                        }  
		        s = s.replace("-", "+-");
		        String[] t = s.split("\\+");
                        l = l+t.length;
		        int[] exp = new int[l]; 
		        double[] coff = new double[l];
		        for(int j=0;j<l;j++)
                        { 
                            if (t[i+j].equals("x"))
                            {
                              coff[j] = 1;
                              exp[j] = 1;
                              continue;
                             }  
                            if (t[i+j].equals("-x"))
                            {
                              coff[j] = -1;
                              exp[j] = 1;
                              continue;
                             }
		            String[] t_2 = t[j+i].split("x");
                            if(t_2.length == 0)
                               coff[j] = 1;
                            else if(t_2[0].equals(""))
                            {
		            coff[j] =1;
                            }
                            else if(t_2[0].equals("-"))
                            {
                            coff[j] =-1;
                             }
                            else if(t_2[0].equals("+"))
                             {
                              coff[j] =1;
                              }
                            else if(!(t_2[0].isEmpty()))
                            { 
                              coff[j] = Double.parseDouble(t_2[0]);

                            } 
		            if (t_2.length == 1&&(t[i+j].indexOf("x") == -1))
		            {
		                exp[j] = 0; 
		            }
		            else if(t_2.length == 1&&(t[i+j].indexOf("x") != -1))
                            { 
                               exp[j]=1;
		                 
		            }
                            else if(!(t_2.length == 0))
                            {
                              exp[j] = Integer.parseInt(t_2[1]);
                             }
		        }
		        this.coefficients = coff; 
		        this.exponents = exp; 
		        newScanner.close();
				}
				}
			public Polynomial Zero_coff_remover()
			{
				int z=this.coefficients.length;
				for(int i =0;i<this.coefficients.length;i++)
				{
					if(this.coefficients[i] == 0)
					{
					   z--;
					}
				}
				double [] r_coefficients;
	             int[] r_exponents;
	             r_coefficients = new double[z];
	             r_exponents = new int[z];
	             int j=0;
	            for(int i =0;i<this.coefficients.length;i++)
	            {
	            	if(this.coefficients[i] != 0)
	            	{
	            		r_coefficients[j] = this.coefficients[i];
	            		r_exponents[j] = this.exponents[i];
                                j++;
	            	}
	            }
	            Polynomial P = new Polynomial(r_coefficients,r_exponents);

		        return P;
			}
			public Polynomial Common_exponent_remover(Polynomial other)
			{
				int n =0;
				Polynomial P = new Polynomial(this.coefficients,this.exponents);
				for(int i=0; i<P.coefficients.length;i++)
				{

					for(int j=0;j < other.coefficients.length ;j++)
					{
					    if(P.exponents[i] == other.exponents[j])
                                                {
					    	P.coefficients[i] = 0;
                                                }

				    }
				} 

				Polynomial P_1= new Polynomial();
                                P_1 = P.Zero_coff_remover();
                                return P_1;
			}
			public Polynomial Merger(Polynomial other)
			{
				int j = this.coefficients.length+other.coefficients.length;
				int [] r_exponents;
				double[] r_coefficients;
				r_exponents = new int[j];
				r_coefficients = new double[j];
				for(int i =0;i<j;i++)
				{
					if(i<this.coefficients.length)
					{
						r_exponents[i] = this.exponents[i];
						r_coefficients[i] = this.coefficients[i];
					
					}
					else
					{
						r_exponents[i] = other.exponents[i - this.exponents.length];
						r_coefficients[i] = other.coefficients[i - this.exponents.length];
					}
				}
				Polynomial P = new Polynomial(r_coefficients,r_exponents);
				return P;
				
			}
			public Polynomial common_coefficient_adder(Polynomial other) 
			{
				double [] c_coefficients;
	             int[] c_exponents;
	             c_coefficients = new double[this.coefficients.length];
	             c_exponents = new int[this.coefficients.length];                          
					for(int i=0; i<this.coefficients.length;i++)
					{
						c_exponents[i] = this.exponents[i];
						for(int j=0;j < other.coefficients.length ;j++)
						{
						    if(this.exponents[i] == other.exponents[j])
						    	c_coefficients[i] = this.coefficients[i]+ other.coefficients[j];

					    }
			        }
					Polynomial P_1 = new Polynomial(c_coefficients,c_exponents);
					P_1 = P_1.Zero_coff_remover();
                    return P_1;        
			}
			public Polynomial add(Polynomial other)
			{
				if(this.coefficients == null && other.coefficients == null)
					return null;
				Polynomial P_1 = new Polynomial();
				if(this.coefficients == null)
				{
					int a=0;
					a = other.exponents.length;
					double[] r_coefficients = new double [a];
					int[] r_exponents = new int[a];
					for(int i=0;i<a;i++) 
					{
						r_coefficients[i] = other.coefficients[i];
						r_exponents[i] = other.exponents[i];
					}
					Polynomial A= new Polynomial(r_coefficients,r_exponents);
					return A;
				}
				
				if(other.coefficients ==null)
				{
					int a=0;
					a = this.exponents.length;
					double[] r_coefficients = new double [a];
					int[] r_exponents = new int[a];
					for(int i=0;i<a;i++) 
					{
						r_coefficients[i] = this.coefficients[i];
						r_exponents[i] = this.exponents[i];
					}
					Polynomial A= new Polynomial(r_coefficients,r_exponents);
					return A;
				}
              
              P_1 = this.common_coefficient_adder(other);
              Polynomial P_2 = new Polynomial();
              P_2 = this.Common_exponent_remover(other);
              Polynomial P_3 = new Polynomial();
              P_3 = other.Common_exponent_remover(this);
              Polynomial P_4 = new Polynomial(); 
              P_4 = P_1.Merger(P_2);
              P_4 = P_4.Merger(P_3);
              P_4 = P_4.Zero_coff_remover();
              return P_4;
             }

			public double evaluate(double x)
			{
				double y=0;
				double z=1;
                           if(this.coefficients == null)
                               return 0;
			   for(int i =0; i < this.coefficients.length; i++)
			   { 
				   z = 1;
				   for(int j=0; j<this.exponents[i] ;j++)
				   {
					      z = z*x;  
			       }
				   y = y+(z* this.coefficients[i]);
			   }
			   return y;
			}
			public boolean hasRoot(double x)
			{
						if(evaluate(x) == 0 )
							return true;
						else
							return false;
			}
			public Polynomial multiply(Polynomial other)
			{ 
		        if(this.coefficients == null || other.coefficients == null)
					return null;
			 Polynomial final_product = new Polynomial();
			 int[] oth_exp;
			 oth_exp = new int[other.exponents.length];
			 double[] oth_coff;
			 oth_coff = new double[other.coefficients.length];
			 
			 Polynomial intermediate_polynomial = new Polynomial(oth_coff,oth_exp);
				for(int i = 0; i < this.exponents.length;i++)
				{
					for(int j = 0; j<other.exponents.length;j++)
					{
					  intermediate_polynomial.coefficients[j] = other.coefficients[j]*this.coefficients[i];
					  intermediate_polynomial.exponents[j] = other.exponents[j]+this.exponents[i];
					}
					final_product = final_product.add(intermediate_polynomial);
				}
                         return final_product;
			}
			public void saveToFile(String s) throws IOException
			{

				FileWriter W = new FileWriter(s); 
		        for (int i = 0; i < coefficients.length; i++){
		            if (coefficients[i] > 0 && i==0)
		            {
		                W.write(Double.toString(coefficients[i])); 
		            }
		            
		            else if (coefficients[i] > 0 && i !=0){
		                W.write("+" + coefficients[i]); 
		            }
		            else if(coefficients[i] < 0)
		            {
		                W.write(Double.toString(coefficients[i])); 
		            }
		            if (exponents[i] != 0){
		                W.write("x" + exponents[i]);
		            }
		        }
		        W.close();
		    
}
}