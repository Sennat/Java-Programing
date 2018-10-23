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
import java.util.logging.Level;
import java.util.logging.Logger;

public class Server {

	public final static Logger errors = Logger.getLogger("errors");
	private static final int SEQUENCE_NUMBER = 0;
	private final static int BUFFER_SIZE = 128;
	private final static int PORT = 423;

	public static void receivedData() {

		// Display server status
		System.out.println("+ ============================================ +");
		System.out.println("\t\t~ Server Started ~");
		System.out.println("+ ============================================ +");

		// Display server status
		System.out.println("The server is ready, waiting for incoming data...");

		// Connection
		try (DatagramSocket socket = new DatagramSocket(PORT)) {
			// Create buffers to incoming data
			byte[] data_in = new byte[BUFFER_SIZE];

			// Receive packet
			DatagramPacket receive_packet = new DatagramPacket(data_in, data_in.length);

			int squence_num = SEQUENCE_NUMBER;

			while (true) {
				socket.receive(receive_packet);

				byte[] data_out = receive_packet.getData();

				String message = new String(data_out, 0, receive_packet.getLength());
				System.out.println("\nRECEIVED PACKET: " + message + "\n");

				// Retrieve packet info and send acknowledgement response to the sender
				String reply = new String("\nDATA ACKNOWLEDGEMENT RECEIVED FOR PACKET SEQUENCE #: SEQ-"
						+ squence_num + "\nRECEIVED DATA: \n" + message + "\n");
				System.out.println("\nDATA ACKNOWLEDGEMENT SENT FOR PACKET SEQUENCE #: SEQ-" + squence_num);
				squence_num++;

				// Send response packet to sender
				DatagramPacket send_packet = new DatagramPacket(reply.getBytes(), reply.getBytes().length,
						receive_packet.getAddress(), receive_packet.getPort());
				socket.send(send_packet);

			}

		} catch (IOException | RuntimeException ex) {
			errors.log(Level.SEVERE, ex.getMessage(), ex);
			System.out.println("unable to open ");
			System.exit(1);
		}

	}

	public static void main(String[] args) {

		receivedData();
	}

}
