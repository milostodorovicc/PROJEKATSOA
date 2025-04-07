package soa.soa;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SoaApplication {

	public static void main(String[] args) {
		SpringApplication.run(SoaApplication.class, args);
	}


//	@Value("${server.grpc.port}")
//	private int grpcPort;
//
//	@Bean
//	public Server grpcServer() {
//		Server server = null;
//		try {
//			// Create a new gRPC server
//			server = ServerBuilder.forPort(grpcPort)
//					.addService(new MyServiceImpl())
//					.build();
//
//			// Start the server
//			server.start();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return server;
//	}
}