/*
 * HttpRequest.java -- 16-4-6
 * Author: Hao.Jinlong
 * Email: hao.jinlong@eppdev.cn
 */
package cn.eppdev.tools.weixin.utils;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import com.thoughtworks.xstream.io.xml.XmlFriendlyNameCoder;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.conn.ConnectionPoolTimeoutException;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.SocketTimeoutException;

/**
 * @Author: Hao.Jinlong
 * @since: 16-4-6
 */
public class HttpRequest {
    static Logger logger = LoggerFactory.getLogger(HttpRequest.class);

    //连接超时时间，默认10秒
    private int socketTimeout = 10000;

    //传输超时时间，默认30秒
    private int connectTimeout = 30000;

    public String sendPost(String url, Object xmlObj){
        String result = null;
        HttpPost httpPost = new HttpPost(url);
        HttpClient httpClient = HttpClients.createDefault();

        //解决XStream对出现双下划线的bug
        XStream xStreamForRequestPostData = new XStream(new DomDriver("UTF-8", new XmlFriendlyNameCoder("-_", "_")));

        //将要提交给API的数据对象转换成XML格式数据Post给API
        String postDataXML = xStreamForRequestPostData.toXML(xmlObj);
        //得指明使用UTF-8编码，否则到API服务器XML的中文不能被成功识别
        StringEntity postEntity = new StringEntity(postDataXML, "UTF-8");
        httpPost.addHeader("Content-Type", "text/xml");
        httpPost.setEntity(postEntity);

        RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(socketTimeout).setConnectTimeout(connectTimeout).build();
        //设置请求器的配置
        httpPost.setConfig(requestConfig);

        try {
            HttpResponse response = httpClient.execute(httpPost);

            HttpEntity entity = response.getEntity();

            result = EntityUtils.toString(entity, "UTF-8");

        } catch (ConnectionPoolTimeoutException e) {
            logger.error("http get throw ConnectionPoolTimeoutException(wait time out)");
            logger.error("error: {}", e.getMessage());
            logger.error("detail:{}", e.getStackTrace());
        } catch (ConnectTimeoutException e) {
            logger.error("http get throw ConnectTimeoutException");
            logger.error("error: {}", e.getMessage());
            logger.error("detail:{}", e.getStackTrace());
        } catch (SocketTimeoutException e) {
            logger.error("http get throw SocketTimeoutException");
            logger.error("error: {}", e.getMessage());
            logger.error("detail:{}", e.getStackTrace());
        } catch (Exception e) {
            logger.error("http get throw Exception");
            logger.error("error: {}", e.getMessage());
            logger.error("detail:{}", e.getStackTrace());
        } finally {
            httpPost.abort();
        }

        return result;
    }
}
