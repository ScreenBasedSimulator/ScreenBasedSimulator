package edu.cmu.lti.bic.sbs;

import edu.cmu.lti.bic.sbs.engine.Engine;
import edu.cmu.lti.bic.sbs.socket.io.ChatLauncher;

public class Main {
  public static void main(String[] args) throws Exception {
    new Engine();
    edu.cmu.lti.bic.sbs.web.Server.start();
    System.out.println("Finished Starting");
  }
}