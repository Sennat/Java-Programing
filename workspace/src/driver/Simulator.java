/* ************************************************************************************
 * Copyright (C) Oct 11, 2018, Natnael Alemayehu, All rights Reserved. Unauthorized 
 * copying of this file and/or distributed without, the express permission of 
 * Natnael Alemayehu is strictly prohibited. Written by Natnael Alemayehu 1:40:22 AM.
 * ************************************************************************************
 */

package driver;

import gui.ClientWindow;
import javafx.scene.control.ProgressBar;

public class Simulator {

	private static Thread serverStartThread;
	private static String[] args;
	private static double number;
	private static ProgressBar progressbar;

	public static void main(String[] args) {

		(new Thread() {

			@Override
			public void run() {

				while (true) {
					
					ClientWindow.main(args);

					try {

						Thread.sleep(2000);

					} catch (InterruptedException e) {

						e.printStackTrace();
					}
				}

			}

		}).start();

	}

	public static ProgressBar updateStatusBar() {
			progressbar = new ProgressBar();
		(new Thread() {

			@Override
			public void run() {

				while (number < 1000) {
					progressbar.setProgress(number);
					number+= 0.05;
					
					try {
						Thread.sleep(3000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				}
			}
		}).start();
		
		return progressbar;

	}

}
