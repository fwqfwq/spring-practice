package crawler;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class Test1 {

    public static void get_html(String url){
        try {
            Document doc = Jsoup.connect(url)
//                    .timeout(3000)
                    .get();

            Elements contents = doc.select("div.col-sm-8");

            for(Element content: contents) {
                System.out.println(content.select("a").attr("href"));
                System.out.println(content.select("img").attr("src"));
                System.out.println(content.select("p[class]").first().text());
                System.out.println(content.select("p[class]").next().text());
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        String url = "https://editorial.rottentomatoes.com/";
//        String url = "https://www.w3schools.com/w3css/tryw3css_templates_clothing_store.htm";
        get_html(url);

    }

}
