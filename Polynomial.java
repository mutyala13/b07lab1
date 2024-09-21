
public class Polynomial {
double[] coefficients;
	
	//methods
	public Polynomial() {
		coefficients = new double [0];
	}
	public Polynomial(double[] myarray) 
	   {
		int a;
		a = myarray.length;
		coefficients = new double [a];
		for(int i=0;i<a;i++) 
		{
			coefficients[i] = myarray[i];
		}
	}
	public Polynomial add(Polynomial other)
	{
               int a = 0;
               int b = 0;
               int x = 0;
               a = other.coefficients.length;
               b = this.coefficients.length;
               if(a >= b)
                 x = a;
               else 
                 x = b;
               double [] c;
               c = new double [x];
               Polynomial P = new Polynomial(c); 
		for(int i=0; i < a && i <b; i++)
		{
		P.coefficients[i] = this.coefficients[i]+other.coefficients[i];
		}
                if(a > b)
                 {
                 for(int i=b; i < a ; i++)
		{
		P.coefficients[i] = other.coefficients[i];
		}
                 }
                 else 
                 {
                 for(int i=b; i < a ; i++)
		{
		P.coefficients[i] = other.coefficients[i];
		}

                 }
               return P;
	}
	public double evaluate(double x)
	{
		double y=0;
		double z=1;
	   for(int i =0; i < coefficients.length; i++)
	   {
		   z = 1;
		   for(int j=0; j<i ;j++)
		   {
			   z = z*x;  
	       }
		   y = y+(z* coefficients[i]);
	   }
	   return y;
	}
	public boolean hasRoot(double x)
	{
				if(evaluate(x) == 0)
					return true;
				else
					return false;
	}
	}