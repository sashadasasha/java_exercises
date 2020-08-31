package notifications;

import Entities.Student;
import Entities.Teacher;

import javax.persistence.*;

@Entity
public class SendWorkNotification extends Notification {

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(columnDefinition = "INT UNSIGNED")
    private Student notificationSender;

    public SendWorkNotification() {
    }

    public SendWorkNotification(int id, Teacher notificationReceiver, String notificationText, String notificationHeader, Student notificationSender) {
        super(id, notificationReceiver, notificationText, notificationHeader);
        this.notificationSender = notificationSender;
    }

    public Student getNotificationSender() {
        return notificationSender;
    }

    public void setNotificationSender(Student notificationSender) {
        this.notificationSender = notificationSender;
    }
}
