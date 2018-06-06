package com.domo.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.springframework.stereotype.Service;

import com.domo.model.Comport;
import com.domo.service.ComportService;
import com.fazecast.jSerialComm.SerialPort;

@Service
public class ComportServiceImpl implements ComportService {

	static SerialPort chosenPort;

	@Override
	public List<Comport> getComPort() {

		List<Comport> list = new ArrayList<>();
		SerialPort[] portNames = SerialPort.getCommPorts();
		for (int i = 0; i < portNames.length; i++) {
			list.add(new Comport(portNames[i].getSystemPortName()));
		}
		return list;
	}

	@Override
	public boolean openComPort(String portNumber) {
		boolean isOpenPort = false;
		chosenPort = SerialPort.getCommPort(portNumber);
		chosenPort.setComPortTimeouts(SerialPort.TIMEOUT_SCANNER, 0, 0);
		isOpenPort = chosenPort.openPort();
		if(chosenPort.openPort()) {		
			// create a new thread that listens for incoming text and populates the graph
			Thread thread = new Thread() {
				@Override
				public void run() {
					Scanner scanner = new Scanner(chosenPort.getInputStream());
					while (scanner.hasNextLine()) {
						try {
							String line = scanner.nextLine();
							int number = Integer.parseInt(line);
							System.out.println(number);
						} catch (Exception e) {
						}
					}
					scanner.close();
				}
			};
			thread.start();
		}
		System.out.println("Port Opened: " + portNumber + " " + isOpenPort);	
		return isOpenPort;
	}

	@Override
	public boolean closeComPort(String portNumber) {
		boolean isClosePort = false;
		isClosePort = chosenPort.closePort();
		System.out.println("Port Closed: " + portNumber + " " + isClosePort);	
		return isClosePort;
	}
}