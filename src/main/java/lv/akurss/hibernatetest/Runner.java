package lv.akurss.hibernatetest;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;

public class Runner {

    public static void main(String[] args) {

        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();

        Session session = sessionFactory.openSession();

        Transaction tx = session.beginTransaction();

        User user = new User();
        user.setName("Alex");
        user.setSurname("Ivanov");
        user.setLogin("alex111");

        Task task1 = new Task();
        task1.setTitle("Do homework");
        task1.setDone(false);
        task1.setUser(user);

        Task task2 = new Task();
        task2.setTitle("Sleep");
        task2.setDone(true);
        task2.setUser(user);

        List<Task> tasks = new ArrayList<>();
        tasks.add(task1);
        tasks.add(task2);

        user.setTasks(tasks);

        session.save(user);

        tx.commit();

        session.close();


        //======================================

        Session session1 = sessionFactory.openSession();

        Transaction tx1 = session1.beginTransaction();

        User u = session1.get(User.class, 1L);

        System.out.println(u.getName() + " " + u.getSurname());

        Query query = session1.createQuery("from Task where done = false");

        List<Task> qTasks = query.list();

        for (Task qTask : qTasks) {
            System.out.println(qTask.getTitle());

            qTask.setDone(true);

            session1.update(qTask);


        }

        tx1.commit();

        session1.close();

        //===============


        Session session3 = sessionFactory.openSession();

        User alex = session3.get(User.class, 1L);
        System.out.println(alex.getName());
        alex.getTasks().size();

        session3.close();

        System.out.println(alex.getSurname());
        System.out.println(alex.getTasks().size());

        //====================


        Session session4 = sessionFactory.openSession();
        Transaction tx4 = session4.beginTransaction();

        User u2 = new User();
        u2.setName("Tolik");
        u2.setSurname("Ivanov");
        u2.setId(null);

        session4.persist(u2);


        tx4.commit();
        session4.close();
    }
}
