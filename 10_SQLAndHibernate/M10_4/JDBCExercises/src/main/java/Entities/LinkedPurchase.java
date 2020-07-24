package Entities;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.sql.Timestamp;

@Entity
@Table(name = "linkedpurchaselist")
public class LinkedPurchase {

    @EmbeddedId
    private LinkedPurchaseKey purchaseKye;

    private int price;

    @Column(name = "subscription_date")
    private Timestamp subscriptionDate;

    public LinkedPurchase() {
    }

    public LinkedPurchase(LinkedPurchaseKey purchaseKye, int price, Timestamp subscriptionDate) {
        this.purchaseKye = purchaseKye;
        this.price = price;
        this.subscriptionDate = subscriptionDate;
    }

    public LinkedPurchaseKey getPurchaseKye() {
        return purchaseKye;
    }

    public void setPurchaseKye(LinkedPurchaseKey purchaseKye) {
        this.purchaseKye = purchaseKye;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Timestamp getSubscriptionDate() {
        return subscriptionDate;
    }

    public void setSubscriptionDate(Timestamp subscriptionDate) {
        this.subscriptionDate = subscriptionDate;
    }
}
