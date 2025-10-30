package com.wavegis.kafka_consumer_service.response;

public class MessageResponse {
    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "MessageResponse{message='" + message + "'}";
    }
}
