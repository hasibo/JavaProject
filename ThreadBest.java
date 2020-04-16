import java.util.concurrent.Executors;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;

/**
 * Implémente les traitements que doit éxécuter le thread qui va envoyer les 3 meilleurs messages au client
 * @author Haseeb JAVAID, Mathieu JUGI
 *
 */

public class ThreadBest {
	
 
		private ArrayList<Message> best3;
		
		public void setBest3(ArrayList<Message> b) {
			this.best3.clear();
			this.best3.add(b.get(0));
			this.best3.add(b.get(1));
			this.best3.add(b.get(2));
		}				
			
	
	public static void main(String[] args) {
	      ExecutorService executorService = Executors.newSingleThreadExecutor();

	      executorService.execute(new Runnable() {
	          public void run() {
	            System.out.println("debut tache");
	            try {
	            	System.out.println("yo");
	            	
	            	ThreadBest tb = new ThreadBest();
	            	/*tb.setBest3(threadBestMessages.getBest3messages());*/
	            	
	            	if(tb.best3 != null) {
						if (!tb.best3.isEmpty()) {
						
					System.out.println(tb.best3.get(0).getIdMessage() + " | " + tb.best3.get(0).getIdUser() + " | " + 
							tb.best3.get(1).getIdMessage() + " | " + tb.best3.get(1).getIdUser() + " | " + 
							tb.best3.get(2).getIdMessage() + " | " + tb.best3.get(2).getIdUser());
						}
					}

	            	Thread.sleep(10000);
	            } catch (InterruptedException e) {
	              e.printStackTrace();
	            }
	            System.out.println("fin tache");
	          }
	        });
	     
	        executorService.shutdown();
	     
	        System.out.println("Fin thread principal");
	   }
	
}
