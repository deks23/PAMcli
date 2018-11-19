package pl.damiankotynia.model;

import java.io.Serializable;

public class Request implements Serializable {
    private Service service;
    private String nickName;
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

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    @Override
    public String toString() {
        return "Request{" +
                "service=" + service +
                ", nickName='" + nickName + '\'' +
                ", requestType=" + requestType +
                '}';
    }
}
