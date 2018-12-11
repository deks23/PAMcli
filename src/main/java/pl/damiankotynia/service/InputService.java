package pl.damiankotynia.service;



import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.util.Scanner;

public class InputService {

    private Scanner input;

    public InputService(){
        input = new Scanner(System.in);
    }

    public String getString(){
        String returnValue = input.nextLine();
        return returnValue;
    }

    public long getLong(){
        boolean isCorrect = false;
        long returnValue = 0;
        while(!isCorrect){
            String value = input.nextLine();
            try{
                returnValue = Long.parseLong(value);
                isCorrect = true;
            }catch (NumberFormatException e){
                System.out.println("Podana wartosc jest niepoprawna");
            }
        }
        return returnValue;
    }

    public int getMainMenuInput(){

        boolean isCorrect = false;
        int returnValue = 0;
        while(!isCorrect){
            String value = input.nextLine();
            try{
                returnValue = Integer.parseInt(value);
                if(returnValue>=0 && returnValue<5)
                    isCorrect = true;
                else
                    System.out.println("Podana wartosc jest niepoprawna");
            }catch (NumberFormatException e){
                System.out.println("Podana wartosc jest niepoprawna");
            }
        }
        return returnValue;
    }

    public boolean getAreYouSure(){
        while (true){
            System.out.println("Czy napewno? (T/N)");
            String value = input.nextLine();
            if(value.length()==1){
                if(value.equals("T")||value.equals("t"))
                    return true;
                else if(value.equals("N")||value.equals("n"))
                    return false;
            }
            System.out.println("Podano niepoprawny symbol");
        }
    }


    public int getInteger(){
        boolean isCorrect = false;
        int returnValue = 0;
        while(!isCorrect){
            String value = input.nextLine();
            try{
                returnValue = Integer.parseInt(value);
                isCorrect = true;
            }catch (NumberFormatException e){
                System.out.println("Podana wartosc jest niepoprawna");
            }
        }
        return returnValue;
    }

    public int getIndex(int max){
        boolean isCorrect = false;
        int returnValue = 0;
        while(!isCorrect){
            returnValue = getInteger();
            if(returnValue<=max)
                isCorrect ^= isCorrect;
        }
        return returnValue;
    }

    public LocalDateTime getDate(){
        LocalDateTime date = null;
        do {
            System.out.println("Podaj rok: ");
            int year = getInteger();
            System.out.println("Podaj miesiac: ");
            int month = getInteger();
            System.out.println("Podaj dzien: ");
            int day = getInteger();
            System.out.println("Podaj godzine: ");
            int hour = getInteger();
            int minutes = 0;
            try{
                date =LocalDateTime.of(year, month, day, hour, minutes);
            }catch (DateTimeException exception){
                System.out.println("Podana data jest nieprawidłowa. Spróbuj ponownie");
            }
        }while (!DateCheckerService.isDateAcceptable(date));
        return date;
    }

}
