/**
 * 
 */
/**
 * @author Lucas Guerrero
 *
 */
package com.domo.service;

import java.util.List;

import com.domo.model.Comport;
import com.fazecast.jSerialComm.SerialPort;

public interface ComportService {

	List<Comport> getComPort();
	boolean openComPort(String portNumber);
	boolean closeComPort(String portNumber);
}