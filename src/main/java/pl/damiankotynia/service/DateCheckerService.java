package pl.damiankotynia.service;

import java.time.LocalDateTime;
import java.time.LocalTime;

public class DateCheckerService {

   public static boolean isInOpenHours(LocalDateTime startTime){
       LocalDateTime endTime = startTime.plusHours(1L);
       if(FINISH_TIME.isBefore(endTime.toLocalTime())||START_TIME.isAfter(startTime.toLocalTime())){
            System.out.println("Termin poza godzinami otwarcia");
           return false;
       }
       return true;
   }

   public static boolean isInFuture(LocalDateTime startTime){
       if(LocalDateTime.now().isAfter(startTime)){
            System.out.println("Termin znajduje się w przeszłości.");
           return false;
       }
       return true;
   }

   public static boolean isDateAcceptable(LocalDateTime startTime){
       if(startTime.equals(null))
           return false;
       if(isInOpenHours(startTime)&&isInFuture(startTime))
           return true;
       return false;
   }

    private static final LocalTime START_TIME = LocalTime.of(10, 0);
    private static final LocalTime FINISH_TIME = LocalTime.of(18, 0);
}
