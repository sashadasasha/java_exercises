<<<<<<< HEAD
import Entities.*;
import notifications.SendWorkNotification;
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

            Student student = session.get(Student.class, 3);
            Teacher teacher = session.get(Teacher.class, 5);
            SendWorkNotification sendWorkNotification = new SendWorkNotification();
            Transaction transaction = session.beginTransaction();
            sendWorkNotification.setNotificationSender(student);
            sendWorkNotification.setNotificationReceiver(teacher);
            sendWorkNotification.setNotificationHeader("Пришла one more работа");
            sendWorkNotification.setNotificationText("Проверьте работу в течение недели и дайте студенту ответ!");
            session.persist(sendWorkNotification);
            transaction.commit();
        }
    }
}
=======
import Entities.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.*;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

public class JDBCExercises {
    public static void main(String[] args)  {
        StandardServiceRegistry standardServiceRegistry = new StandardServiceRegistryBuilder()
                .configure("hibernate.cfg.xml").build();
        Metadata metadata = new MetadataSources(standardServiceRegistry).getMetadataBuilder().build();
        try(
        SessionFactory sessionFactory = metadata.getSessionFactoryBuilder().build();
        Session session = sessionFactory.openSession()) {

//            Transaction transaction = session.beginTransaction();
            //session.save(course1);
//            transaction.commit();
//           

        }
    }
}
>>>>>>> module11_1
