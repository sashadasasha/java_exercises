package Entities;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class PurchasesKey implements Serializable {

    @Column(name = "course_name")
    private String courseName;

    @Column(name = "student_name")
    private String studentName;

    public PurchasesKey() {
    }

    public PurchasesKey(String courseName, String studentName) {
        this.courseName = courseName;
        this.studentName = studentName;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }
}
