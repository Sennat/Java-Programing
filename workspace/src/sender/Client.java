/* ************************************************************************************
 * Copyright (C) Oct 18, 2018, Natnael Alemayehu, All rights Reserved. Unauthorized 
 * copying of this file and/or distributed without, the express permission of 
 * Natnael Alemayehu is strictly prohibited. Written by Natnael Alemayehu 3:58:38 AM.
 * ************************************************************************************
 */

package sender;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.UnknownHostException;

import connection.NetworkConnection;
import file_processor.DataSender;

abstract class Client {

	private InetAddress ip_address;

	private byte[] sendData() {

		try {
			// get ip destination wanted
			ip_address = InetAddress.getByName(NetworkConnection.instance().getHOSTNAME());

			DataSender data_sender = new DataSender();
			// create buffers to process data
			byte[] data_out = data_sender.loadFile();
			byte[] data_in = new byte[1024];

			// send packet
			DatagramPacket send_packet = new DatagramPacket(data_out, data_out.length, ip_address,
					NetworkConnection.instance().getConnection().getLocalPort());
			try {
				NetworkConnection.instance().getConnection().send(send_packet);

				// receive packet
				DatagramPacket recieve_packet = new DatagramPacket(data_in, data_in.length);
				NetworkConnection.instance().getConnection().receive(recieve_packet);

				System.out.println("Replay from Server: " + recieve_packet.getData());

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}

}
