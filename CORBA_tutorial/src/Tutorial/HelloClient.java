package Tutorial;

import org.omg.CORBA.ORB;
import org.omg.CosNaming.NamingContextExt;
import org.omg.CosNaming.NamingContextExtHelper;
import org.omg.CosNaming.NamingContextHelper;

import HelloApp.Hello;
import HelloApp.HelloHelper;

public class HelloClient {
	
	static Hello helloImpl;
	
	public static void main(String args[]){
		try{
			ORB orb = ORB.init(args, null);
			
			org.omg.CORBA.Object objRef = orb.resolve_initial_references("NameService");
			
			NamingContextExt ncRef = NamingContextExtHelper.narrow(objRef);
			
			String name = "Hello";
			
			helloImpl = HelloHelper.narrow(ncRef.resolve_str(name));
			
			System.out.println("Obtained a handle on server object "+ helloImpl);
			
			System.out.println(helloImpl.sayHello());
			
			helloImpl.shutdown();
		}
		catch (Exception e){
			System.out.println("ERROR"+e);
			e.printStackTrace(System.out);
		}
	}
}
