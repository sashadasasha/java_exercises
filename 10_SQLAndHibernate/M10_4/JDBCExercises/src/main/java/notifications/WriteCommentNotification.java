package notifications;

import Entities.Student;
import Entities.Teacher;

import javax.persistence.*;

@Entity
public class WriteCommentNotification extends Notification {

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinTable(name = "Students")
    @JoinColumn(name = "student_id", columnDefinition = "INT UNSIGNED")
    private Student notificationSender;

    public WriteCommentNotification(Student notificationSender) {

    }

    public WriteCommentNotification(int id, Teacher notificationReceiver, String notificationText, String notificationHeader, Student notificationSender) {
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
