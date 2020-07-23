package Entities;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "linkedpurchaselist")
public class LinkedPurchase {

    @EmbeddedId
    private LinkedPurchaseKey purchaseKye;

    public LinkedPurchase() {
    }

    public LinkedPurchase(LinkedPurchaseKey purchaseKye) {
        this.purchaseKye = purchaseKye;
    }

    public LinkedPurchaseKey getPurchaseKye() {
        return purchaseKye;
    }

    public void setPurchaseKye(LinkedPurchaseKey purchaseKye) {
        this.purchaseKye = purchaseKye;
    }
}
