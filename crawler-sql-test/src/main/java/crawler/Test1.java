package crawler;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashSet;
import java.util.Set;

import com.csvreader.CsvWriter;


public class Test1 {

    private final static String CSV_PATH = "~/Downloads/crawler/data3.csv";
    private static CsvWriter cw;
    private static File csv;



    public static void get_html(String url) throws IOException {
        Set<String> imgCollection = new HashSet<String>();

        csv = new File(CSV_PATH);

//        if (csv.isFile()) {
//            csv.delete();
//        }

        cw = new CsvWriter(new FileWriter(csv, true), ',');
        cw.write("brand");
        cw.write("model");
        cw.write("parts");
        cw.write("img");
        cw.endRecord();


        try {
            String website = "http://tb.cnhlwl.com/";

            Document doc = Jsoup.connect(url).get();
            Elements contents = doc.select("a[href]").select(".aui-flex");

//            cw = new CsvWriter(new FileWriter(csv, true), ',');
            for (Element content : contents) {

                // brand
                String brand = content.select(".aui-flex-box").text();
//                if(!brand.equals("法拉利")) continue;

                System.out.println(brand);

                Document jump1 = Jsoup.connect(website + content.attr("href")).get();
                Elements pages1 = jump1.select("a[href]").select(".aui-flex");
                for (Element page1 : pages1) {

                    Document jump2 = Jsoup.connect(website + page1.attr("href")).get();
                    Elements pages2 = jump2.select(".sr-productOne");
                    for (Element page2 : pages2) {

                        // model
                        String model = page2.select(".sr-productAdaptation").select("strong").text();
                        System.out.println(model);
                        //parts
                        String parts = page2.select(".sr-productAdaptation").select("p").text();
                        System.out.println(parts);

                        if(!parts.equals("暂无资料")) {
                            // image
                            Elements imgs = page2.select("img[src]");
                            StringBuilder imgList = new StringBuilder();

                            for(Element img : imgs) {
                                String imgg = img.attr("src");
                                if(imgg.isEmpty()) break;

                                String imgName = imgg.substring(8);
                                System.out.println(imgName);

                                imgList.append(imgName).append(" ");

                                if(!imgCollection.contains(imgName)) {
                                    imgCollection.add(imgName);

                                    URL imgurl = new URL(website + imgg);
                                    URLConnection uri = imgurl.openConnection();
                                    InputStream is = uri.getInputStream();
                                    OutputStream os = new FileOutputStream(new File("~/Downloads/imgDownload", imgName));

                                    byte[] buf = new byte[1024];
                                    int len = -1;

                                    while ((len = is.read(buf)) != -1) {
                                        os.write(buf, 0, len);
                                    }
                                    System.out.println("downloaded");
                                }
                            }

                            cw.write(brand);
                            cw.write(model);
                            cw.write(parts);
                            cw.write(imgList.toString());
                            cw.endRecord();
                        }

                        else {
                            cw.write(brand);
                            cw.write(model);
                            cw.write(parts);
                            cw.write("暂无资料");
                            cw.endRecord();
                        }

                    }
                }
//                Thread.sleep(1000);

            }

        } catch (IOException e) {
            e.printStackTrace();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
        }

        cw.close();
    }




    public void crawlerCsv() throws IOException {
        csv = new File(CSV_PATH);

        if (csv.isFile()) {
            csv.delete();
        }

        cw = new CsvWriter(new FileWriter(csv, true), ',');
        cw.write("brand");
        cw.write("model");
        cw.write("parts");
        cw.write("img");
        cw.endRecord();
        cw.close();
    }


    public static void main(String[] args) throws IOException {
//        String url = "https://editorial.rottentomatoes.com/";
//        String url = "https://www.w3schools.com/w3css/tryw3css_templates_clothing_store.htm";
        String url = "http://tb.cnhlwl.com/";
        get_html(url);

    }

}
