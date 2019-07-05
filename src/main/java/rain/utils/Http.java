package rain.utils;

import com.google.common.base.Charsets;
import com.google.common.io.CharStreams;
import lombok.Cleanup;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import lombok.val;

import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

@Slf4j
public class Http {

    public static void main(String[] args) {
        String str = new Http().postXml("https://api.weixin.qq.com/sns/oauth2/access_token" +
                "?appid=wx3ca953ad9f9aee69&secret=7694d3ef84e5aa3bac41ec2585b20caf" +
                "&code=001bHhHb0JbX8B1x4aFb00z1Hb0bHhHh&grant_type=authorization_code", "");
        System.out.println(str);

        String tempStr = str.substring(str.indexOf("\"openid\":\"") + 10, str.lastIndexOf("\",\"scope\""));
        System.out.println(tempStr);
    }

    @SneakyThrows
    public String postXml(String url, String xmlString) {
        log.debug("URL Post {}", url);

        @Cleanup("disconnect") val con = getConnection(url);
        con.setDoInput(true);
        con.setRequestMethod("POST");
        con.setDoOutput(true);
        con.setRequestProperty("Content-Type", "text/xml; charset=utf-8");
        val bytes = xmlString.getBytes(StandardCharsets.UTF_8);
        con.setRequestProperty("Content-Length", "" + bytes.length);

        @Cleanup val osw = con.getOutputStream();
        osw.write(bytes);

        return dealResponse(con);
    }

    @SneakyThrows
    private String dealResponse(HttpURLConnection con) {
        String res = asString(con);
        val responseCode = con.getResponseCode();
        if (responseCode != 200) throw new RuntimeException(responseCode + ", " + res);

        return res;
    }

    /**
     * Returns the response body as string.<br>
     * Disconnects the internal HttpURLConnection silently.
     */
    @SneakyThrows
    private String asString(HttpURLConnection con) {
        @Cleanup val is = con.getInputStream();

        val responseAsString = CharStreams.toString(new InputStreamReader(is, Charsets.UTF_8));
        log.debug("Response: {}", responseAsString);

        return responseAsString;
    }

    @SneakyThrows
    private HttpURLConnection getConnection(String url) {
        val con = (HttpURLConnection) new URL(url).openConnection();
        con.setConnectTimeout(5000);
        con.setReadTimeout(50000);
        return con;
    }
}
