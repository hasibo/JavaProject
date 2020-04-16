import java.util.ArrayList;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class BestMessagesImpl extends UnicastRemoteObject implements BestMessages{
	
	private static final long serialVersionUID = 2674880711467464646L;
	
	private ArrayList<Message> bestMessage;
	
	public BestMessagesImpl(ArrayList<Message> bestMsg) throws RemoteException{
		this.bestMessage=bestMsg;
	}
	
	public ArrayList<Message> getBestMessage() throws RemoteException{
		return this.bestMessage;
	}

}
