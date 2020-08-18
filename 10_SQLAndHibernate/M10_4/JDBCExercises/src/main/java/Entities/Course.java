package Entities;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "Courses")
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private int duration;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "ENUM('DESIGN','PROGRAMMING','MARKETING','MANAGMENT','BUSINESS')")
    private CourseType type;
    private String description;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name ="courses_teachers",
    joinColumns = {@JoinColumn(name = "course_id", columnDefinition = "INT UNSIGNED")},
    inverseJoinColumns = {@JoinColumn(name="teacher_id", columnDefinition = "INT UNSIGNED")})
    private Set<Teacher> teachersSet;

    @Column(name = "students_count")
    private int studentsCount;
    private int price;
    @Column(name = "price_per_hour")
    private float pricePerHour;

    @ManyToMany
    @JoinTable(name = "Subscriptions",
    joinColumns = {@JoinColumn (name= "course_id")},
    inverseJoinColumns = {@JoinColumn (name = "student_id")})
    private List<Student> studentsList;

    @OneToMany(mappedBy = "subscriptionsKey.course")
    private List<Subscription> subscriptionList;

//    @OneToMany(mappedBy = "purchaseKey.course")
//    private List<LinkedPurchase> linkedPurchaseList;

    public Course(int id, String name, int duration, CourseType type, String description, Set<Teacher> teachersSet, int studentsCount, int price, float pricePerHour, List<Student> studentsList) {
        this.id = id;
        this.name = name;
        this.duration = duration;
        this.type = type;
        this.description = description;
        this.teachersSet = teachersSet;
        this.studentsCount = studentsCount;
        this.price = price;
        this.pricePerHour = pricePerHour;
        this.studentsList = studentsList;
    }

    public Course() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public CourseType getCourseType() {
        return type;
    }

    public void setCourseType(CourseType courseType) {
        this.type = courseType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getStudentsCount() {
        return studentsCount;
    }

    public void setStudentsCount(int studentsCount) {
        this.studentsCount = studentsCount;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public float getPricePerHour() {
        return pricePerHour;
    }

    public void setPricePerHour(float pricePerHour) {
        this.pricePerHour = pricePerHour;
    }

    public Entities.CourseType getType() {
        return type;
    }

    public void setType(Entities.CourseType type) {
        this.type = type;
    }

    public List<Student> getStudentsList() {
        return studentsList;
    }

    public List<Subscription> getSubscriptionList() {
        return subscriptionList;
    }

    public Set<Teacher> getTeachersSet() {
        return teachersSet;
    }

    public void setTeachersSet(Set<Teacher> teachersSet) {
        this.teachersSet = teachersSet;
    }

    //    public List<LinkedPurchase> getLinkedPurchaseList() {
//        return linkedPurchaseList;
//    }

    @Override
    public String toString() {
        return "Course{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", duration=" + duration +
                ", type=" + type +
                ", description='" + description + '\'' +
                ", teacher=" + teachersSet +
                ", studentsCount=" + studentsCount +
                ", price=" + price +
                ", pricePerHour=" + pricePerHour +
                ", studentsList=" + studentsList +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (!(o instanceof Course))
            return false;

        Course other = (Course) o;

        return id != 0 &&
                id == other.getId();
    }

    @Override
    public int hashCode() {
        return 31;
    }
}
