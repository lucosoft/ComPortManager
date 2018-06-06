package com.domo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.domo.model.Comport;
import com.domo.service.ComportService;

@RestController
public class ComportController {

	@Autowired
	private ComportService comportService;

	@GetMapping("/getComPort")
	@ResponseBody
	public List<Comport> getComPort() {

		List<Comport> lista = this.comportService.getComPort();

		return lista;

	}

	@RequestMapping(method = RequestMethod.GET, value = "/openPort")
	@ResponseBody
	public ResponseEntity<String> openPort(@RequestParam String portNumber) {
		boolean isOpenPort = this.comportService.openComPort(portNumber);
		return new ResponseEntity<String>(portNumber + " Abierto = " + isOpenPort, HttpStatus.OK);

	}

	@RequestMapping(method = RequestMethod.GET, value = "/closePort")
	@ResponseBody
	public ResponseEntity<String> closePort(@RequestParam String portNumber) {
		boolean isclosePort = this.comportService.closeComPort(portNumber);
		return new ResponseEntity<String>(portNumber + " Cerrado = " + isclosePort, HttpStatus.OK);

	}
	
}