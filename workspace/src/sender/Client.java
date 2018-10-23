/* ************************************************************************************
 * Copyright (C) Oct 18, 2018, Natnael Alemayehu, All rights Reserved. Unauthorized 
 * copying of this file and/or distributed without, the express permission of 
 * Natnael Alemayehu is strictly prohibited. Written by Natnael Alemayehu 3:58:38 AM.
 * ************************************************************************************
 */

package sender;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.HashMap;
import java.util.Map;

import file_processor.DataSender;

public class Client {

	private static Map<Integer, byte[]> bytes_array = new HashMap<Integer, byte[]>();
	private static long timestamp, start_time, end_time = 0;
	private static DatagramPacket send_packet = null;
	private final static String HOSTNAME = "127.0.0.1";
	private static final int SEQUENCE_NUMBER = -1;
	private final static int BUFFER_SIZE = 128;
	private final static int PORT = 423;
	private static int squence_num = 0;

	public static void sendData() {
		System.out.println("+ ======================================================= +");
		System.out.println("\t\tClient Started To Send Data");
		System.out.println("+ ======================================================= +");

		// Create a datagram socket
		try (DatagramSocket socket = new DatagramSocket(0)) {
			socket.setSoTimeout(3000);

			// Get sender’s address and port number from the datagram
			InetAddress host_ip = InetAddress.getByName(HOSTNAME);

			Integer squence_num = SEQUENCE_NUMBER;
			int packets_sent = 0;
			int bytes_sent = 0;

			// Get an input file
			DataSender data_sender = new DataSender();
			// Create a buffer to store the incoming datagrams packets
			byte[] data_out = data_sender.loadFile();
			
			start_time = System.currentTimeMillis();
			boolean loop = false;

			while (!loop) {
				squence_num++;

				// Create a datagram packet object for outgoing datagrams packets
				send_packet = new DatagramPacket(data_out, data_out.length, host_ip, PORT);

				// Send datagram packets to a receiver server
				socket.send(send_packet);
				packets_sent++;

				// timestamp = System.currentTimeMillis();

				System.out.println("\nSENT PACKET #: " + packets_sent + "\tBYTE (" + (bytes_sent + data_out.length - 1)
						+ " - " + (bytes_sent + data_out.length) + ")" + "\tSEQUENCE #: SEQ-" + squence_num);

				byte[] data_in = new byte[BUFFER_SIZE];
				
				// Create a datagram packet object for incoming datagrams packets
				DatagramPacket recieve_packet = new DatagramPacket(data_in, data_in.length);
				
				// Receive incoming datagrams packets
				socket.receive(recieve_packet);

				// Receive acknowledgment message from the Server
				String result = new String(data_in, 0, recieve_packet.getLength());
				System.out.println("\nACKNOWLEDGMENT FROM HOSTNAME: " + recieve_packet.getAddress().getHostAddress() + 
						" PORT #: " + recieve_packet.getPort() + " " + result);

			}

			// For debugging purpose
			System.out.println("Total packet: " + send_packet.getLength());
			end_time = System.currentTimeMillis();
			System.out.println("Total elapsed time: " + ((end_time - start_time) / 1000) % 60 + " Seconds");

		} catch (IOException e) {
			// If client don't get an acknowledgment, re-send sequence number
			System.out.println("TIMEDOUT FOR SEQUENCE NUMBER:\t" + squence_num  );
			squence_num--;
			e.printStackTrace();
		}

	}

	public static void main(String[] args) {

		sendData();
	}

}
