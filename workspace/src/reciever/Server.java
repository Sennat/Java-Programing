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
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Server {

	public final static Logger errors = Logger.getLogger("errors");
	private static final int SEQUENCE_NUMBER = 0;
	private final static int BUFFER_SIZE = 65535;
	private final int PORT = 423;
	private byte[] data_in = null;
	private byte[] data_out = null;

	public byte[] receivedData() {

		// Display server status
		System.out.println("+ ============================================ +");
		System.out.println("\t\tServer Started");
		System.out.println("+ ============================================ +");

		// Display server status
		System.out.println("The server is ready about to start receiving pakets ....");

		
		// Connection
		try (DatagramSocket server_socket = new DatagramSocket(PORT)) {

			// Create buffers to process data
			data_out = new byte[BUFFER_SIZE];
			data_in = new byte[BUFFER_SIZE];
			int squence_num = SEQUENCE_NUMBER;

			while (true) {
				squence_num++;

				// Receive packet
				DatagramPacket receive_packet = new DatagramPacket(data_in, data_in.length);
				server_socket.receive(receive_packet);
				InetAddress host_ip = receive_packet.getAddress();
				System.out.println("\nServer connected to ...... " + host_ip + "\n");
				int port = receive_packet.getPort();

				long timestamp = System.currentTimeMillis();

				Random random = new Random();
				int probablity = random.nextInt(50);

				// Retrieve packet info to send response to same sender
				String message = new String(receive_packet.getData(), 0, receive_packet.getLength());
				message = message.toUpperCase();
				data_out = message.getBytes();
				System.out.println("PACKET RECEIVED: \n" + message);
				//data_out = message.getBytes();
				
				// 50% chance of responding to the message
				if (((probablity % 2) == 0)) {

					String acknowledgment = new String("ACKNOWLEDGEMENT RECEIVED FOR: ");
					String result = new String(
							"\nRECEIVED  PACKET SEQUENCE #:\t" + squence_num + "\tTIME STAMP:\t" + timestamp);
					data_out = result.getBytes();
					data_out = acknowledgment.getBytes();
					
					// Send response packet to sender
					DatagramPacket send_packet = new DatagramPacket(data_out, data_out.length, host_ip, port);
					server_socket.send(send_packet);
					break;

				} else {

					String drop_msg = new String("MESSAGE FOR DROPPED PACKETS: ");
					String drop_packets = new String(
							"DROPPED  PACKET SEQUENCE #:\t" + squence_num + "\tTIME STAMP:\t" + timestamp);
					data_out = drop_msg.getBytes();
					data_out = drop_packets.getBytes();
					squence_num--;

				}

				//server_socket.close();

			}

		} catch (IOException | RuntimeException ex) {
			errors.log(Level.SEVERE, ex.getMessage(), ex);
		}

		return data_in;

	}

}
