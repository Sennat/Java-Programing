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
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import file_processor.DataSender;

public class Client {

	private static List<byte[]> bytes_list = new ArrayList<>();
	private final static String HOSTNAME = "127.0.0.1";
	private static final int SEQUENCE_NUMBER = -1;
	private final static int BUFFER_SIZE = 100;
	private final static int PORT = 423;

	public static void sendData() {
		System.out.println("+ ============================================ +");
		System.out.println("\t\tClient Started");
		System.out.println("+ ============================================ +");

		// Create a datagram socket
		try (DatagramSocket client_socket = new DatagramSocket(0)) {
			client_socket.setSoTimeout(23000);

			// Get sender’s address and port number from the datagram
			InetAddress host_ip = InetAddress.getByName(HOSTNAME);

			int packets_sent = 0;
			int bytes_sent = 0;

			// Get an input file
			DataSender data_sender = new DataSender();
			bytes_list = data_sender.loadFile();
			int squence_num = SEQUENCE_NUMBER;				
			boolean loop = true;

			// For debugging purpose
			//bytes_list.forEach(item -> System.out.println(item));

			while (true) {
				// Create a buffer to store the incoming datagrams packets
				for (byte[] data_out : bytes_list) {
						squence_num++;
						try {
							// Create a datagram packet object for incoming datagrams packets
							DatagramPacket send_packet = new DatagramPacket(data_out, data_out.length, host_ip, PORT);
							// Send datagram packets to a receiver server
							client_socket.send(send_packet);

							long timestamp = System.currentTimeMillis();
							packets_sent++;

							// For debugging purpose
							//System.out.println("Total packet: " + send_packet.getLength());

							System.out.println("SENT PACKET#: " + packets_sent + "\tBYTE (" + (bytes_sent + data_out.length - 1) + 
									" - " + (bytes_sent + data_out.length) + ")\tTIME STAMP:\t" + timestamp + "\tSEQUENCE#:\t" + 
									squence_num);

							// If acknowledgment received stop while loop
							//loop = false;

						} catch( SocketTimeoutException exception ){

							// If client don't get an acknowledgment, re-send sequence number
							System.out.println("TIMEDOUT FOR SEQUENCE NUMBER:\t" + squence_num );
							squence_num--;

						}

				}

				byte[] data_in = new byte[BUFFER_SIZE];

				// Create a datagram packet object for incoming datagrams packets
				DatagramPacket recieve_packet = new DatagramPacket(data_in, data_in.length);

				// Receive incoming datagrams packets
				client_socket.receive(recieve_packet);

				String incomming_msg = new String(recieve_packet.getData(), 0, recieve_packet.getLength());

				// Receive acknowledgment message from the Server
				System.out.println("A replay acknowledgment message from the Server: " + incomming_msg);

			}

		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (SocketException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}

	}
	
	public static void main(String[] args) {

		sendData();
	}

}
