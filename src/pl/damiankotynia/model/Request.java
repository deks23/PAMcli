package pl.damiankotynia.model;

import java.io.Serializable;

public class Request implements Serializable {
    private Service service;
    private RequestType requestType;

    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
    }

    public RequestType getRequestType() {
        return requestType;
    }

    public void setRequestType(RequestType requestType) {
        this.requestType = requestType;
    }

    @Override
    public String toString() {
        return "Request{" +
                "service=" + service +
                ", requestType=" + requestType +
                '}';
    }
}
