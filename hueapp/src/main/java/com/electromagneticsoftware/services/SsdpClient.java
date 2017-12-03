package com.electromagneticsoftware.services;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketTimeoutException;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SsdpClient {
	private static final Logger LOGGER = LoggerFactory.getLogger(SsdpClient.class);

	public static Set<String> discover(int timeout) throws IOException  {
		Set<String> bridges = new HashSet<String>();
		byte[] sendData;
		byte[] receiveData = new byte[1024];
		
		// build the request
		StringBuilder msearch = new StringBuilder();
		msearch.append("M-SEARCH * HTTP/1.1\nHost: 239.255.255.250:1900\nMan: \"ssdp:discover\"\n");
		msearch.append("ST: ssdp:all\n");
		
		// send the request
		sendData = msearch.toString().getBytes();
		DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, InetAddress.getByName("239.255.255.250"), 1900);
		DatagramSocket clientSocket = new DatagramSocket();
		clientSocket.setSoTimeout(timeout);
		clientSocket.send(sendPacket);
		
		// receive all responses
		while(true) {
			try {
				DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
				clientSocket.receive(receivePacket);
				String ip = parse(receivePacket);
				if (null != ip) {
					bridges.add(ip);
				}
			}
            catch (SocketTimeoutException e) { break; }
		}
		clientSocket.close();
		return Collections.unmodifiableSet(bridges);
	}
	
	private static String parse(DatagramPacket ssdpResult) {
		Map<String,String> headers = new HashMap<String, String>();
		
		String result = new String(ssdpResult.getData());
		LOGGER.info("Result: " + result);
		String[] headerStrs = StringUtils.split(result, "\r\n");
		for (String header : headerStrs) {
			String[] parts = StringUtils.splitByWholeSeparator(header, ": ");
			if (parts.length == 2 ) {
				headers.put(parts[0], parts[1]);
			}
		}
		String server = headers.get("SERVER");
		String ip = null;
		if (StringUtils.contains(server, "IpBridge")) {
			ip = ssdpResult.getAddress().getHostAddress();
		}
		return ip;
	}

}
