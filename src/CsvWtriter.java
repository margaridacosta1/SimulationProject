import java.io.*;

public class CsvWtriter {
	
	public static File createFile(String filepath) {
		
		File file = new File(filepath);
		
		return file;
	}
	
	public static void saveHeader(File file) throws IOException {
		
		FileWriter fos = new FileWriter(file,true);
		PrintWriter pw = new PrintWriter(fos);
		pw.println("Day, New_Packages_Home, New_Packages_locker, Deliveries_Pf, Deliveries_Oc, Deliveries_Lockr, PFDEL_ACC, OCDEL_ACC, LOCKDEL_ACC, Costs_PF, Costs_OC, COSTS_AC, STATUS_HOME, STATUS_LOCKR");
		pw.close();
	}
	
	public static void saveRecord(int ID, Day day, int accPF, int accOC, int accLocker, double accCosts, File file) throws IOException {
		
	try {
		FileWriter fos = new FileWriter(file,true);
		PrintWriter pw = new PrintWriter(fos);
		
		pw.println(ID + "," + day.getNewDeliveriesHome() + "," + day.getNewDeliveriesLocker() + "," + day.getDeliveriesPF() + "," + day.getDeliveriesOc() + "," + day.getDeliveriesLock() + "," + accPF + "," + accOC + "," +  accLocker + "," + day.getHomecost() + "," + day.getOccost() + "," + accCosts + "," + day.getUndeliveredPackagesHome() + "," + day.getUndeliveredPackagesLock());
		pw.close();
				
	} catch (FileNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} 
	
		
	}

}
