import java.util.Random;

public class Day {
	
	private static final int low = 10;
	private static final int high = 50;

	private static final double deliveryPercented = 0.75;
	 

	private static final Random r = new Random();


	private int totalPackagesLock;
    private int undeliveredPackagesHome;
    private int undeliveredPackagesLock;
    private int newDeliveriesHome;
    private int newDeliveriesLocker;
    private int deliveriesPF; 
    private int deliveriesOc;
    private int deliveriesLock;
    private double homecost;
    private double occost;
    private double totalcost;

    public Day() {
      this.totalPackagesLock = 0;
      this.undeliveredPackagesHome = 0;
      this.undeliveredPackagesLock = 0;
      this.newDeliveriesHome = 0;
      this.newDeliveriesLocker = 0;
      this.deliveriesPF = 0; 
      this.deliveriesOc = 0;
      this.deliveriesLock = 0;
      this.homecost = 0.0;
      this.occost = 0.0;
      this.totalcost = 0.0;
    }
    
    public int getTotalPackagesLock() {
    	return totalPackagesLock;
    }
    
    public void setTotalPackagesLock(int totalPackagesLock) {
    	this.totalPackagesLock = totalPackagesLock;
    }

    public int getUndeliveredPackagesHome() {
		return undeliveredPackagesHome;
	}

	public void setUndeliveredPackagesHome(int undeliveredPackagesHome) {
		this.undeliveredPackagesHome = undeliveredPackagesHome;
	}

	public int getUndeliveredPackagesLock() {
		return undeliveredPackagesLock;
	}

	public void setUndeliveredPackagesLock(int undeliveredPackagesLock) {
		this.undeliveredPackagesLock = undeliveredPackagesLock;
	}

	public int getNewDeliveriesHome() {
		return newDeliveriesHome;
	}

	public void setNewDeliveriesHome(int newDeliveriesHome) {
		this.newDeliveriesHome = newDeliveriesHome;
	}

	public int getNewDeliveriesLocker() {
		return newDeliveriesLocker;
	}

	public void setNewDeliveriesLocker(int newDeliveriesLocker) {
		this.newDeliveriesLocker = newDeliveriesLocker;
	}

	public int getDeliveriesOc() {
		return deliveriesOc;
	}

	public void setDeliveriesOc(int deliveriesOc) {
		this.deliveriesOc = deliveriesOc;
	}

	public int getDeliveriesPF() {
		return deliveriesPF;
	}

	public void setDeliveriesPF(int deliveriesPF) {
		this.deliveriesPF = deliveriesPF;
	}

	public int getDeliveriesLock() {
		return deliveriesLock;
	}

	public void setDeliveriesLock(int deliveriesLock) {
		this.deliveriesLock = deliveriesLock;
	}
	

	public double getHomecost() {
		return homecost;
	}

	public void setHomecost(double homecost) {
		this.homecost = homecost;
	}

	public double getOccost() {
		return occost;
	}

	public void setOccost(double occost) {
		this.occost = occost;
	}

	public double getTotalcost() {
		return totalcost;
	}

	public void setTotalcost(double totalcost) {
		this.totalcost = totalcost;
	}

//number totalPackages
static int getPackagesDay() {
  return r.nextInt(high - low + 1) + low;
}

//number of OC who delivered to home
static int getOCtoHome(final int lockerPackageDeliveried) {

    int numOCtoHome = 0;

    for(int i = 0; i < lockerPackageDeliveried; i++) {
      final double prob = r.nextDouble();
      if(prob <= Simulation.probOCdelivery) numOCtoHome++;
    }
    return numOCtoHome;
}

//cost of compensation 
static double ocCost(final int numbOCtoHome, final int deliveriesHome) {
	
	if(numbOCtoHome > deliveriesHome) return (deliveriesHome)*Simulation.probCompensation;

	else return numbOCtoHome * Simulation.probCompensation;
}

//cost of delivery to home
static double homeCost(final int numberDeliveriesHome) {

  double cost = 0.0;

  if(numberDeliveriesHome <= 10) {
    cost = numberDeliveriesHome;
  }

  else {
    cost = 10 + (numberDeliveriesHome - 10)*2;
  }

  return cost;
}

static int undHome(final int underliveredPackages) {
	
	if(underliveredPackages < 0) return 0; 
	
	else return underliveredPackages;
	
}


//quantity of deliveries from the locker 
static int lockerDeliveried(final int deliveriesLock) {

  int lockerDeliveried = 0;

  for(int i = 0; i < deliveriesLock; i++) {
    final double prob = r.nextDouble();
    if(prob <= deliveryPercented) lockerDeliveried++;
  }
  return lockerDeliveried;
}

//simulate a day
static Day simulateADay(int undeliveredPackagesLock, int undeliveredPackagesHome) {
	  
    final Day day = new Day();
 
	
    final int totalPackages = getPackagesDay();
    
    int deliveriesHome = 0;
    int deliveriesLock = 0;
    	      
    for(int i = 0; i < totalPackages; i++) {
	      final int random = r.nextInt(2);
	      if(random < 1) deliveriesHome++;
	      else deliveriesLock++;
    }
    
    day.setNewDeliveriesLocker(deliveriesLock);
    day.setNewDeliveriesHome(deliveriesHome);

        
    deliveriesLock += undeliveredPackagesLock;	
    
    
    final int lockerPackageDeliveried = lockerDeliveried(deliveriesLock);

    final int numberofOc = getOCtoHome(lockerPackageDeliveried);
    
    final int totalPackagesInLocker = deliveriesLock + deliveriesHome;
        
    day.setDeliveriesLock(lockerPackageDeliveried);
    day.setDeliveriesOc(numberofOc);
    day.setDeliveriesPF(undeliveredPackagesHome);
    day.setTotalPackagesLock(totalPackagesInLocker);

    
    final double costhome = homeCost(undeliveredPackagesHome);
    
    
    final double occost = ocCost(numberofOc, deliveriesHome);

    
    final double totalcost = costhome + occost;
    
    day.setHomecost(costhome);
    day.setOccost(occost);
    day.setTotalcost(totalcost);
    
       
    undeliveredPackagesHome = deliveriesHome - numberofOc;
    
    undeliveredPackagesHome = undHome(undeliveredPackagesHome);
    
    undeliveredPackagesLock = deliveriesLock - lockerPackageDeliveried;
    
    day.setUndeliveredPackagesHome(undeliveredPackagesHome);
    day.setUndeliveredPackagesLock(undeliveredPackagesLock);
    
   

    return day;
	}

}
