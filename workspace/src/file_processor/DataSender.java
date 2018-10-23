/* ************************************************************************************
 * Copyright (C) Oct 18, 2018, Natnael Alemayehu, All rights Reserved. Unauthorized 
 * copying of this file and/or distributed without, the express permission of 
 * Natnael Alemayehu is strictly prohibited. Written by Natnael Alemayehu 4:33:15 AM.
 * ************************************************************************************
 */

package file_processor;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.swing.JFileChooser;

import packet.Packet;

public class DataSender implements FileLoader {

	private ByteArrayOutputStream bytearray_stream;
	private byte[] buffer = new byte[128];
	private FileInputStream file_input_stream;
	private String file_name;
	private Map<Integer, byte[]> bytes_array;

	@Override
	public byte[] loadFile() {
		JFileChooser file_Chooser = new JFileChooser();
		file_Chooser.setCurrentDirectory(new File("."));

		if (file_Chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
			file_name = file_Chooser.getSelectedFile().getName();

			try {
				// get a file from user
				File file = new File(file_name);
					// read file into bytes[]
					file_input_stream = new FileInputStream(file);
					bytearray_stream = new ByteArrayOutputStream();
					int readNum = 0;
						while ((readNum = file_input_stream.read(buffer)) != -1) {
							bytearray_stream.write(buffer, 0, readNum);
							buffer = bytearray_stream.toByteArray();

/*							// For debugging purpose
							for(Entry<Integer, byte[]> entry : bytes_array.entrySet()) {
								System.out.println("Key = " + entry.getKey() + 
				                        ", Value = " + entry.getValue());
							}*/
						}

			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				if (file_input_stream != null) {
					try {
						file_input_stream.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}

		return buffer;

	}
}
