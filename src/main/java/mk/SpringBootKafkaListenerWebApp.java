package mk;

import java.util.Map;
import java.util.UUID;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

//@EnableDiscoveryClient
@SpringBootApplication
public class SpringBootKafkaListenerWebApp {
	
	
	

	public static void main(String[] args) {

		System.out.println("Version 02.02.2019");
		System.out.println("Environment variables");
		
//		UUID uniqueKey = UUID.randomUUID();
//		String span=UUID.randomUUID().toString();
		
//		String spanid= (UUID.randomUUID().toString()).replace("-","").substring(0, 16);
		
//	    System.out.println ("uniqueKey:"+ span);		


		Map<String, String> env = System.getenv();
		for (String envName : env.keySet()) {
			System.out.format("%s=%s%n", envName, env.get(envName));
		}

		SpringApplication.run(SpringBootKafkaListenerWebApp.class, args);
	}

}
