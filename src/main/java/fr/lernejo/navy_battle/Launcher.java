package fr.lernejo.navy_battle;


public class Launcher {
    
    public static void main(String[] args) {
        if (args.length != 2 && args.length != 1) {
            System.err.println("Program must be start with 1 or 2 arguments.");
            return;
        }
        Server serverTools = new Server(Integer.parseInt(args[0]));
        serverTools.runHttpServer();
        if (args.length == 2) {
            String adversaryUrl = args[1];
            Client client = new Client();
            String endpoint = adversaryUrl + "/api/game/start";
            String message = "{\"id\":\"" + args[0] + "\", \"url\":\"http://localhost:" + args[0] + "\", \"message\":\"hello\"}";
            client.sendStartRequest(endpoint, message);
            client.sendGetFireRequest(adversaryUrl);
        }
    }
}
