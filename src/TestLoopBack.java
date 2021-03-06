import java.io.IOException;
import org.jpos.iso.ISOMsg;
import org.jpos.iso.ISOFilter;
import org.jpos.iso.ISOChannel;
import org.jpos.iso.ISOException;
import org.jpos.iso.channel.LoopbackChannel;
import org.jpos.util.LogEvent;

public class TestLoopBack implements ISOFilter {
	public static void main (String[] args) {
		try {
			new TestLoopBack().run();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
  
	public void run () throws ISOException, IOException {
			LoopbackChannel channel = new LoopbackChannel ();
			channel.addIncomingFilter (this);
			ISOMsg request = createRequest();
			request.dump (System.out, "request> ");
			channel.send (request);
			ISOMsg response = channel.receive();
			response.dump (System.out, "response> ");
	}
  
	private ISOMsg createRequest () throws ISOException {
			ISOMsg m = new ISOMsg ("0800");
			m.set (11, "000001");
			m.set (41, "29110001");
			m.set (70, "301");
			return m;
	}
  
	public ISOMsg filter (ISOChannel channel, ISOMsg m, LogEvent evt) {
		try {
			m.setResponseMTI ();
			m.set (39, "00");
		} catch (ISOException e) {
			e.printStackTrace();
		}
		return m;
	}
}


