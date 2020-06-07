import java.util.Scanner;
import java.io.IOException;
import java.util.LinkedList;
import java.util.concurrent.TimeUnit;

public class Simulation {
	
	  private static int observations = 0;
	  public static int numberDays = 120;
	  
	  public static double confidence = 0.0;
	  
	  public static double probOCdelivery = 0.0;
	  public static double probCompensation = 0.0;
	   
	  private static double timeTaken = 0.0;
	  	  	
	  //creation of things's trust interval 
	  static Interval[] getTrustInterval(final double alpha, final LinkedList<Accumulated> acc) {

	    final LinkedList<Double> maxItStored = new LinkedList<Double>();
	    final LinkedList<Double> accTotalCost = new LinkedList<Double>();
	   
	    
	    for(Accumulated i : acc) {
	      maxItStored.add((double)i.getMaxItStored());
	      accTotalCost.add(i.getAccTotalCosts());
	    }
	   

	    final Interval arrayIntervals[] = new Interval[4];

	    arrayIntervals[0] = Interval.TrustInterval(alpha,maxItStored);
	    arrayIntervals[1] = Interval.TrustInterval(alpha,accTotalCost);
	       
	    
	    return arrayIntervals;
	    
	  }

	  //create a simulator with n observations
	  static Interval[] simulatorObservations() throws IOException {

		  long startTime = System.currentTimeMillis();
		  
	    final LinkedList<Accumulated> LinkedLinkedListAccumulated = new LinkedList<>();

	    for (int i = 0; i < observations; i++) {
	      Accumulated acc = Accumulated.accumulatedThings();
	      LinkedLinkedListAccumulated.add(acc);
	    }

	    Interval[] interval = getTrustInterval(confidence, LinkedLinkedListAccumulated);
	    	    	
	    timeTaken = (System.currentTimeMillis() - startTime);
	    
	    return interval;
	  }

	  //find interval that has a best compensation
	  static Interval bestCompensation(LinkedList<Interval> ListIntervals) {

		 double min = Double.MAX_VALUE;
		 
		 Interval intervalSaved = new Interval();

		 for(Interval interval : ListIntervals) {
			 if(min >= interval.getUpper()) {
				 min = interval.getUpper();
				 intervalSaved = interval;
			 	}
			 }

		 return intervalSaved;

		}
	  
	  static LinkedList<Double> compensationDefaults() {
		
		   LinkedList<Double> list = new LinkedList<>();
		   
		   list.add(0.0); 
		   list.add(0.5);
		   list.add(1.0);
		   list.add(1.5);
		   list.add(1.8);
		  
		   return list;
	  }

	  //match a probability with compensation, according to the given table 
	 static double matchProbCompensation(double compensation) {

		  if(compensation == 0.0) {
			  return 0.01;
		  }

		  else if(compensation == 0.5) {
			  return 0.25;
		  }

		  else if(compensation == 1.0) {
			  return 0.50;
		  }

		  else if(compensation == 1.5) {
			  return 0.60;
		  }

		  else if(compensation == 1.8) {
			  return 0.75;
		  }
		  
		  else 
			  return probOCdelivery;
	  }
	 
	 public final static void clearConsole()
	 {
	     try
	     {
	       Runtime.getRuntime().exec("clear");
	     }
	     catch (final Exception e)
	     {
	         //  Handle any exceptions.
	     }
	 }

		  public static void main(final String[] args) throws InterruptedException, IOException {

		  Scanner input = new Scanner(System.in);

		  final LinkedList<Interval> listIntervals = new LinkedList<>();
		  
		  LinkedList<Double> listCompensation = new LinkedList<>();
		  
          int choose = 0;

         

		  while(true){
	            System.out.println();
	            System.out.println("THE DELIVERY GAME");
	            System.out.print("Number of observations: ");
	            observations = input.nextInt(); 
	            while(observations == 1) {
	            	System.out.println("Number 1 isn't allowed! Choose other number: ");
	            	 System.out.print("Number of observations: ");
	            	observations = input.nextInt();
	            }
	            System.out.print("Number of days: ");
	            numberDays = input.nextInt();
	            System.out.print("Confidence Degree:"); 
	            confidence = input.nextDouble();
	            clearConsole();
	            System.out.println("1-Compensations and probabilitys default");
	            System.out.println("2 - Choose other values: ");
	            choose = input.nextInt();
	            switch(choose) {
	             case 1: listCompensation = compensationDefaults();
	            	 break;
	             case 2:   
	            	  System.out.print("Compensation: ");
	            	  double add = input.nextDouble();
	            	  listCompensation.add(add);
	            	  System.out.print("Probability: ");
	            	  probOCdelivery = input.nextDouble(); 
	            	  break;
	             default: break;
	            }
	           
	            
	            System.out.println("Implement a simulation model for this system over " + numberDays + " days, for each of the scenarios concerning the compensation offered to OCs.");
	            System.out.println();
	            System.out.println("(1) Indentify " + confidence + " confidence intervals for a sample of " + observations + " observations for:");
	            System.out.println("1 - the expected total cost, for each compensation value");
	            System.out.println("2 - the expected maximum number of items stored in the locker in these" + numberDays + "days");
	            System.out.println();
	            System.out.println("(2) Determine which compensation should UPzon offer to OCs");
	            System.out.println("3 - Calculate");
	            System.out.println();
	            System.out.print("Option: ");
	            choose = input.nextInt();
	            clearConsole();
	            switch(choose){
	            	case 1: 
	            		for(Double i: listCompensation) {
	            			probOCdelivery = matchProbCompensation(i);
	            			probCompensation = i;
	            			Interval[] interval = simulatorObservations();
	            			System.out.println("Compensation: " + probCompensation); 
	            			System.out.println("Trust Interval of Total Costs: [ " + interval[1].getLower() + " - " + interval[1].getUpper() + " ]");
	            			listIntervals.add(interval[1]);
	            			System.out.println("Time Taken " + timeTaken);
	            		}
	            		clearConsole();
	            		break;
	            	
	            	case 2:
	            		for(Double i: listCompensation) {
	            			probOCdelivery = matchProbCompensation(i);
	            			probCompensation = i;
	            			Interval[] interval = simulatorObservations();
	            			System.out.println("Compensation: " + probCompensation); 
	            			System.out.println("Trust Interval of Total Costs: [ " + interval[0].getLower() + " - " + interval[0].getUpper() + " ]");
	            			System.out.println("Time Taken " + timeTaken);
	            		}
	            		clearConsole();
	            		break;
	            	
	            	case 3: 
	            		Interval interval = bestCompensation(listIntervals);
	            		System.out.println("The optimal compensation is " + interval.getCompensation() + "€, with a minimal total cost in the interval [ " + interval.getLower() + " - " + interval.getUpper() + " ]");
	            		clearConsole(); 
	            		break;
	            		
	            	default:
	                    System.out.println();
	                    System.out.println("Choose other number");
	                    TimeUnit.MILLISECONDS .sleep(2);
	                    continue;
	            }

	            System.out.println();
	            System.out.print("Run another simulation? (y/n): ");
	            String answer = input.next();
	            if(answer.equals("y") || answer.equals("Y")) continue;
	            else break;
	        }
		  	input.close();
		  }
		 	}
