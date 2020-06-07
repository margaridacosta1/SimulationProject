import java.io.File;
import java.io.IOException;

public class Accumulated {
		
	private int maxItStored;
	private int accDelPF; 
	private int accDelOC; 
	private int accDelLocker;
    private double accTotalCosts;


    public Accumulated() {
      this.maxItStored = 0;
      this.accDelPF = 0; 
      this.accDelOC = 0; 
      this.setAccDelLocker(0);
      this.accTotalCosts = 0.0;
    }

    


	  public int getMaxItStored() {
		return maxItStored;
	}



	public void setMaxItStored(int maxItStored) {
		this.maxItStored = maxItStored;
	}

	public int getAccDelPF() {
		return accDelPF;
	}

	public void setAccDelPF(int accDelPF) {
		this.accDelPF = accDelPF;
	}


	public int getAccDelOC() {
		return accDelOC;
	}


	public void setAccDelOC(int accDelOC) {
		this.accDelOC = accDelOC;
	}

	public int getAccDelLocker() {
		return accDelLocker;
	}

	public void setAccDelLocker(int accDelLocker) {
		this.accDelLocker = accDelLocker;
	}


	public double getAccTotalCosts() {
		return accTotalCosts;
	}

	public void setAccTotalCosts(double accTotalCosts) {
		this.accTotalCosts = accTotalCosts;
	}


	static Accumulated accumulatedThings() throws IOException {

	      Day day = new Day();
	      String filename = Simulation.probCompensation + "output.csv";  
	      File f = CsvWtriter.createFile(filename);
	      CsvWtriter.saveHeader(f);
	      Accumulated acc = new Accumulated();
	      int accumulatedDelPF = 0; 
	      int accumulatedDelOC = 0; 
	      int accumulatedDelLock = 0;
	      double accumulatedTotalCosts = 0.0;
	      int maxItStored = Integer.MIN_VALUE;

	      for(int i = 0; i < Simulation.numberDays; i++) {
	          day = Day.simulateADay(day.getUndeliveredPackagesLock(), day.getUndeliveredPackagesHome());
		      accumulatedDelPF += day.getDeliveriesPF();
		      accumulatedDelOC += day.getDeliveriesOc();
		      accumulatedDelLock += day.getDeliveriesLock();
	          accumulatedTotalCosts += day.getTotalcost();
	          CsvWtriter.saveRecord(i+1, day, accumulatedDelPF, accumulatedDelOC, accumulatedDelLock, accumulatedTotalCosts, f);
	          if(maxItStored <= day.getTotalPackagesLock()) maxItStored = day.getTotalPackagesLock();
	      }
	      
      acc.setAccTotalCosts(accumulatedTotalCosts);
      acc.setAccDelPF(accumulatedDelPF);
      acc.setAccDelOC(accumulatedDelOC);
      acc.setAccDelLocker(accumulatedDelLock);
	  acc.setMaxItStored(maxItStored);
	    
	      return acc;
	  }
	

}
