import org.apache.xbean.spring.context.ClassPathXmlApplicationContext;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.sergei.ComplexController;
import org.sergei.SimpleBean;
import org.sergei.SimpleController;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by sergei on 1/19/16.
 */
/*@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring-config.xml"})*/
public class TestXbeans {

    @Test
    public void testBeanGetsCreated() {

        // Got to use the XBean version of the Application Context
        ApplicationContext context = new ClassPathXmlApplicationContext("spring-config.xml");
        assertEquals(1, context.getBeansOfType(SimpleBean.class).size());

        SimpleBean bean = context.getBean(SimpleBean.class);
        assertEquals("testMe", bean.getMyProperty());

        SimpleController controller = bean.getSimpleController();
        assertEquals("testMeToo", controller.getControllerName());

        List<ComplexController> controllers = bean.getControllers();
        assertEquals(3, controllers.size());

    }
}
