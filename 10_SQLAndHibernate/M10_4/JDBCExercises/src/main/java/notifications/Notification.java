package notifications;

import Entities.Teacher;

import javax.persistence.*;

@MappedSuperclass
@Table(name = "Notification")
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinTable(name = "Teachers")
    @JoinColumn(name = "teacher_id", columnDefinition = "INT UNSIGNED")
    private Teacher notificationReceiver;
    private String notificationText;
    private String notificationHeader;

    public Notification() {
    }

    public Notification(int id, Teacher notificationReceiver, String notificationText, String notificationHeader) {
        this.id = id;
        this.notificationReceiver = notificationReceiver;
        this.notificationText = notificationText;
        this.notificationHeader = notificationHeader;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Teacher getNotificationReceiver() {
        return notificationReceiver;
    }

    public void setNotificationReceiver(Teacher notificationReceiver) {
        this.notificationReceiver = notificationReceiver;
    }

    public String getNotificationText() {
        return notificationText;
    }

    public void setNotificationText(String notificationText) {
        this.notificationText = notificationText;
    }

    public String getNotificationHeader() {
        return notificationHeader;
    }

    public void setNotificationHeader(String notificationHeader) {
        this.notificationHeader = notificationHeader;
    }
}
