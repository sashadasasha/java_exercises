import Entities.Course;
import Entities.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.*;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class JDBCExercises {
    public static void main(String[] args)  {
        StandardServiceRegistry standardServiceRegistry = new StandardServiceRegistryBuilder()
                .configure("hibernate.cfg.xml").build();

        Metadata metadata = new MetadataSources(standardServiceRegistry).getMetadataBuilder().build();
        SessionFactory sessionFactory = metadata.getSessionFactoryBuilder().build();

        Session session = sessionFactory.openSession();

        Course course = session.get(Course.class, 3);
        System.out.println("Название курса - " + course.getName());
        System.out.println("Всего студентов - " + course.getStudentsList().size());
        System.out.println("Список студентов:");
        course.getStudentsList().forEach(student -> {
            System.out.println(student.getName());
        });

        sessionFactory.close();
    }
}
