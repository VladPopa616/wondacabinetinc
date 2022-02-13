package com.wondacabinetinc.wondacabinetinc.Mail;

import org.hibernate.validator.constraints.Length;

public class UpdateEmailRequest {
    private long trackingNo;

    @Length(max=5000)
    private String body;

    public long getTrackingNo() {
        return trackingNo;
    }

    public void setTrackingNo(long trackingNo) {
        this.trackingNo = trackingNo;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
