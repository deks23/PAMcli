package pl.damiankotynia.model;

import java.io.Serializable;
import java.time.LocalDateTime;

public class Service implements Serializable, Comparable<Service> {
    private String id;
    private String customerName;
    private LocalDateTime startTime;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }


    public Service (String customerName, LocalDateTime startTime){
        this.customerName = customerName;
        this.startTime = startTime;
    }

    public Service(){

    }
    @Override
    public String toString() {
        return "Rezerwacja{" +
                "\tid=" + id +
                ", \tnick= " + customerName + '\'' +
                ", \trozpoczecie = " + startTime +
                '}';
    }

    public String getMessage(){
        return "Termin " + startTime + " zarezerwowany przez " + customerName;
    }

    @Override
    public int compareTo(Service o) {
        if(this.getStartTime().isBefore(o.getStartTime()))
            return -1;
        if(this.getStartTime().isBefore(o.getStartTime()))
            return 1;
        return 0;
    }
}
