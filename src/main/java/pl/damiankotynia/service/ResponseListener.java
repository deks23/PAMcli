package pl.damiankotynia.service;

import pl.damiankotynia.exceptions.InvalidResponseFormatException;
import pl.damiankotynia.model.Model;
import pl.damiankotynia.model.Response;
import pl.damiankotynia.view.MainMenuView;

import java.io.IOException;
import java.io.ObjectInputStream;

public class ResponseListener implements Runnable {
    private ObjectInputStream inputStream;
    private boolean isRunning;
    private Model model;

    public ResponseListener(ObjectInputStream inputStream, Model model) {
        this.inputStream = inputStream;
        this.isRunning = true;
        this.model = model;
    }

    @Override
    public void run() {
        while (isRunning) {
            Object responseObject = null;
            Response response = null;
            try {
                responseObject = inputStream.readObject();
                response = getResponse(responseObject);
                switch (response.getResponseType()) {
                    case RESERVATION_COMPLETE:
                    case DELETED_RESERVATION:
                        model.setUserReservations(response.getServiceList());
                        System.out.println(response.getMessage());
                        MainMenuView.printMenu(model.getUserReservations());
                        break;
                    case RESERVATION_FAILED:
                    case RESERVATION_REMOVING_FAILED:
                        System.out.println(response.getMessage());
                        break;
                    case GET_OWN_RESERVATIONS:
                        model.setUserReservations(response.getServiceList());
                        MainMenuView.printMenu(model.getUserReservations());
                    default:
                        break;
                }


            } catch (IOException e) {
                System.out.println("\n Błąd serwera. Kończę działanie aplikacji \n");
                isRunning ^= true;
            } catch (ClassNotFoundException e) {
                System.out.println("\n Niepoprawna odpowiedź serwera. Kończę działanie aplikacji \n");
                isRunning ^= true;
            } catch (InvalidResponseFormatException e) {
                System.out.println(responseObject);
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
