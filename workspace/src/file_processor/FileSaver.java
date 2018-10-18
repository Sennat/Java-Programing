/* ************************************************************************************
 * Copyright (C) Oct 18, 2018, Natnael Alemayehu, All rights Reserved. Unauthorized 
 * copying of this file and/or distributed without, the express permission of 
 * Natnael Alemayehu is strictly prohibited. Written by Natnael Alemayehu 5:42:04 AM.
 * ************************************************************************************
 */

package file_processor;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class FileSaver implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private FileOutputStream file_output_stream;
	private ObjectOutputStream object_output_stream;

	/**
	* A method save a file onto a disk it takes a list of byte array as an argument
	*/
	public void saveFile(byte[] bytes) {
		try {
		 file_output_stream = new FileOutputStream(new File("output.dat"));
		 object_output_stream = new ObjectOutputStream(file_output_stream);
		 object_output_stream.writeObject(bytes);
			
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			try {
				file_output_stream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
