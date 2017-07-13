/**
 * Created by Administrator on 2017/7/12.
 */

import java.io.IOException;

/**
 * 07-12 19:18
 *
 * @author xt
 **/
public class App {

    public static String IP = "0.0.0.0";
    public static void main(String[] args) throws IOException {
        JMail jMail = new JMail("shawnsky@126.com","");
        IPListener listener = new IPListener();
        listener.setMail(jMail);
        listener.listen();
    }
}
