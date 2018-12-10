import com.RabbitApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author: fanbopeng
 * @Date: 2018/11/20 15:54
 * @Description:
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = RabbitApplication.class)
public class ProductTest {

    @Autowired
    private RabbitTemplate rabbitTemplate;


    @Test
    public void send() {
            for (int i=0;i<10;i++) {
                rabbitTemplate.convertAndSend("fanbopeng", "123");
                System.out.println(12313);
            }
    }
    @Test
    public void send1() {
        for (int i=0;i<10;i++) {
            rabbitTemplate.convertAndSend("chuanzhi","","分裂模式测试");
            System.out.println(12313);
        }
    }

    @Test
    public void sendTpic() {

        rabbitTemplate.convertAndSend("exchange","","分裂模式");

    }

    @Test
    public void sendTpic1() {

        for (int i =0;i<10;i++) {
            rabbitTemplate.convertAndSend("topic1", "fannn", "hahaha");
        }
    }



}
