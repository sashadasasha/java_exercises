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
                String hql = "FROM Student WHERE name = :studentName";
                Query query1 = session.createQuery(hql);
                query1.setParameter("studentName", el[0]);
                List<Student> students = query1.getResultList();
                Student student = students.get(0);
                String hql2 = "FROM Course WHERE name = :courseName";
                Query query2 = session.createQuery(hql2);
                query2.setParameter("courseName", el[1]);
                Course course = (Course) query2.getSingleResult();
                Transaction transaction = session.beginTransaction();
                LinkedPurchase linkedPurchase = new LinkedPurchase();
                linkedPurchase.setPurchaseKey(new LinkedPurchaseKey(student,course));
                linkedPurchase.setPrice((Integer)el[2]);
                linkedPurchase.setSubscriptionDate((Timestamp)el[3]);
                session.persist(linkedPurchase);
                transaction.commit();
            });
        }
    }
}
