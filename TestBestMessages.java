/**
 * Classe qui créer une instance du thread pour trouver les 3 meilleurs messages
 * @author Haseeb JAVAID,Mathieu JUGI
 *
 */

public class TestBestMessages {
	public static void main(String[] args){
		BestMessages threadBestMessages = new BestMessages();
	    threadBestMessages.start();
	    while(threadBestMessages.isAlive()) {
	    	try {
	    		threadBestMessages.sleep(10);
	    	}catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
	    	System.out.println(threadBestMessages.getBest3messages());
	    }
	}
}
