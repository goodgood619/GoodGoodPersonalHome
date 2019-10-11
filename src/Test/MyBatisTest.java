import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.inject.Inject;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/applicationContext.xml"})
public class MyBatisTest {
    @Inject
    private SqlSessionFactory sessionFactory;

    @Test
    public void testSessionFactory() {

    }

    @Test
    public void testSqlSession() {
        try (SqlSession session = sessionFactory.openSession()) {
            System.out.println(session);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
