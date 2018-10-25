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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import connection.NetworkConnection;
import file_processor.DataSender;
import gui.ClientWindow;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ListView;

public class Client {

	private ObservableList<String> client_log_list = null;
	private ObservableList<String> server_log_list = null;
	private long timestamp, start_time, end_time = 0;
	private ListView<String> client_log_listView;
	private DatagramPacket send_packet = null;
	private final int SEQUENCE_NUMBER = -1;
	private final int BUFFER_SIZE = 1024;
	private final static int PORT = 423;
	private final static String HOSTNAME = "127.0.0.1";
	private ClientWindow client_Window;
	private int squence_num = 0;

	public void sendData() {
		System.out.println("+ ======================================================= +");
		System.out.println("\t\tClient Started To Send Data");
		System.out.println("+ ======================================================= +");

		// Create a datagram socket
		try (DatagramSocket socket = new DatagramSocket(0)) {
			socket.setSoTimeout(3000);

			// Get sender’s address and port number from the datagram
			InetAddress host_ip = InetAddress.getByName(HOSTNAME);

			start_time = System.currentTimeMillis();
			Integer squence_num = SEQUENCE_NUMBER;
			int packets_sent = 0;
			int bytes_sent = 0;

			// Get an input file
			DataSender data_sender = new DataSender();
			List<byte[]> byteList = byteArrayToChunks(data_sender.loadFile());
			byteList.forEach(items -> System.out.println(items));

			// Create a buffer to store the incoming datagrams packets
			for (byte[] data_out : byteList) {

				squence_num++;

				// Create a datagram packet object for outgoing datagrams packets
				send_packet = new DatagramPacket(data_out, data_out.length, host_ip, PORT);

				// Send datagram packets to a receiver server
				socket.send(send_packet);
				packets_sent++;

				// Receive acknowledgment message from the Server
				String result1 = new String(data_out, 0, send_packet.getLength());

				String sent_log = new String("\nSENT PACKET #: " + packets_sent + "\tBYTE (" + bytes_sent + " - "
						+ (bytes_sent + data_out.length) + ")" + "\tSEQUENCE #: SEQ-" + squence_num);
				System.out.println("\nSENT PACKET Debuger:-\n" + result1);
				System.out.println("\nSENT PACKET Debug:- " + sent_log);
				client_log_list = FXCollections.<String>observableArrayList();
				client_log_list.add(sent_log);

				byte[] data_in = new byte[BUFFER_SIZE];

				// Create a datagram packet object for incoming datagrams packets
				DatagramPacket recieve_packet = new DatagramPacket(data_in, data_in.length);

				// Receive incoming datagrams packets
				socket.receive(recieve_packet);

				// Receive acknowledgment message from the Server
				String result2 = new String(data_in, 0, recieve_packet.getLength());
				String recieved_log = new String(
						"\nACKNOWLEDGMENT FROM HOSTNAME: " + recieve_packet.getAddress().getHostAddress() + " PORT #: "
								+ recieve_packet.getPort() + " " + result2);
				System.out.println("\nSENT PACKET Debug: " + recieved_log);
				// server_log_list = FXCollections.<String>observableArrayList();
				// server_log_list.add(recieved_log);
			}

			// For debugging purpose
			System.out.println("Total packet: " + send_packet.getLength());
			end_time = System.currentTimeMillis();
			System.out.println("Total elapsed time: " + ((end_time - start_time) / 1000) % 60 + " Seconds");

		} catch (IOException e) {
			// If client don't get an acknowledgment, re-send sequence number
			System.out.println("TIMEDOUT FOR SEQUENCE NUMBER:\t" + squence_num);
			squence_num--;
			e.printStackTrace();
		}

	}

	/**
	 * Split byte[] to smaller chunks
	 */
	private List<byte[]> byteArrayToChunks(byte[] bytes) {

		List<byte[]> byteList = new ArrayList<byte[]>();
		final int PACKET_SIZE = 1024;

		for (int i = 0; i < bytes.length; i += PACKET_SIZE) {
			byte[] chunk_bytes = Arrays.copyOfRange(bytes, i, i + PACKET_SIZE);

			byteList.add(chunk_bytes);
		}

		return byteList;
	}

	public ObservableList<String> appendfListView(ListView<String> listView) {
		client_log_list = FXCollections.<String>observableArrayList();
		listView = new ListView<String>();
		listView.setItems(client_log_list);
		return client_log_list;
	}
	
}
