import java.rmi.RemoteException;
import java.util.ArrayList;

public class BestMessagesImpl implements BestMessages{
	
	private ArrayList<Message> bestMessage;
	
	public BestMessagesImpl(ArrayList<Message> bestMsg) {
		this.bestMessage=bestMsg;
	}
	
	public ArrayList<Message> getBestMessage() throws RemoteException{
		return this.bestMessage;
	}

}
