import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

public class Bootstrap {
    public static void main(String[] args) {
        Configuration config = new Configuration();
        config.configure("hibernate.cfg.xml");
        ServiceRegistry serviceRegistry = new ServiceRegistryBuilder()
                .applySettings(config.getProperties()).buildServiceRegistry();

        SessionFactory factory = config.buildSessionFactory(serviceRegistry);
        Session session = factory.openSession();
    }

}
