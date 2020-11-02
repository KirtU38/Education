import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.util.List;

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
        System.out.println("Запрос к PurchaseList вывести кто, за сколько и какой курс купил");

        SubscriptionCK subscriptionCK = new SubscriptionCK(2, 11);
        Subscription subscription = session.get(Subscription.class, subscriptionCK);
        System.out.println(subscription.getStudent().getName() + " - " + subscription.getCourse().getName());
        System.out.println("Запрос к Subscription вывести кто и на какой курс подписан (ManyToOne к Students и Courses)");

        Student student = session.get(Student.class, 1);
        List<Subscription> subscriptions = student.getSubscriptions();
        for (Subscription sub : subscriptions) {
            System.out.println(sub.getCourse().getName());
        }
        System.out.println("Запрос к Student вывести все курсы на которых учится студент(OneToMany к Subscription)");

        Teacher teacher = session.get(Teacher.class, 10);
        List<Course> courses = teacher.getCourses();
        for (Course course : courses) {
            System.out.println(course.getName());
        }
        System.out.println("Запрос к Teacher вывести список курсов которые он ведет(OneToMane к Course)");

        PurchaseListCK pListCK = new PurchaseListCK("Абакумов Казимир", "Figma");
        PurchaseList pList = session.get(PurchaseList.class, pListCK);
        System.out.println("Курс - " + pList.getCourse().getId() + " Студент - " + pList.getStudent().getId());
        System.out.println("Запрос к PurchaseList вывести ID купленного курса и ID купившего студента" +
                "(ManyToOne к Course и Student)");

        transaction.commit();
        sessionFactory.close();
    }
}
