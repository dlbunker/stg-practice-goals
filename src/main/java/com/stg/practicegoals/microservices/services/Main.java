package com.stg.practicegoals.microservices.services;

import org.springframework.boot.SpringApplication;

import com.stg.practicegoals.microservices.services.practices.PracticeServer;
import com.stg.practicegoals.microservices.services.registration.RegistrationServer;
import com.stg.practicegoals.microservices.services.training.TrainingServer;

/**
 * Allow the servers to be invoke from the command-line. The jar is built with
 * this as the <code>Main-Class</code> in the jar's <code>MANIFEST.MF</code>.
 * 
 * @author Paul Chapman
 */
public class Main {

	public static void main(String[] args) {

		String serverName = "NO-VALUE";

		switch (args.length) {
		case 2:
			// Optionally set the HTTP port to listen on, overrides
			// value in the <server-name>-server.yml file
			System.setProperty("server.port", args[1]);
			// Fall through into ..

		case 1:
			serverName = args[0].toLowerCase();
			break;

		default:
			usage();
			return;
		}

		// Tell server to look for <server-name>-server.properties or
		// <server-name>-server.yml (this app. uses YAML)
		System.setProperty("spring.config.name", serverName + "-server");

		if (serverName.equals("registration")) {
			SpringApplication.run(RegistrationServer.class, args);
		} else if (serverName.equals("practice")) {
			SpringApplication.run(PracticeServer.class, args);
		} else if (serverName.equals("training")) {
			SpringApplication.run(TrainingServer.class, args);
		} else {
			System.out.println("Unknown server type: " + serverName);
			usage();
		}
	}

	protected static void usage() {
		System.out.println("Usage: java -jar ... <server-name> [server-port]");
		System.out.println("     where server-name is 'registration', "
				+ "'practice'  and server-port > 1024");
	}
}
