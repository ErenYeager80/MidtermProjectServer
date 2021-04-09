package main;

import javafx.concurrent.Task;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.TimeUnit;

import static main.Config.*;
import static main.Globals.LOG;

public class InitServer extends Task<Long> {
    private ServerSocket ss;

    @Override
    protected Long call() throws Exception {

        try {
            ss= new ServerSocket(SERVER_PORT);
        } catch (IOException e) {
            e.printStackTrace();
        }
        LOG.add("---Waiting for players---");
        for(int i=0;JOINED_PLAYERS<SERVER_SIZE;i++){
            try {
                Socket s=ss.accept();
                LOG.add("---"+ (i+1) +" player requested---");
                Thread thread = new Thread(new InitialResponse(s));
                thread.start();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return 0L;
    }

    public static void send(Socket s, String data) throws IOException, InterruptedException {
        DataOutputStream out = new DataOutputStream(s.getOutputStream());
        out.writeUTF(data);
        out.flush();
    }
    public static void sendObject(Socket s, Object  data) throws IOException, InterruptedException {
        ObjectOutputStream out=new ObjectOutputStream(s.getOutputStream());
        out.writeObject(data);
        out.flush();
    }

    public static String receive(Socket s) throws IOException {
        DataInputStream in = new DataInputStream(s.getInputStream());
        return in.readUTF();
    }
}
