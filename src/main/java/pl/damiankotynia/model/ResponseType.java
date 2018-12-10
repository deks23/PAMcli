package pl.damiankotynia.model;

import java.io.Serializable;

public enum ResponseType implements Serializable {
    RESERVATION_COMPLETE,
    RESERVATION_FAILED,
    NEW_RESERVATION,
    DELETED_RESERVATION,
    RESERVATION_REMOVING_FAILED,
    GET_RESERVATIONS,
    GET_OWN_RESERVATIONS
}
