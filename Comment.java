import java.util.Date;
/**
 * Classe fille de message, décrit ce que contient un commentaire
 * @author Haseeb JAVAID, Mathieu JUGI
 *
 */

public class Comment extends Message{
	private int pidCommentaire;
	private int pidMessage;
	
	public Comment(Date dateC,int idC,int idU,String commentaire,String user,int score,int pidCommentaire, int pidMessage) {
		super(dateC,idC,idU,commentaire,user,score);
		this.pidCommentaire=pidCommentaire;
		this.pidMessage=pidMessage;
	}
	
	/**
	 * 
	 * @return Retourne le pid du commentaire ciblé par le commentaire
	 */
	
	public int getPidCommentaire() {
		return this.pidCommentaire;
	}
	
	/**
	 * 
	 * @return Retourne le pid du message ciblé par le commentaire
	 */
	
	public int getPidMessage() {
		return this.pidMessage;
	}
	
	/**
	 * Fonction qui gère l'affichage d'un commentaire
	 */
	
	public String toString() {
		return "Commentaire de "+super.getUser()+" du "+super.getDate()+" : "+super.getMessage();
	}
}
