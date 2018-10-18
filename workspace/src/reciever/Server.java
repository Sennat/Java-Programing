/* ************************************************************************************
 * Copyright (C) Oct 18, 2018, Natnael Alemayehu, All rights Reserved. Unauthorized 
 * copying of this file and/or distributed without, the express permission of 
 * Natnael Alemayehu is strictly prohibited. Written by Natnael Alemayehu 5:30:52 AM.
 * ************************************************************************************
 */

package reciever;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

abstract class Server {

	private DatagramPacket send_packet, receive_packet;
	private DatagramSocket socket;
	private InetAddress ip_address;
	private int port;

	private byte[] sendData() {

		// create buffers to process data
		byte[] data_out = new byte[1024];
		byte[] data_in = new byte[1024];

		try {
			// receive packet
			receive_packet = new DatagramPacket(data_in, data_in.length);

			// connection
			socket = new DatagramSocket();
			socket.receive(receive_packet);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// display receive
		System.out.println("Packet Received!");

		// retrieve packet info to send response to same sender
		ip_address = receive_packet.getAddress();
		port = receive_packet.getPort();

		// process data
		String temp = new String(receive_packet.getData());
		temp = temp.toUpperCase();
		data_out = temp.getBytes();

		try {
			// send response packet to sender
			send_packet = new DatagramPacket(data_out, data_out.length, ip_address, port);
			socket.send(send_packet);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;

	}

}
