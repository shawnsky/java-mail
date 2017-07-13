/**
 * Created by Administrator on 2017/7/12.
 */

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 07-12 19:22
 *
 * @author xt
 **/
public class IPListener {

    private JMail jMail;

    public void setMail(JMail jMail){
        this.jMail = jMail;
    }

    public void get() throws IOException {
        Document doc = Jsoup.connect("https://baidu.com/s?wd=ip").get();
        Element i = doc.select("span.c-gap-right").first();
        String str = i.text();
        Pattern pattern = Pattern.compile("\\d+\\.\\d+\\.\\d+\\.\\d+");
        Matcher matcher = pattern.matcher(str);
        if (matcher.find()){
            String ip = matcher.group(0);
            if(!ip.equals(App.IP)){
                String info = "ip address has changed from ["+App.IP+"] to ["+ip+"].";
                System.out.println(info);
                if(jMail!=null){
                    try {
                        jMail.send("5835007@qq.com","advice","IP changes", info);
                    } catch (Exception e) {
                        System.out.println("ERROR:"+e.getMessage());
                    }
                }

                App.IP = ip;
            }

        }

    }


    public void listen(){
        Thread thread = new Thread(() -> {
            try {
                for (;;){
                    get();
                    Thread.sleep(1000*30);
                }

            } catch (IOException | InterruptedException e) {
                System.out.println(e.getMessage());
            }
        });
        thread.start();
    }
}
