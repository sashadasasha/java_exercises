package Entities;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "teachers")
public class Teacher {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;
    private int salary;
    private int age;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name ="courses_teachers",
            joinColumns = {@JoinColumn(name = "teacher_id", columnDefinition = "INT UNSIGNED")},
            inverseJoinColumns = {@JoinColumn(name="course_id", columnDefinition = "INT UNSIGNED")})
    private Set<Course> courseSet;

    public Teacher () {}

    public Teacher(int id, String name, int salary, int age) {
        this.id = id;
        this.name = name;
        this.salary = salary;
        this.age = age;
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

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Set<Course> getCourseSet() {
        return courseSet;
    }

    public void setCourseSet(Set<Course> courseSet) {
        this.courseSet = courseSet;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (!(o instanceof Teacher))
            return false;

        Teacher other = (Teacher) o;

        return id != 0 &&
                id == other.getId();
    }

    @Override
    public int hashCode() {
        return 31;
    }
}
