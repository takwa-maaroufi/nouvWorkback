package work365.work.dto;


import lombok.Data;
import lombok.NonNull;


@Data
public class PurchaseResponse {

    public String getOrderTrackingNumber() {
        return orderTrackingNumber;
    }

    public void setOrderTrackingNumber(String orderTrackingNumber) {
        this.orderTrackingNumber = orderTrackingNumber;
    }

    @NonNull
    private String orderTrackingNumber;
}