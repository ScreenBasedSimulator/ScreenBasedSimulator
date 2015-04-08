package edu.cmu.lti.bic.sbs.web;
import static spark.Spark.*;
public class Server {
	public static void start() {
		port(8080);
		staticFileLocation("/public");
		get("/hello", (request, response) -> {
			return "Hello: there";
		});
		post("/pickup", (request, response) -> {
			System.out.println(request.queryParams());
			System.out.println("Someone picked up a :" + request.queryParams("name"));
			return "You picked up a " + request.queryParams("name");
		});
	}
}
