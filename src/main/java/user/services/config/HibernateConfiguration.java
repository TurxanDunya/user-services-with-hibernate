package user.services.config;

import java.util.Properties;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;
import user.services.entity.Gender;
import user.services.entity.Phone;
import user.services.entity.Profession;
import user.services.entity.User;

public class HibernateConfiguration {

    private static SessionFactory sessionFactory;

    public static SessionFactory getSessionFactory() {
        if (sessionFactory != null) {
            return sessionFactory;
        }

        try {
            Configuration configuration = new Configuration();

            Properties properties = new Properties();
            properties.put(Environment.DRIVER, "oracle.jdbc.driver.OracleDriver");
            properties.put(Environment.URL, "jdbc:oracle:thin:@//localhost:1521/xe");
            properties.put(Environment.USER, "hope");
            properties.put(Environment.PASS, "hardpassword");
            properties.put(Environment.DIALECT, "org.hibernate.dialect.Oracle8iDialect");
            properties.put(Environment.SHOW_SQL, "true");
            properties.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");
            properties.put(Environment.HBM2DDL_AUTO, "update");

            configuration.setProperties(properties);
            configuration.addAnnotatedClass(User.class);
            configuration.addAnnotatedClass(Gender.class);
            configuration.addAnnotatedClass(Phone.class);
            configuration.addAnnotatedClass(Profession.class);

            ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                    .applySettings(configuration.getProperties())
                    .build();

            sessionFactory = configuration.buildSessionFactory(serviceRegistry);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return sessionFactory;
    }

}
