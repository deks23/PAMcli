package pl.damiankotynia.view;

import pl.damiankotynia.model.Service;

import java.util.List;

public class MainMenuView {



    public static void printMenu(List<Service> serviceList){
        System.out.println("\t\t\tSALON FRYZJERSKI\n\n");
        System.out.println("1.\t Dodaj rezerwację");
        System.out.println("2.\t Usun rezerwacje");
        System.out.println("3.\t Wyjdz\n");

        System.out.println("\t\t\tTwoje rezerwacje");
        if(serviceList==null||serviceList.isEmpty()){
            System.out.println("Nie posiadasz zarezerwowanych terminow");
        }else{
            for(Service service : serviceList){
                System.out.println(serviceList.indexOf(service) + ".\t " + service.toString());
            }
        }
        System.out.println("\n\n####################################");
    }




    public static void printIncentive(){
        System.out.println("Wprowadz numer funkcji\n");
    }

}
