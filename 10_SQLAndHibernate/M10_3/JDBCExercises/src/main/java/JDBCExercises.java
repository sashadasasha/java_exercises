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

//            Course course = session.get(Course.class, 3);
//            System.out.println("Название курса - " + course.getName());
//            System.out.println("Всего студентов - " + course.getStudentsList().size());
//            System.out.println("Список студентов:");
//            course.getStudentsList().forEach(student -> {
//                System.out.println(student.getName());
//            });
//            Transaction transaction = session.beginTransaction();
//            Course course1 = new Course();
//            course1.setName("Еще еще курс");
//            Teacher teacher = new Teacher();
//            teacher.setName("Роберт Мартин");
//            teacher.setAge(45);
//            teacher.setSalary(75000);
//            course1.setPrice(22000);
//            course1.setTeacher(teacher);
//            course1.setType(CourseType.PROGRAMMING);
//            session.save(course1);
//            transaction.commit();

            Course course = session.get(Course.class, 3);
            Student student = session.get(Student.class, 4);

            course.getSubscriptionList().forEach(sub->{
                System.out.println(sub.getSubscriptionDate());
            });
//            Subscription subscription = session.get(Subscription.class, new SubscriptionsKey(student, course));
//            System.out.println(subscription.getSubscriptionDate());
//            System.out.println(subscription.getSubscriptionsKey().getStudent().toString());

//            Purchase purchase = session.get(Purchase.class, new PurchasesKey(course.getName(), student.getName()));
//            System.out.println(purchase.getPrice());
//            System.out.println(purchase.getPurchasesKye().getCourseName());
//            System.out.println(purchase.getPurchasesKye().getStudentName());
//            System.out.println(purchase.getSubscriptionDate());

//            Transaction transaction = session.beginTransaction();
//            Course course1 = new Course();
//            course1.setName("Очень очень нужный курс");
//            course1.setTeacher(session.get(Teacher.class,52));
//            course1.setType(CourseType.BUSINESS);
//            course1.setPrice(79000);
//            session.save(course1);
//            Purchase purchase = new Purchase();
//            purchase.setPrice(79000);
//            purchase.setPurchasesKye(new PurchasesKey(course1.getName(), student.getName()));
//            Date date = new Date();
//            Timestamp ts = new Timestamp(date.getTime());
//            purchase.setSubscriptionDate(ts);
//            session.save(purchase);
//            transaction.commit();

        }
    }
}
