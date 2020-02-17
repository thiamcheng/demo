import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.jpos.iso.ISOException;
import org.jpos.iso.ISOMsg;
import org.jpos.iso.packager.GenericPackager;


public class TestISO {

	public static void main(String[] args) throws IOException, ISOException {
		// TODO Auto-generated method stub
		// Create Packager based on XML that contain DE type
		GenericPackager packager = new GenericPackager("src/main/resources/basic.xml");

		Logger logger = Logger.getLogger("log");
		logger.setLevel(Level.INFO);
		
		// Create ISO Message
		ISOMsg isoMsg = new ISOMsg();
		isoMsg.setPackager(packager);
		isoMsg.setMTI("0200");
		isoMsg.set(3, "201234");
		isoMsg.set(4, "10000");
		isoMsg.set(7, "110722180");
		isoMsg.set(11, "123456");
		isoMsg.set(44, "A5DFGR");
		isoMsg.set(105, "VETEALDEMONIOISO8583 1234567890");

		// print the DE list
		// logger.log(isoMsg);

		// Get and print the output result
		byte[] data = isoMsg.pack();
		//System.out.println("RESULT : " + new String(data));
		logger.info("RESULT : " + new String(data));
		return;
	}

}
