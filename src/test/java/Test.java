import bean.Product;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by Administrator on 2016/7/30.
 */
public class Test {
    public static void main(String[] args) {
//        Response<User> response = new Response<>();
//        Type genType = response.getClass().getGenericSuperclass();
//        Type[] params = ((ParameterizedType) genType).getActualTypeArguments();
//        Class entityClass = (Class) params[0];
//        int a=5;//101
//        int b=7;//111
//        int c=a&b;
//        System.out.print(b^a);
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("beans/applicationContext.xml");
        Product product = applicationContext.getBean("product", Product.class);
    }
}
