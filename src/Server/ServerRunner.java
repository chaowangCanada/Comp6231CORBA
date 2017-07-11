package Server;

import java.util.ArrayList;

import Config.PublicParamters.*;

public class ServerRunner {

	public static ArrayList<CenterServer> serverList = new ArrayList<CenterServer>();
	
	
	public static void main(String args[]){
		try{
			
			CenterServer mtl = new CenterServer(Location.MTL);
			CenterServer lvl = new CenterServer(Location.LVL);
			CenterServer ddo = new CenterServer(Location.DDO); 
			
			serverList.add(mtl);
			serverList.add(lvl);
			serverList.add(ddo);
			
			// UDP waiting request thread
			mtl.openUDPListener();
			lvl.openUDPListener();
			ddo.openUDPListener();
			
			// create registry, Corba binding
			mtl.exportServer(args);
			lvl.exportServer(args);
			ddo.exportServer(args);

			System.out.println("Servers are up and running ");

		}
		catch (Exception e){
			e.printStackTrace();
		}
	}

}
