import java.util.Date;

public class Comment extends Message{
	private int pidCommentaire;
	private int pidMessage;
	
	public Comment(Date dateC,int idC,int idU,String commentaire,String user,int score,int pidCommentaire, int pidMessage) {
		super(dateC,idC,idU,commentaire,user,score);
		this.pidCommentaire=pidCommentaire;
		this.pidMessage=pidMessage;
	}
	
	public String toString() {
		return "Commentaire de "+super.getUser()+" du "+super.getDate()+" : "+super.getMessage();
	}
}
