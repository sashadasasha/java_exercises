package Entities;

import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.OneToOne;
import java.io.Serializable;

@Embeddable
public class SubscriptionsKey implements Serializable {
    @OneToOne(cascade = CascadeType.ALL)
    private Student student;
    @OneToOne(cascade = CascadeType.ALL)
    private Course course;

    public SubscriptionsKey() {
    }

    public SubscriptionsKey(Student student, Course course) {
        this.student = student;
        this.course = course;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }
}
