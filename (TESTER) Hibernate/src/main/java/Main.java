import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class Main {

    public static void main(String[] args) {

        StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure("hibernate.cfg.xml").build();
        Metadata metadata = new MetadataSources(registry).getMetadataBuilder().build();
        SessionFactory sessionFactory = metadata.getSessionFactoryBuilder().build();

        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();



        PurchaseListCK purchaseListCK = new PurchaseListCK("Абакумов Казимир", "Figma");
        PurchaseList purchaseList = session.get(PurchaseList.class, purchaseListCK);
        System.out.println(purchaseList.getPurchaseListCK().getCourseName() + " - "
                + purchaseList.getPurchaseListCK().getStudentName() + " - " + purchaseList.getPrice() + " рублей");


        SubscriptionCK subscriptionCK = new SubscriptionCK(2, 11);
        Subscription subscription = session.get(Subscription.class, subscriptionCK);
        System.out.println(subscription.getStudent().getName() + " - " + subscription.getCourse().getName());


        transaction.commit();
        sessionFactory.close();
    }
}
