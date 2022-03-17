package fr.lernejo;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import com.sun.net.httpserver.HttpServer;


public class Launcher {
    public static void main(String args[]) {
        ServerTools serverTools = new ServerTools();
        HttpServer server = serverTools.initHttpServer();
        if (server == null) {
            return;
        }
        ExecutorService executor = Executors.newFixedThreadPool(1);
        server.setExecutor(executor);

        ServerHandler serverHandler = new ServerHandler();
        serverHandler.createPingContext(server);
        serverHandler.createStartContext(server);

        server.start();
    }
}
