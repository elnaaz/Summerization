
import java.lang.Math.*;
import java.util.*;
import org.apache.commons.math.stat.descriptive.DescriptiveStatistics;


public class MathFuncs {
	
	public static ArrayList<Double> normalizedArray(ArrayList<Double> inputarray){
	
		ArrayList<Double> array=new ArrayList<Double>();
		
        for(int i=0; i<inputarray.size();i++){
        	double value=(double)MathFuncs.normalize(inputarray,i);
        	array.add(value);
        }
        
        return array;

	}
	public static double normalize(ArrayList<Double> inputArray, int index) {

		DescriptiveStatistics stats = new DescriptiveStatistics();

		for( int i = 0; i < inputArray.size(); i++) {
		        stats.addValue(inputArray.get(i));
		}
		double mean = stats.getMean();
		double std = stats.getStandardDeviation();

//		System.out.println("column "+index+" : min: "+ stats.getMin()+" max: "+ stats.getMax()+ " mean: "+ stats.getMean()+ " std: "+ stats.getStandardDeviation());

		double normal_rand= ((double)inputArray.get(index)-mean)/std;
		if(std==0)
			normal_rand=0;
		return normal_rand;
	}

	public double variance(double[] population) {
		long n = 0;
	    double mean2 = 0;
	    double s = 0.0;
	    for (double x2 : population) {
	    	n++;
	        double delta = x2 - mean2;
	        mean2 += delta / n;
	        s = s + delta * (x2 - mean2);
	   }
	        // if you want to calculate std deviation
	        // of a sample change this to (s/(n-1))
	        return (s / n);
	}

	public double standard_deviation(double[] population) {

		int i = 7;
	    int j = -9;
	    double x = 72.3;
	    double y = 0.34;

	    System.out.println("i is " + i);
	    System.out.println("j is " + j);
	    System.out.println("x is " + x);
	    System.out.println("y is " + y);

	    System.out.println("|" + i + "| is " + Math.abs(i));
	    System.out.println("|" + j + "| is " + Math.abs(j));
	    System.out.println("|" + x + "| is " + Math.abs(x));
	    System.out.println("|" + y + "| is " + Math.abs(y));

	     System.out.println(x + " is approximately " + Math.round(x));
	     System.out.println(y + " is approximately " + Math.round(y));

	     System.out.println("The ceiling of " + i + " is " + Math.ceil(i));
	     System.out.println("The ceiling of " + j + " is " + Math.ceil(j));
	     System.out.println("The ceiling of " + x + " is " + Math.ceil(x));
	     System.out.println("The ceiling of " + y + " is " + Math.ceil(y));

	     System.out.println("The floor of " + i + " is " + Math.floor(i));
	     System.out.println("The floor of " + j + " is " + Math.floor(j));
	     System.out.println("The floor of " + x + " is " + Math.floor(x));
	     System.out.println("The floor of " + y + " is " + Math.floor(y));

	     System.out.println("min(" + i + "," + j + ") is " + Math.min(i,j));
	     System.out.println("min(" + x + "," + y + ") is " + Math.min(x,y));
	     System.out.println("min(" + i + "," + x + ") is " + Math.min(i,x));
	     System.out.println("min(" + y + "," + j + ") is " + Math.min(y,j));

	     System.out.println("max(" + i + "," + j + ") is " + Math.max(i,j));
	     System.out.println("max(" + x + "," + y + ") is " + Math.max(x,y));
	     System.out.println("max(" + i + "," + x + ") is " + Math.max(i,x));
	     System.out.println("max(" + y + "," + j + ") is " + Math.max(y,j));

	     System.out.println("Pi is " + Math.PI);
	     System.out.println("e is " + Math.E);

	     double angle = 45.0 * 2.0 * Math.PI/360.0;
	    System.out.println("cos(" + angle + ") is " + Math.cos(angle));
	    System.out.println("sin(" + angle + ") is " + Math.sin(angle));

	    double value = 0.707;
	    System.out.println("acos(" + value + ") is " + Math.acos(value));
	    System.out.println("asin(" + value + ") is " + Math.asin(value));
	    System.out.println("atan(" + value + ") is " + Math.atan(value));

	    System.out.println("exp(1.0) is "  + Math.exp(1.0));
	    System.out.println("exp(10.0) is " + Math.exp(10.0));
	    System.out.println("exp(0.0) is "  +  Math.exp(0.0));

	    System.out.println("log(1.0) is "    + Math.log(1.0));
	    System.out.println("log(10.0) is "   + Math.log(10.0));
	    System.out.println("log(Math.E) is " + Math.log(Math.E));

	    System.out.println("pow(2.0, 2.0) is "  + Math.pow(2.0,2.0));
	    System.out.println("pow(10.0, 3.5) is " + Math.pow(10.0,3.5));
	    System.out.println("pow(8, -1) is "     + Math.pow(8,-1));

	    for (i=0; i < 10; i++) {
	      System.out.println(
	       "The square root of " + i + " is " + Math.sqrt(i));
	    }

	    System.out.println("Here's one random number: " + Math.random());
	    System.out.println("Here's another random number: " + Math.random());

	    return Math.sqrt(variance(population));
	}

	public void test(double[] inputArray){
		// Get a DescriptiveStatistics instance
		DescriptiveStatistics stats = new DescriptiveStatistics();
		// Add the data from the array
		for( int i = 0; i < inputArray.length; i++) {
		        stats.addValue(inputArray[i]);
		}
		// Compute some statistics
		double mean = stats.getMean();
		double std = stats.getStandardDeviation();
		//double median = stats.getMedian();
	}
}
