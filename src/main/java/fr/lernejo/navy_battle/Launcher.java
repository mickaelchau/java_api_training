package fr.lernejo.navy_battle;


public class Launcher {
    
    public static void main(String[] args) {
        if (args.length != 2 && args.length != 1) {
            System.err.println("Program must be start with 1 or 2 arguments.");
            return;
        }
        Server server = new Server(Integer.parseInt(args[0]));
        server.runHttpServer();
        if (args.length == 2) {
            String adversaryUrl = args[1];
            String port = args[0];
            server.client.sendStartRequest(adversaryUrl, port);
        }
    }
}
