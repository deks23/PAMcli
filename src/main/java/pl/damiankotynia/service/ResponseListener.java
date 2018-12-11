package pl.damiankotynia.service;

import pl.damiankotynia.exceptions.InvalidResponseFormatException;
import pl.damiankotynia.model.Response;
import pl.damiankotynia.model.Service;
import pl.damiankotynia.view.MainMenuView;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.List;

public class ResponseListener implements Runnable {
    private ObjectInputStream inputStream;
    private boolean isRunning;
    private List<Service> userReservations;

    public ResponseListener(ObjectInputStream inputStream, List<Service> userReservations) {
        this.inputStream = inputStream;
        this.isRunning = true;
        this.userReservations = userReservations;
    }

    @Override
    public void run() {
        while (isRunning) {
            Object responseObject = null;
            Response response = null;
            try {
                responseObject = inputStream.readObject();
                try {
                    response = getResponse(responseObject);
                } catch (InvalidResponseFormatException e) {
                    System.out.println(response.toString());
                }

                switch (response.getResponseType()) {
                    case RESERVATION_COMPLETE:
                    case DELETED_RESERVATION:
                            userReservations.clear();
                            userReservations.addAll(response.getServiceList());
                        System.out.println(response.getMessage());
                        MainMenuView.printMenu(userReservations);
                        break;
                    case RESERVATION_FAILED:
                    case RESERVATION_REMOVING_FAILED:
                        System.out.println(response.getMessage());
                        break;
                    case GET_OWN_RESERVATIONS:
                        userReservations = response.getServiceList();
                        MainMenuView.printMenu(userReservations);
                }


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
