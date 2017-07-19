package Tutorial;

import org.omg.CORBA.ORB;

import HelloApp.HelloPOA;

public class HelloImpl extends HelloPOA{
	private ORB orb;
	public void setORB(ORB orb_val){
		orb = orb_val;
	}
	
	public String sayHello(){
		return "\n hello world";
	}
	
	public void shutdown(){
		orb.shutdown(false);
	}
}
