package pl.damiankotynia;

import pl.damiankotynia.model.Request;
import pl.damiankotynia.model.RequestType;
import pl.damiankotynia.model.Service;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.LocalDateTime;

public class Main {

    public static void main(String[] args) {
        int port = 4444;
        String serverAddress = "localhost";
        Connection connection = null;

        try {
            connection = new Connection(port, serverAddress);
        } catch (IOException e) {
            e.printStackTrace();
        }


        System.out.println("Ok");
        Request request = new Request();
        request.setRequestType(RequestType.POST);
        Service service = new Service();
        service.setStartTime(LocalDateTime.now());
        service.setCustomerName("qweqw");
        service.setId(12);
        request.setService(service);
        try {
            connection.getOutputStream().writeObject(request);
            connection.getOutputStream().close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}