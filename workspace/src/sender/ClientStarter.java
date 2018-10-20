/* ************************************************************************************
 * Copyright (C) Oct 19, 2018, Natnael Alemayehu, All rights Reserved. Unauthorized 
 * copying of this file and/or distributed without, the express permission of 
 * Natnael Alemayehu is strictly prohibited. Written by Natnael Alemayehu 4:42:23 PM.
 * ************************************************************************************
 */

package sender;

import file_processor.DataSender;
import file_processor.FileLoader;

public class ClientStarter {

	public static void main(String[] args) {
		FileLoader loader = new DataSender();
		Client client =  new Client();
		client.sendData();
	}

}
