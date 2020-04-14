import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.util.Timer;
import java.util.TimerTask;
import java.util.Date;

public class BestMessages implements Runnable{
	
	public int[] valueOfImportance(ArrayList<Message> msgs,ArrayList<Comment> comsDeC,ArrayList<Comment> coms) {
		int arrayLenght=msgs.size();
		int[] valueOfImportance;
		if(!msgs.isEmpty()) {
			valueOfImportance=new int[arrayLenght];
			for(int i=0;i<msgs.size();i++) {
				for(int j=0;j<coms.size();j++) {
					//on ajoute la valeur du score du message
					valueOfImportance[i]=msgs.get(i).getScore();
					if(msgs.get(i).getIdMessage()==coms.get(j).getPidMessage()) {
						//on ajoute la valeur du score du commentaire associé au message
						valueOfImportance[i]=valueOfImportance[i]+coms.get(j).getScore();
						for(int k=0;k<comsDeC.size();k++) {
							if(coms.get(j).getIdMessage()==comsDeC.get(k).getPidCommentaire()) {
								//on ajoute la valeur du commentaire associé à ce commentaire
								valueOfImportance[i]=valueOfImportance[i]+comsDeC.get(k).getScore();
							}
						}
					}
				}
			}
		}
		else {
			valueOfImportance=new int[1];
			Arrays.fill(valueOfImportance, 0);
		}
		return valueOfImportance;
	}
	
	public void run() {
		ArrayList<Message> messagesLus=new ArrayList<Message>();//messages lus par le thread
		ArrayList<Comment> commentairesLus=new ArrayList<Comment>();//commentaires lues par le threads
		ArrayList<Comment> commentairesDeComLus=new ArrayList<Comment>();
		try {
            File f = new File("src/reseauSocial");

            BufferedReader b = new BufferedReader(new FileReader(f));

            String readLine = "";

            System.out.println("Reading file using Buffered Reader");
            while ((readLine = b.readLine()) != null) {
            	//on lit toutes les n secondes
            	int n=(int)(Math.random() * ((3000 - 1000) + 1)) + 1000;
            	Thread.sleep(n);
                String ligne=readLine;
                //On ranges les élements dans un tableau
                String[] tokens = ligne.split("\\|");
            	//date du message/commentaire
            	Date currentDate = new Date();
            	if(ligne.endsWith("||")) {//si c'est un message
                    //on créer linstance du message et on l'ajoute a notre arraylist avec la date
                    Message msg=new Message(currentDate,Integer.parseInt(tokens[0]),Integer.parseInt(tokens[1]),tokens[2],tokens[3],20);
                    messagesLus.add(msg);
                    
                }
                else if(ligne.endsWith("|")){//sinon un commentaire qui répond à un commentaire
                    Comment coms=new Comment(currentDate,Integer.parseInt(tokens[0]),Integer.parseInt(tokens[1]),tokens[2],tokens[3],20,Integer.parseInt(tokens[4]),-1);
                    commentairesDeComLus.add(coms);
                }
                else {//sinon un commentaire qui répond à un message
                    Comment coms=new Comment(currentDate,Integer.parseInt(tokens[0]),Integer.parseInt(tokens[1]),tokens[2],tokens[3],20,-1,Integer.parseInt(tokens[5]));
                    commentairesLus.add(coms);
                }
            	
            	//timer qui se reset toutes les 30 secondes
            	Timer timer = new Timer();
            	TimerTask diminueScore = new TimerTask() {
            	    public void run() {
            	        //on enlève -1 aux scores des 3 types de messages
            	    	for(int i=0;i<messagesLus.size();i++) {
            	    		if(messagesLus.get(i).getScore()>0) {
            	    			int score=messagesLus.get(i).getScore()-1;
            	    			messagesLus.get(i).setScore(score);
            	    			System.out.println(messagesLus.get(0).getScore());
            	    		}
            	    	}
            	    	for(int j=0;j<commentairesDeComLus.size();j++) {
            	    		if(commentairesDeComLus.get(j).getScore()>0) {
            	    			int score=commentairesDeComLus.get(j).getScore()-1;
            	    			commentairesDeComLus.get(j).setScore(score);
            	    		}
            	    	}
            	    	for(int k=0;k<commentairesLus.size();k++) {
            	    		if(commentairesLus.get(k).getScore()>0) {
            	    			int score=commentairesLus.get(k).getScore()-1;
            	    			commentairesLus.get(k).setScore(score);
            	    		}
            	    	}
            	    }
            	};
            	
            	timer.schedule(diminueScore, 5000, 5000);
            	//int[] resultat=valueOfImportance(messagesLus,commentairesDeComLus,commentairesLus);
            	//System.out.println(resultat[0]);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
		catch (InterruptedException e) {
		      e.printStackTrace();
		    }
	}
}
