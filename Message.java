import java.util.Date;
/**
 * Classe décrivant la description d'un message
 * @author Haseeb JAVAID,Mathieu JUGI
 *
 */
public class Message {
	
	private Date dateMessage;
	private int idMessage;
	private int idUser;
	private String message;
	private String user;
	private int score;
	
	public Message(Date dateM,int idM,int idU,String message,String user,int score) {
		this.dateMessage=dateM;
		this.idMessage=idM;
		this.idUser=idU;
		this.message=message;
		this.user=user;
		this.score=score;
	}
	
	/**
	 * 
	 * @return Retourne la date du message
	 */
	public Date getDate() {
		return this.dateMessage;
	}
	
	/**
	 * 
	 * @return Retourne l'identifiant du message
	 */
	
	public int getIdMessage() {
		return this.idMessage;
	}
	
	/**
	 * 
	 * @return Retourne l'identifiant de l'utilisateur qui a rédigé le message
	 */
	
	public int getIdUser() {
		return this.idUser;
	}
	
	/**
	 * 
	 * @return Retourne le contenu du message
	 */
	
	public String getMessage() {
		return this.message;
	}
	
	/**
	 * 
	 * @return Retourne le nom de l'utilisateur
	 */
	
	public String getUser() {
		return this.user;
	}
	
	/**
	 * 
	 * @return Retourne le score du message
	 */
	
	public int getScore() {
		return this.score;
	}
	
	/**
	 * 
	 * @param score
	 * Modifie le score d'un message
	 */
	
	public void setScore(int score) {
		this.score=score;
	}
	
	/**
	 *Fonction qui gère l'affichage d'un message 
	 */
	
	public String toString() {
		return "Message de "+this.user+" du "+this.dateMessage+" : "+this.message;
	}
	
}
