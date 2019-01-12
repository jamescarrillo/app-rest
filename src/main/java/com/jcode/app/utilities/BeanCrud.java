package com.jcode.app.utilities;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class BeanCrud {

    private String messageServer;
    private BeanPagination beanPagination;

    public String getMessageServer() {
        return messageServer;
    }

    public void setMessageServer(String messageServer) {
        this.messageServer = messageServer;
    }

    public BeanPagination getBeanPagination() {
        return beanPagination;
    }

    public void setBeanPagination(BeanPagination beanPagination) {
        this.beanPagination = beanPagination;
    }

    @Override
    public String toString() {
        return "BeanCrud [messageServer=" + messageServer + ", beanPagination=" + beanPagination + "]";
    }

}
