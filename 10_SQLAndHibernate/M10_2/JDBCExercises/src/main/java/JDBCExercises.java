import Entities.Course;
import Entities.CourseType;
import Entities.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.*;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class JDBCExercises {
    public static void main(String[] args)  {
        StandardServiceRegistry standardServiceRegistry = new StandardServiceRegistryBuilder()
                .configure("hibernate.cfg.xml").build();
        Metadata metadata = new MetadataSources(standardServiceRegistry).getMetadataBuilder().build();
        try(
        SessionFactory sessionFactory = metadata.getSessionFactoryBuilder().build();
        Session session = sessionFactory.openSession()) {

//            Course course = session.get(Course.class, 3);
//            System.out.println("Название курса - " + course.getName());
//            System.out.println("Всего студентов - " + course.getStudentsList().size());
//            System.out.println("Список студентов:");
//            course.getStudentsList().forEach(student -> {
//                System.out.println(student.getName());
//            });
            Transaction transaction = session.beginTransaction();
            Course course1 = new Course();
            course1.setName("Еще еще курс");
            course1.setTeacherId(1);
            course1.setPrice(22000);
            course1.setType(CourseType.BUSINESS);
            session.save(course1);
            transaction.commit();
        }
    }
}
