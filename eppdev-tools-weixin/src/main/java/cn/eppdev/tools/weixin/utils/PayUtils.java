package cn.eppdev.tools.weixin.utils;

import cn.eppdev.tools.weixin.commons.RandomStringGenerator;
import cn.eppdev.tools.weixin.data.PayReqData;
import com.thoughtworks.xstream.XStream;
import cn.eppdev.tools.weixin.commons.Signature;
import cn.eppdev.tools.weixin.data.PayResData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * Created by haojinlong on 16-4-4.
 */
public class PayUtils {

    static Logger logger = LoggerFactory.getLogger(PayUtils.class);

    /**
     * 统一下单
     * @param reqData
     * @return
     */
    public static String createOrder(PayReqData reqData){
        logger.debug("reqData: {}", reqData);
        HttpRequest httpRequest  = new HttpRequest();
        String reqString = httpRequest.sendPost(WeixinConfigReader.ORDER_URL, reqData);
        return reqString;
    }


    /**
     * 创建订单
     * @param total_fee 订单价格，单位为分
     * @param out_trade_no
     * @param body
     * @param notify_url
     * @param openid
     * @return
     */
    public static PayResData createOrder(int total_fee,
                                         String out_trade_no,
                                         String body,
                                         String notify_url,
                                         String openid){
        //设置参数
        PayReqData payReqData = new PayReqData();
        // appid
        payReqData.setAppid(WeixinConfigReader.getInstance().getAppid());

        // 商户id
        payReqData.setMch_id(WeixinConfigReader.getInstance().getMchid());

        // 随机数
        payReqData.setNonce_str(RandomStringGenerator.getRandomString());

        // 价格
        payReqData.setTotal_fee(total_fee);

        // 自定义订单号
        payReqData.setOut_trade_no(out_trade_no);

        // 订单内容
        payReqData.setBody(body);

        // 通知url
        try {
            payReqData.setNotify_url(URLEncoder.encode(notify_url, "UTF-8"));
        } catch (UnsupportedEncodingException e) {
            logger.error("error:{}\n{}", e.getMessage(), e.getStackTrace());
        }

        // openid
        payReqData.setOpenid(openid);

        // 签名
        payReqData.setSign(Signature.getSign(payReqData.toMap()));

        logger.debug("reqData: {}", payReqData);

        // 调用接口进行处理
        String resString = createOrder(payReqData);

        logger.debug("resString:{}", resString);

        try {
            if(Signature.checkIsSignValidFromResponseString(resString)){
                XStream xStream = new XStream();
                xStream.alias("xml", PayResData.class);
                xStream.ignoreUnknownElements();;
                PayResData payResData = (PayResData)xStream.fromXML(resString);
                logger.debug("resData:{}", payResData);
                return payResData;
            }
        } catch (ParserConfigurationException e) {
            logger.error("error:{}\n{}", e.getMessage(), e.getStackTrace());
        } catch (IOException e) {
            logger.error("error:{}\n{}", e.getMessage(), e.getStackTrace());
        } catch (SAXException e) {
            logger.error("error:{}\n{}", e.getMessage(), e.getStackTrace());
        }

        return null;
    }

}
