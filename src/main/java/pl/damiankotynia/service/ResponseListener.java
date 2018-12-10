package pl.damiankotynia.service;

import pl.damiankotynia.exceptions.InvalidResponseFormatException;
import pl.damiankotynia.model.Response;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class ResponseListener implements Runnable{
    private ObjectInputStream inputStream;
    private boolean isRunning;

    public ResponseListener(ObjectInputStream inputStream) {
        this.inputStream = inputStream;
        this.isRunning=true;
    }

    @Override
    public void run() {
        while (isRunning) {
            Response response = null;
            try {

                //TODO obsługa responsów w zależności od tego jaki otrzymaliśmy

                response = getResponse(inputStream.readObject());
                System.out.println(response.toString());
            } catch (InvalidResponseFormatException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
                isRunning ^= true;
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    private Response getResponse(Object response) throws InvalidResponseFormatException {
        if (!(response instanceof Response))
            throw new InvalidResponseFormatException();
        else
            return (Response) response;
    }
}