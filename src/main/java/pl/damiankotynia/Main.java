package pl.damiankotynia;

import pl.damiankotynia.model.Model;
import pl.damiankotynia.model.Request;
import pl.damiankotynia.model.Service;
import pl.damiankotynia.service.Connection;
import pl.damiankotynia.service.InputService;
import pl.damiankotynia.view.MainMenuView;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import static pl.damiankotynia.model.RequestType.DELETE;
import static pl.damiankotynia.model.RequestType.GET;
import static pl.damiankotynia.model.RequestType.POST;

public class Main {

    public static void main(String[] args) {
        int port = 4444;
        String serverAddress = "localhost";
        Connection connection = null;
        InputService inputService = new InputService();
        List<Service> userReservations = new LinkedList<>();
        boolean exit = false;
        String nickname;
        Request request;
        Model model = new Model();

        try {
            connection = new Connection(port, serverAddress, model);
            new Thread(connection).start();
        } catch (IOException e) {
            System.out.println("Nie można połączyć się z serwerem. Proszę spróbować później.");
            System.exit(0);
        }


        System.out.println("Podaj swój nick");
        nickname = inputService.getString();
        System.out.println("Twój nick to " + nickname);

        while (!exit) {
            request = new Request( GET, nickname);
            try {
                connection.getOutputStream().writeObject(request);
            } catch (IOException e) {
                System.out.println("Wystąpił nieoczekiwany błąd. Spróbuj ponownie później");
            }



            switch (inputService.getMainMenuInput()) {
                case 1:
                    LocalDateTime date = inputService.getDate();
                    Service service = new Service(nickname, date);
                    request = new Request(service, POST, nickname);
                    try {
                        connection.getOutputStream().writeObject(request);
                    } catch (IOException e) {
                        System.out.println("Wystąpił nieoczekiwany błąd. Spróbuj ponownie później");
                    }
                    break;
                case 2:
                    if(model.getUserReservations().isEmpty()){
                        System.out.println("Nie posiadasz żadnych rezerwacji do usunięcia");
                        break;
                    }
                    System.out.println("Wybierz rezerwację ");

                    int index = inputService.getIndex(model.getUserReservations().size()-1);
                    request = new Request(model.getUserReservations().get(index), DELETE, nickname);
                    try {
                        connection.getOutputStream().writeObject(request);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;
                case 3:
                    System.out.println("\nZakończyć działanie programu? ");
                    exit = inputService.getAreYouSure();
                    break;


            }

        }

        try {
            connection.getOutputStream().close();
            connection.getInputStream().close();
            connection.getSocket().close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.exit(1);
    }


}