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

public class JDBCExercises {
    public static void main(String[] args)  {
        StandardServiceRegistry standardServiceRegistry = new StandardServiceRegistryBuilder()
                .configure("hibernate.cfg.xml").build();
        Metadata metadata = new MetadataSources(standardServiceRegistry).getMetadataBuilder().build();
        try(SessionFactory sessionFactory = metadata.getSessionFactoryBuilder().build();
            Session session = sessionFactory.openSession()) {
            NativeQuery query = session.createSQLQuery("SELECT * FROM purchaselist");
            List<Object[]> list = query.getResultList();
            list.forEach(el->{
                Purchase purchase = new Purchase();
                purchase.setPurchasesKye(new PurchasesKey(el[1].toString(), el[0].toString()));
                purchase.setPrice(Integer.parseInt(el[2].toString()));
                purchase.setSubscriptionDate(Timestamp.valueOf(el[3].toString()));

                String hql = "FROM " + Student.class.getSimpleName() + " WHERE name = :studentName";
                Query query1 = session.createQuery(hql);
                query1.setParameter("studentName", purchase.getPurchasesKye().getStudentName());
                List<Student> students = query1.getResultList();
                Student student = students.get(0);

                String hql2 = "FROM " + Course.class.getSimpleName() + " WHERE name = :courseName";
                Query query2 = session.createQuery(hql2);
                query2.setParameter("courseName", purchase.getPurchasesKye().getCourseName());
                List<Course> courses = query2.getResultList();
                Course course = courses.get(0);
                Transaction transaction = session.beginTransaction();
                LinkedPurchase linkedPurchase = new LinkedPurchase();
                linkedPurchase.setPurchaseKye(new LinkedPurchaseKey(student,course));
                linkedPurchase.setPrice(purchase.getPrice());
                linkedPurchase.setSubscriptionDate(purchase.getSubscriptionDate());
                session.persist(linkedPurchase);
                transaction.commit();
            });
        }
    }
}
