package com.liaoguoyin.aipao.api.Entity;

public class uploadEntity {

    /**
     * Success : true
     * Data : success
     */

    private boolean Success;
    private String Data;

    public boolean isSuccess() {
        return Success;
    }

    public void setSuccess(boolean Success) {
        this.Success = Success;
    }

    public String getData() {
        return Data;
    }

    public void setData(String Data) {
        this.Data = Data;
    }
}
