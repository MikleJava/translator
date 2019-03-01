package translator;

import com.cybozu.labs.langdetect.Detector;
import com.cybozu.labs.langdetect.DetectorFactory;
import com.cybozu.labs.langdetect.LangDetectException;
import com.google.gson.Gson;
import data.Text;

import javax.net.ssl.HttpsURLConnection;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLEncoder;

public class YandexTranslator {
    public static void main(String[] args) throws IOException {
//        for (int i = 0; i < args.length; i++) {
            String language = detectLanguage("123456");
            if("ru".equals(language)) { System.out.println(translate("en", "123456"));}
            else if("en".equals(language)) {System.out.println(translate("ru", "123456"));}
//        }
    }

    public static String translate(String lang, String input) throws IOException {
        String urlStr = "https://translate.yandex.net/api/v1.5/tr.json/translate?key=trnsl.1.1.20150627T071448Z.117dacaac1e63b79.6b1b4bb84635161fcd400dace9fb2220d6f344ef";
        URL urlObj = new URL(urlStr);
        HttpsURLConnection connection = (HttpsURLConnection)urlObj.openConnection();
        connection.setRequestMethod("POST");
        connection.setDoOutput(true);
        DataOutputStream dataOutputStream = new DataOutputStream(connection.getOutputStream());
        dataOutputStream.writeBytes("text=" + URLEncoder.encode(input, "UTF-8") + "&lang=" + lang);
        InputStream response = connection.getInputStream();
        String json = new java.util.Scanner(response).nextLine();
        Gson gson = new Gson();
        Text translated = gson.fromJson(json, Text.class);
        return translated.text[0];
    }

    public static String detectLanguage(String text){
        String langDetected = "";
        try {
            String path = "src\\main\\java\\translator\\profiles";
            DetectorFactory.loadProfile(path);
            Detector detector = DetectorFactory.create();
            detector.append(text);
            langDetected = detector.detect();
        } catch (LangDetectException exc) {
            System.out.println("Incorrect symbols");
        }
        return langDetected;
    }
}