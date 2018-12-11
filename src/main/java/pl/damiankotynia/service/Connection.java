package pl.damiankotynia.service;

import pl.damiankotynia.exceptions.InvalidResponseFormatException;
import pl.damiankotynia.model.Response;
import pl.damiankotynia.model.Service;

import java.io.*;
import java.net.Socket;
import java.util.List;

public class Connection implements Runnable {
    private Socket socket;
    private int port;
    private String serverAddress;
    private ObjectInputStream inputStream;
    private ObjectOutputStream outputStream;
    private boolean isRunning;
    private List<Service> userReservations;

    public Connection(int port, String serverAddress, List<Service> usersReservations) throws IOException {
        socket = new Socket(serverAddress, port);
        BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        outputStream = new ObjectOutputStream(socket.getOutputStream());
        inputStream = new ObjectInputStream(socket.getInputStream());
        this.isRunning = true;
        this.userReservations = usersReservations;
    }

    public void run() {
       new Thread(new ResponseListener(inputStream, userReservations)).start();
    }



    private Response getResponse(Object response) throws InvalidResponseFormatException {
        if (!(response instanceof Response))
            throw new InvalidResponseFormatException();
        else
            return (Response) response;
    }

    public synchronized boolean writeObject(Object object){
        try {
            outputStream.writeObject(object);
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public Socket getSocket() {
        return socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getServerAddress() {
        return serverAddress;
    }

    public void setServerAddress(String serverAddress) {
        this.serverAddress = serverAddress;
    }

    public ObjectInputStream getInputStream() {
        return inputStream;
    }

    public void setInputStream(ObjectInputStream inputStream) {
        this.inputStream = inputStream;
    }

    public ObjectOutputStream getOutputStream() {
        return outputStream;
    }

    public void setOutputStream(ObjectOutputStream outputStream) {
        this.outputStream = outputStream;
    }

    public boolean isRunning() {
        return isRunning;
    }
}
