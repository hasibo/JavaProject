import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;
import java.util.Timer;
import java.util.TimerTask;
import java.util.Date;

/**
 * Implémente les traitements que doit éxécuter le thread qui va lire et chercher les 3 meilleurs messages
 * @author Haseeb JAVAID, Mathieu JUGI
 *
 */

public class BestMessages extends Thread{
	
	private ArrayList<Message> resultatBest3messages;
	
	/**
	 * 
	 * @return retourne le resultat contenant les 3 meilleurs messages
	 */
	
	public ArrayList<Message> getBest3messages(){
		return this.resultatBest3messages;
	}
	
	/**
	 * 
	 * @param msgs ArrayList des messages lus par le thread
	 * @param comsDeC ArrayList des commentaires qui commentent d'autres commentaires
	 * @param coms ArrayList des commentaires qui commentent un message
	 * @return Retourne un tableau contenant les values of importance des messages lus par le thread ou un tableau contenant un zéro si msgs est vide
	 */
	
	public int[] valueOfImportance(ArrayList<Message> msgs,ArrayList<Comment> comsDeC,ArrayList<Comment> coms) {
		int arrayLenght=msgs.size();
		//initialisation du tableau va contenir les values of importance
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
		//cas ou msgs est vide
		else {
			valueOfImportance=new int[1];
			Arrays.fill(valueOfImportance, 0);
		}
		return valueOfImportance;
	}
	
	/**
	 * 
	 * @param msg ArrayList des messages lus par le thread
	 * @param vOI Tableau contenant les values of importances de ces messages
	 * @return Retourne dans une liste les 3 messages qui ont la plus grande value of importance, si msg contient moins de 3 messages, renvoie une liste vide
	 */
	
	public ArrayList<Message> BestOfMessage(ArrayList<Message> msg,int[] vOI){
		ArrayList<Message> bestMessage=new ArrayList<Message>();
		//Liste qui va contenir les values of importance
		ArrayList<Integer> vOImportance=new ArrayList<Integer>();
		//on le crée pour éviter de modifier msg
		ArrayList<Message> msgTemp=new ArrayList<Message>();
		// on met les valeurs de vOI dans vOImportance
		for(int i=0;i<vOI.length;i++) {
			vOImportance.add(vOI[i]);
			msgTemp.add(msg.get(i));
		}
		if(msg.size()>=3) {
			while(bestMessage.size()<3) {
				int max=Collections.max(vOImportance);
				int indexMax=vOImportance.indexOf(max);
				bestMessage.add(msgTemp.get(indexMax));
				//on enlève la valeur trouvé pour ne pas compter le même message deux fois
				msgTemp.remove(indexMax);
				vOImportance.remove(indexMax);
			}
		}
		return bestMessage;
	}
	
	/**
	 * Méthode run qui implémente les traitements à exécuter par le thread 
	 */
	
	public void run() {
		ArrayList<Message> messagesLus=new ArrayList<Message>();//messages lus par le thread
		ArrayList<Comment> commentairesLus=new ArrayList<Comment>();//commentaires lues par le threads
		ArrayList<Comment> commentairesDeComLus=new ArrayList<Comment>();
		//timer pour enlever -1 aux scores des messages et commentaires
    	Timer timer = new Timer();
    	TimerTask diminueScore = new TimerTask() {
    		/**
    		 * Méthode run qui implémente les traitements à exécuter par le timer. 
    		 */
    		
    	    public void run() {
    	        //on enlève -1 aux scores des 3 types de messages
    	    	for(int i=0;i<messagesLus.size();i++) {
    	    		if(messagesLus.get(i).getScore()>0) {
    	    			int score=messagesLus.get(i).getScore()-1;
    	    			messagesLus.get(i).setScore(score);
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
    	//se répète tous les 30 secondes
    	timer.schedule(diminueScore,30000,30000);
    	
		try {
            File f = new File("src/reseauSocial");

            BufferedReader b = new BufferedReader(new FileReader(f));

            String readLine = "";

            System.out.println("Reading file using Buffered Reader");
            while ((readLine = b.readLine()) != null) {
            	//on lit reseauSocial toutes les n secondes
            	int n=(int)(Math.random() * ((3000 - 1000) + 1)) + 1000;
            	Thread.sleep(n);
                String ligne=readLine;
                //On ranges les champs du message dans tokens
                String[] tokens = ligne.split("\\|");
            	//date du message/commentaire
            	Date currentDate = new Date();
            	if(ligne.endsWith("||")) {//si c'est un message
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
            	//contient les values of importance des messages
            	int[] vOI=valueOfImportance(messagesLus,commentairesDeComLus,commentairesLus);
            	//contient les 3 meilleurs messages
            	ArrayList<Message> BestOf3=BestOfMessage(messagesLus,vOI);
            	this.resultatBest3messages=BestOf3;
            }
          //Boucle pour continuer à chercher les 3 meilleurs messages même quand on a lu tout le fichier
          while(true) {
            	int[] vOI=valueOfImportance(messagesLus,commentairesDeComLus,commentairesLus);
            	ArrayList<Message> BestOf3=BestOfMessage(messagesLus,vOI);
            	this.resultatBest3messages=BestOf3;
            }
            

        } catch (IOException e) {
            e.printStackTrace();
        }
		catch (InterruptedException e) {
		      e.printStackTrace();
		    }
	}
}
