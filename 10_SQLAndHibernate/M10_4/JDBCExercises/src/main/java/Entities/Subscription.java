package Entities;

import org.hibernate.annotations.ManyToAny;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "subscriptions")
public class Subscription {

    @EmbeddedId
    private SubscriptionsKey subscriptionsKey;
    @Column(name = "subscription_date")
    private Timestamp subscriptionDate;

    public Subscription() {}

    public Subscription(SubscriptionsKey subscriptionsKey, Timestamp subscriptionDate) {
        this.subscriptionsKey = subscriptionsKey;
        this.subscriptionDate = subscriptionDate;
    }

    public SubscriptionsKey getSubscriptionsKey() {
        return subscriptionsKey;
    }

    public void setSubscriptionsKey(SubscriptionsKey subscriptionsKey) {
        this.subscriptionsKey = subscriptionsKey;
    }

    public Timestamp getSubscriptionDate() {
        return subscriptionDate;
    }

    public void setSubscriptionDate(Timestamp subscriptionDate) {
        this.subscriptionDate = subscriptionDate;
    }
}
