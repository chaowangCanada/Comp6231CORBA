package Client;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Scanner;

import Config.PublicParamters.*;


/**
 * Manager class to create manager access server depends on location. 
 * manager can only access its locaion's server
 * @author Chao
 *
 */

public class ManagerClient {


	protected static int managerIDbase =1000; // static to mark unique manager ID
	private String managerID;	
	private File log = null;
	private Registry registry; // each manger only have 1 registry, cannot linked to 2 servers
	private DCMSInterface intrfc;
	
	public ManagerClient(Location l) throws IOException, NotBoundException{
		managerID = l.toString() + managerIDbase;
		log = new File(managerID+".txt");
		if(! log.exists())
			log.createNewFile();
		else{
			if(log.delete())
				log.createNewFile();
		}
		registry = LocateRegistry.getRegistry(l.getPort());
		managerIDbase++;
	}
	
	public String getManagerID(){
		return managerID;
	}
	
	/**
	 * eacher manager has its own log, no race condition
	 * @param str
	 * @throws IOException
	 */
	public void writeToLog(String str) throws IOException{
		 FileWriter writer = new FileWriter(log,true);
		 Date date = new Date();
		 writer.write(PublicParamters.dateFormat.format(date) +" : " + str  +"\n");
		 writer.flush();
		 writer.close();
	}
	
	/**
	 * no need, if need to switch manager to different location
	 * @param l
	 * @throws RemoteException 
	 */
	public void changeLocation(Location l) throws RemoteException{
		String tmp = managerID.substring(3);
		managerID = l.toString() + tmp;
		registry = LocateRegistry.getRegistry(l.getPort());
	}
	
	/**
	 * manager side call Server createTRecord through interface
	 * @param firstName
	 * @param lastName
	 * @param address
	 * @param phone
	 * @param special
	 * @param loc
	 * @throws RemoteException
	 * @throws IOException
	 * @throws NotBoundException
	 */
	public void createTRecord(String firstName, String lastName, String address, 
			  					String phone, Specialization special, Location loc) throws RemoteException, IOException, NotBoundException{
		intrfc = (DCMSInterface)registry.lookup(managerID.substring(0, 3)); //dynamic bindling clinet to server.
		String reply = intrfc.createTRecord(firstName, lastName, address, phone, special, loc);
		System.out.println(reply);
		writeToLog(reply);
		
	}
	
	/**
	 * manager side call Server createSRecord through interface
	 * @param firstName
	 * @param lastName
	 * @param course
	 * @param status
	 * @param statusdate
	 * @throws IOException
	 * @throws RemoteException
	 * @throws NotBoundException
	 */
	public void createSRecord(String firstName, String lastName, Course course, 
								Status status, String statusdate) throws IOException, RemoteException, NotBoundException{
		intrfc = (DCMSInterface)registry.lookup(managerID.substring(0, 3));
		String reply = intrfc.createSRecord(firstName, lastName, course, status, statusdate);
		System.out.println(reply);
		writeToLog(reply);
	}
	
	/**
	 * manager side call Server getRecordCounts through interface
	 * @throws IOException
	 * @throws RemoteException
	 * @throws NotBoundException
	 */
	public void getRecordCounts() throws IOException, RemoteException, NotBoundException{
		intrfc = (DCMSInterface)registry.lookup(managerID.substring(0, 3)); //dynamic bindling clinet to server.
		String reply = intrfc.getRecordCounts();
		System.out.println(reply);
		writeToLog(reply);
	}
	
	/**
	 * manager side call Server EditRecord through interface
	 * @param recordID
	 * @param fieldName
	 * @param newValue
	 * @throws IOException
	 * @throws RemoteException
	 * @throws NotBoundException
	 */
	public void EditRecord(String recordID, String fieldName, String newValue) throws IOException, RemoteException, NotBoundException{
		intrfc = (DCMSInterface)registry.lookup(managerID.substring(0, 3)); //dynamic bindling clinet to server.
		String reply = intrfc.EditRecord(recordID, fieldName, newValue);
		System.out.println(reply);
		writeToLog(reply);
	}

}
