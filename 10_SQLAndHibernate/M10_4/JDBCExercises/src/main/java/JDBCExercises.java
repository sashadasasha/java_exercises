import Entities.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.*;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;

import java.sql.Timestamp;
import java.util.List;
import java.util.Set;

public class JDBCExercises {
    public static void main(String[] args)  {
        StandardServiceRegistry standardServiceRegistry = new StandardServiceRegistryBuilder()
                .configure("hibernate.cfg.xml").build();
        Metadata metadata = new MetadataSources(standardServiceRegistry).getMetadataBuilder().build();
        try(SessionFactory sessionFactory = metadata.getSessionFactoryBuilder().build();
            Session session = sessionFactory.openSession()) {

            Course course = session.get(Course.class, 8);
            Teacher teacher = session.get(Teacher.class, 14);

            Set<Course> courseSet = teacher.getCourseSet();
            Transaction transaction = session.beginTransaction();
            courseSet.add(course);
            teacher.setCourseSet(courseSet);
            session.save(teacher);
            transaction.commit();
        }
    }
}
