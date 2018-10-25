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

import javax.swing.JFileChooser;

public class DataSender implements FileLoader {

	private static ByteArrayOutputStream bytearray_stream;
	private static FileInputStream file_input_stream;
	private static byte[] buffer= null;
	private static String file_name;

	@Override
	public byte[] loadFile() {
		
		JFileChooser file_Chooser = new JFileChooser();
		file_Chooser.setDialogTitle("Open File From Current Directory");
		file_Chooser.setCurrentDirectory(new File("."));

		if (file_Chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
			file_name = file_Chooser.getSelectedFile().getName();

			try {

				// Direct file reader with out JFileChooser
				//File file = new File("C:\\Users\\Natnael\\Programing\\Java Programing\\workspace\\portrait.jpg");

				// get a file from user
				File file = new File(file_name);

				// Get the size of the file
				long length = file.length();

				if (length > Integer.MAX_VALUE) {

					// File is too large
					throw new IOException("File is too large!");
				}

				// To read it in memory to avoid unnecessary copying
				buffer = new byte[(int) file.length()];

				// Read file into bytes[]
				file_input_stream = new FileInputStream(file);
				bytearray_stream = new ByteArrayOutputStream();

				int read = 0;
				while ((read = file_input_stream.read(buffer)) != -1) {
					bytearray_stream.write(buffer, 0, read);
				}

				buffer = bytearray_stream.toByteArray();

			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (Exception e) {
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

		} else {

			file_Chooser.setEnabled(false);
		}

		return buffer;		
	}

}
