import java.rmi.*;
import java.util.ArrayList;


/**
 * Implémente les traitements que doit éxécuter le thread qui va lire et chercher les 3 meilleurs messages
 * @author Haseeb JAVAID, Mathieu JUGI
 *
 */

public interface BestMessages extends Remote{
	
	public ArrayList<Message> getBestMessage() throws RemoteException;
	
}
