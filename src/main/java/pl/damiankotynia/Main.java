package pl.damiankotynia;

import pl.damiankotynia.model.Request;
import pl.damiankotynia.model.RequestType;
import pl.damiankotynia.model.Service;
import pl.damiankotynia.service.InputService;
import pl.damiankotynia.view.MainMenu;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

import static pl.damiankotynia.model.RequestType.POST;

public class Main {

    public static void main(String[] args) {
        int port = 4444;
        String serverAddress = "localhost";
        Connection connection = null;
        InputService inputService = new InputService();
        List<Service> userReservations = null;
        boolean exit = false;
        String nickname;
        try {
            connection = new Connection(port, serverAddress);
        } catch (IOException e) {
            e.printStackTrace();
        }


        System.out.println("Podaj swój nick");
        nickname = inputService.getString();
        System.out.println("Twój nick to " + nickname);

        while (!exit) {
            MainMenu.printMainMenu();
            MainMenu.printUserReservations(userReservations);
            MainMenu.printSeparator();


            switch (inputService.getMainMenuInput()) {
                case 1:
                    LocalDateTime date = inputService.getDate();
                    Service service = new Service(nickname, date);
                    Request request = new Request(service, POST, nickname);
                    try {
                        connection.getOutputStream().writeObject(request);
                    } catch (IOException e) {
                        System.out.println("Wystąpił nieoczekiwany błąd. Spróbuj ponownie później");
                    }
                    break;
                case 2:
                    break;
                case 3:
                    //TODO
                    break;
                case 4:
                    System.out.println("\nZakończyć działanie programu? ");
                    exit = inputService.getAreYouSure();
                    break;

            }

        }

/*
        Service service2 = new Service();

        service2.setCustomerName("deks");
        service2.setStartTime(LocalDateTime.of(2018, 11, 19, 17, 0));

        Request request = new Request();
        request.setRequestType(POST);
        request.setNickName("deks");
        Service service = new Service();
        service.setStartTime(LocalDateTime.now());
        service.setCustomerName("qweqw");
        service.setId("asd");
        request.setService(service2);


        try {
            connection.getOutputStream().writeObject(request);
            //while(true){
            Object qwe = null;
            try {
                qwe = connection.getInputStream().readObject();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

            System.out.println("obiekt z serwera " + qwe.toString());


        } catch (IOException e) {
            e.printStackTrace();
        }*/
    }

    public Request generateRequest(String nickname, RequestType requestType){

        return null;
    }

}