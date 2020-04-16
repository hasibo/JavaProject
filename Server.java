import java.util.ArrayList;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

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
	    		//On instancie l'objet à envoyer au client et on génère un stub sur le port 1000
		    	BestMessages msgBestSkeleton=(BestMessages) UnicastRemoteObject.exportObject(new BestMessagesImpl(threadBestMessages.getBest3messages()),1000);
		    	Registry registry = LocateRegistry.createRegistry(10000);
		    	registry.rebind("bestMessage", msgBestSkeleton); // publie notre instance sous le nom bestMessage
		    	
	    	}catch (Exception e) {
	            e.printStackTrace();
	        }
	    }
	}
}
