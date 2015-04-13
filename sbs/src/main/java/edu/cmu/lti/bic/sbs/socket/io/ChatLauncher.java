package edu.cmu.lti.bic.sbs.socket.io;

import com.corundumstudio.socketio.listener.*;
import com.corundumstudio.socketio.*;

public class ChatLauncher {
		public static SocketIOServer server = null;
    public static void start() throws InterruptedException {

        Configuration config = new Configuration();
        config.setHostname("localhost");
        config.setPort(9092);

        server = new SocketIOServer(config);
        server.addEventListener("chatevent", ChatObject.class, new DataListener<ChatObject>() {
            @Override
            public void onData(SocketIOClient client, ChatObject data, AckRequest ackRequest) {
                // broadcast messages to all clients
                server.getBroadcastOperations().sendEvent("chatevent", data);
            }
        });

        server.start();
    }
}
