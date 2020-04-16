import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;

public class Client {
	 public static void main(String[] argv) {
	        try {
	            Registry registry = LocateRegistry.getRegistry(10000);
	            BestMessages msgBestStub = (BestMessages) registry.lookup("bestMessage");
	            ArrayList<Message> msgBestMessage=msgBestStub.getBestMessage();
	            System.out.println(msgBestMessage); // Affiche les 3 messages
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }
}
