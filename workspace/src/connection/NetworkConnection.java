/* ************************************************************************************
 * Copyright (C) Oct 11, 2018, Natnael Alemayehu, All rights Reserved. Unauthorized 
 * copying of this file and/or distributed without, the express permission of 
 * Natnael Alemayehu is strictly prohibited. Written by Natnael Alemayehu 1:51:29 AM.
 * ************************************************************************************
 */

package connection;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

/**
 * Course: ICS 460 Networks And Security Project Description: A project uses UDP
 * protocol to send and a receive a binary file. The sender accepts a file as a
 * command line parameter (any binary file on your hard disk), breaks it into
 * smaller chunks. Class Description: A Singleton class to create secure
 * connection Instructor: Professor Demodar Chetty
 * 
 * @author Natnael Alemayehu
 *
 */
public final class NetworkConnection {

	private static NetworkConnection networkConn;
	private String HOSTNAME;
	private Integer PORT;
	private DatagramSocket socket;

	// A private constructor to restrict instantiation outside this class	
	private NetworkConnection() {
		this.HOSTNAME = "127.0.0.1";
		this.PORT = Integer.valueOf("423");
	}

	// A public method for to instancing the port number and host name
	public static NetworkConnection instance() {
		if (networkConn == null)
			networkConn = new NetworkConnection();
		return networkConn;
	}

	public DatagramSocket getConnection() {
		try {

			// create udp socket connection
			socket = new DatagramSocket(0);
			//socket.setSendBufferSize(1024);
			socket.setSoTimeout(1000);

		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return socket;
	}

	/**
	 * @return the hOSTNAME
	 */
	public String getHOSTNAME() {
		return HOSTNAME;
	}

	/**
	 * @param hOSTNAME the hOSTNAME to set
	 */
	public void setHOSTNAME(String hostname) {
		HOSTNAME = hostname;
	}

	/**
	 * @return the pORT
	 */
	public Integer getPORT() {
		return PORT;
	}

	/**
	 * @param pORT the pORT to set
	 */
	public void setPORT(Integer port) {
		PORT = port;
	}

}
