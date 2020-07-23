import Entities.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.*;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.util.List;

public class JDBCExercises {
    public static void main(String[] args)  {
        StandardServiceRegistry standardServiceRegistry = new StandardServiceRegistryBuilder()
                .configure("hibernate.cfg.xml").build();
        Metadata metadata = new MetadataSources(standardServiceRegistry).getMetadataBuilder().build();
        try(SessionFactory sessionFactory = metadata.getSessionFactoryBuilder().build();
            Session session = sessionFactory.openSession()) {
            String hql = "FROM " + Subscription.class.getSimpleName();
            List<Subscription> subscriptionList = session.createQuery(hql).getResultList();
            subscriptionList.forEach(el->{
                Transaction transaction = session.beginTransaction();
                LinkedPurchase linkedPurchase = new LinkedPurchase();
                linkedPurchase.setPurchaseKye(new LinkedPurchaseKey(el.getSubscriptionsKey().getStudent().getId(), el.getSubscriptionsKey().getCourse().getId()));
                session.persist(linkedPurchase);
                transaction.commit();
            });

        }
    }
}
