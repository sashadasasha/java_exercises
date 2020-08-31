package notifications;

import Entities.Course;
import Entities.Teacher;

import javax.persistence.*;

@Entity
public class AddCourseNotification extends Notification {
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(columnDefinition = "INT UNSIGNED")
    private Course notificationSender;

    public AddCourseNotification(Course notificationSender) {
    }

    public AddCourseNotification(int id, Teacher notificationReceiver, String notificationText, String notificationHeader, Course notificationSender) {
        super(id, notificationReceiver, notificationText, notificationHeader);
        this.notificationSender = notificationSender;
    }

    public Course getNotificationSender() {
        return notificationSender;
    }

    public void setNotificationSender(Course notificationSender) {
        this.notificationSender = notificationSender;
    }
}
