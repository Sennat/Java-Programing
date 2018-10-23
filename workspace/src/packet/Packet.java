/* ************************************************************************************
 * Copyright (C) Oct 22, 2018, Natnael Alemayehu, All rights Reserved. Unauthorized 
 * copying of this file and/or distributed without, the express permission of 
 * Natnael Alemayehu is strictly prohibited. Written by Natnael Alemayehu 12:19:11 PM.
 * ************************************************************************************
 */

package packet;

import java.net.DatagramPacket;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Packet {

	public boolean isAcknowledge(int probablity) {
		Random random = new Random(100);
		probablity = random.nextInt();

		if (probablity % 2 == 0) {
			return true;
		}

		return false;

	}

	public Map<Integer, byte[]> assignSequenceNum(byte[] bytes, Integer squence_num) {
		HashMap<Integer, byte[]> map = new HashMap<Integer, byte[]>();
		squence_num = 0;
		while (map.size() >= 0) {
			map.put(squence_num, bytes);
			squence_num++;
		}

		return map;

	}

}
