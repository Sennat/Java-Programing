/* ************************************************************************************
 * Copyright (C) Oct 11, 2018, Natnael Alemayehu, All rights Reserved. Unauthorized 
 * copying of this file and/or distributed without, the express permission of 
 * Natnael Alemayehu is strictly prohibited. Written by Natnael Alemayehu 1:51:29 AM.
 * ************************************************************************************
 */

package connection;


/**
 * Course: 				ICS 460 Networks And Security
 * Project Description: A project uses UDP protocol to send and a receive a binary file.
 * 						The sender accepts a file as a command line parameter (any binary 
 * 						file on your hard disk), breaks it into smaller chunks.
 * Class Description: 	A Singleton class to create  secure connection
 * Instructor: 			Professor Demodar Chetty
 * @author Natnael Alemayehu
 *
 */
public final class NetworkConnection {
	
	private static NetworkConnection networkConn;
	public final String HOSTNAME;
	public final String PORT;
	
	//A private constructor to restrict instantiation outside this class
	private NetworkConnection(){
		this.PORT = "211";
		this.HOSTNAME = "127.0.2.1";
	};

	//A public method for to instancing the port number and host name
	public static NetworkConnection instance() {
		if(networkConn == null)
			networkConn = new NetworkConnection();	
		return networkConn;
	}
	
}
