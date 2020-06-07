import java.util.LinkedList;

import org.apache.commons.math3.distribution.TDistribution;
import org.apache.commons.math3.exception.NotStrictlyPositiveException;

public class Interval {
	
	 	private double lower;
	    private double upper;
	    private double compensation;
	    private int maxPackages;
	    
	    
	    public Interval() {
	      this.lower = 0.0;
	      this.upper = 0.0;
	      this.compensation = 0.0;
	      this.maxPackages = 0;
	    }


	    public double getLower() {
			return lower;
		}

		public void setLower(double lower) {
			this.lower = lower;
		}


		public double getUpper() {
			return upper;
		}

		public void setUpper(double upper) {
			this.upper = upper;
		}

		
		public double getCompensation() {
			return compensation;
		}
		
		public void setCompensation(double compensation) {
			this.compensation = compensation;
		}


		public int getMaxPackages() {
			return maxPackages;
		}

		public void setMaxPackages(int maxPackages) {
			this.maxPackages = maxPackages;
		}


	//auxiliar function to sum data
	static double sum(LinkedList<Double> data) {

	  double sum = 0.0;

	  for(Double i: data) {
		 sum += i;
	  }

	 return sum;
}
	
	static int maxStore(LinkedList<Double> data) {
		
		double max = Integer.MIN_VALUE; 
		
		for(Double i: data) {
			if(max < i) max = i;
		}
		
		return (int)max;
	}

	    //create a trust interval
		  static Interval TrustInterval(final double confidence, final LinkedList<Double> data) throws NotStrictlyPositiveException {
			
			double sumTotal = sum(data);
			
			int maxStore = maxStore(data);

		    final double media = sumTotal/(data.size());

		    
		    double variance = 0.0;
		    	    
		    for(int j = 0; j < data.size(); j++) { 
		    		    	
		      variance += Math.pow((data.get(j) - media), 2);
		    
		    }
		    
		    variance = variance / (double)(data.size()-1);
		   	    
		    final TDistribution t_Student = new TDistribution(data.size()-1);
		    
		    //valor calculado pela biblioteca do Java
		    final double criticalValue = (t_Student.inverseCumulativeProbability(1.0 -(1 - confidence)/2.0));
		    		 
		    final double lower = media - (criticalValue * Math.sqrt(variance/(double)data.size()));
		    final double upper = media + (criticalValue * Math.sqrt(variance/(double)data.size()));

		    final Interval interval = new Interval();

		    interval.setLower(lower);
		    interval.setUpper(upper);
		    interval.setCompensation(Simulation.probCompensation);
		    interval.setMaxPackages(maxStore);
		    
		    return interval;
		  }

	
}
