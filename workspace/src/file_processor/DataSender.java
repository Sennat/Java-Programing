/* ************************************************************************************
 * Copyright (C) Oct 18, 2018, Natnael Alemayehu, All rights Reserved. Unauthorized 
 * copying of this file and/or distributed without, the express permission of 
 * Natnael Alemayehu is strictly prohibited. Written by Natnael Alemayehu 4:33:15 AM.
 * ************************************************************************************
 */

package file_processor;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.swing.JFileChooser;

public class DataSender implements FileLoader {

	private String file_name;
	private byte[] data_in = new byte[1024];
	private FileInputStream file_input_stream;

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
				data_in = new byte[(int) file.length()];
				file_input_stream.read(data_in);

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
		return data_in;

	}
}
