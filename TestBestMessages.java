import java.util.ArrayList;
import java.util.Date;
import java.util.StringTokenizer;
import java.util.Timer;
import java.util.TimerTask;

public class TestBestMessages {
	public static void main(String[] args){
		Thread thread = new Thread(new BestMessages());
	    thread.start();
	}
}
