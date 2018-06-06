package com.domo;

import java.util.Scanner;
import com.fazecast.jSerialComm.SerialPort;

public class SensorGraph {

	static SerialPort chosenPort;

	public static void findComPortImpl(String comPort) {
		
		chosenPort = SerialPort.getCommPort(comPort);
		chosenPort.setComPortTimeouts(SerialPort.TIMEOUT_SCANNER, 0, 0);
		boolean isOpenPort = chosenPort.openPort();
		if(chosenPort.openPort()) {
			System.out.println("Port Conected:"+ comPort);	
			
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
		System.out.println("findComPortImpl:"+ comPort);		
	}
	
	public static void funcionSensorGraph(String comPort) {
		System.out.println("Comport:"+ comPort);
		System.out.println("funcionSensorGraph");
		SerialPort[] portNames;
		try {
			portNames = SerialPort.getCommPorts();
			for (int i = 0; i < portNames.length; i++)
				System.out.println(portNames[i].getSystemPortName());
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		if (true) {
			// attempt to connect to the serial port
			chosenPort = SerialPort.getCommPort("1");
			chosenPort.setComPortTimeouts(SerialPort.TIMEOUT_SCANNER, 0, 0);
			if (chosenPort.openPort()) {
				System.out.println("openedPort");
			}

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
		} else {
			// disconnect from the serial port
			chosenPort.closePort();
		}
	}

	public static void getCommPorts() {

		System.out.println("init getCommPorts");
		SerialPort[] portNames = SerialPort.getCommPorts();
		for(int i = 0; i < portNames.length; i++)
			System.out.println(portNames[i].getSystemPortName());

		System.out.println("end getCommPorts");
		
	}
}

