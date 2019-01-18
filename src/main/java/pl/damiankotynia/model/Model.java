package pl.damiankotynia.model;

import java.util.LinkedList;
import java.util.List;

public class Model {
    private List<Service> userReservations;

    public Model() {
        userReservations = new LinkedList<>();
    }

    public List<Service> getUserReservations() {
        return userReservations;
    }

    public void setUserReservations(List<Service> usersReservations) {
        this.userReservations = usersReservations;
    }
}
