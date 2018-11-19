package pl.damiankotynia.model;

import java.io.Serializable;
import java.util.List;

public class Response implements Serializable {
    private ResponseType responseType;
    private String message;
    private List<Service> serviceList;

    public ResponseType getResponseType() {
        return responseType;
    }

    public void setResponseType(ResponseType responseType) {
        this.responseType = responseType;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
