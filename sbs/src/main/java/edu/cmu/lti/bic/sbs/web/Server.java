package edu.cmu.lti.bic.sbs.web;
import static spark.Spark.*;
public class Server {
	public static void start() {
		port(8080);
		staticFileLocation("/public");
		get("/hello", (req, res) -> "Hello World!");
	}
}
