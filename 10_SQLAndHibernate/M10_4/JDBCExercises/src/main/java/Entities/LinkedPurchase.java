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
    private LinkedPurchaseKey purchaseKey;

    private int price;

    @Column(name = "subscription_date")
    private Timestamp subscriptionDate;

    public LinkedPurchase() {
    }

    public LinkedPurchase(LinkedPurchaseKey purchaseKey, int price, Timestamp subscriptionDate) {
        this.purchaseKey = purchaseKey;
        this.price = price;
        this.subscriptionDate = subscriptionDate;
    }

    public LinkedPurchaseKey getPurchaseKey() {
        return purchaseKey;
    }

    public void setPurchaseKey(LinkedPurchaseKey purchaseKey) {
        this.purchaseKey = purchaseKey;
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
