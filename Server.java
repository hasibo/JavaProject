import java.util.ArrayList;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.UnknownHostException;
import java.rmi.Naming;
import java.rmi.RMISecurityManager;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

public class Server {
	public static void main(String[] args) {
		ThreadBestMessages threadBestMessages = new ThreadBestMessages();
		//On lance le thread
		threadBestMessages.start();
		while(threadBestMessages.isAlive()) {
	    	try {
	    		threadBestMessages.sleep(10);
	    	}catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
	    	try {
	    		LocateRegistry.createRegistry(1099);
	    		BestMessagesImpl bestMessagesImpl = new BestMessagesImpl(threadBestMessages.getBest3messages());
	    		String url = "rmi://" + InetAddress.getLocalHost().getHostAddress() + "/bestMessage";
	    		System.out.println("Enregistrement de l'objet avec l'url : " + url);
	    		Naming.rebind(url, bestMessagesImpl);
	    		System.out.println("Serveur lancé");
		    	
	    	}catch (RemoteException e) {
	    	    e.printStackTrace();
	    	} catch (MalformedURLException e) {
	    	    e.printStackTrace();
	    	} catch (UnknownHostException e) {
	    	    e.printStackTrace();
	    	}
	    }
	}
}
