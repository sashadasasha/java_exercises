package Entities;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.sql.Timestamp;

@Entity
@Table(name = "purchaselist")
public class Purchase {

    @EmbeddedId
    private PurchasesKey purchasesKye;
    private int price;
    @Column(name = "subscription_date")
    private Timestamp subscriptionDate;

    public Purchase(){}

    public Purchase(PurchasesKey purchasesKye, int price, Timestamp subscriptionDate) {
        this.purchasesKye = purchasesKye;
        this.price = price;
        this.subscriptionDate = subscriptionDate;
    }

    public PurchasesKey getPurchasesKye() {
        return purchasesKye;
    }

    public void setPurchasesKye(PurchasesKey purchasesKye) {
        this.purchasesKye = purchasesKye;
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
