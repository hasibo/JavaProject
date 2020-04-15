/**
 * Classe qui créer une instance du thread pour trouver les 3 meilleurs messages
 * @author Haseeb JAVAID,Mathieu JUGI
 *
 */

public class TestBestMessages {
	public static void main(String[] args){
		Thread threadBestMessages = new Thread(new BestMessages());
	    threadBestMessages.start();
	}
}
