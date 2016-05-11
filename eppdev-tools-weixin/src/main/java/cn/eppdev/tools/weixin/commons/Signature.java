package cn.eppdev.tools.weixin.commons;

import cn.eppdev.tools.commons.encrypt.StandardEncoder;
import cn.eppdev.tools.weixin.utils.WeixinConfigReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;
import java.util.Objects;

/**
 * @author 郝金隆
 * @since 16-4-4
 */
public class Signature {

    private static Logger logger = LoggerFactory.getLogger(Signature.class);

    public static String getSign(Map<String,Object> map){
        ArrayList<String> list = new ArrayList<>();
        for(Map.Entry<String,Object> entry:map.entrySet()){
            if(entry.getValue()!=""){
                list.add(entry.getKey() + "=" + entry.getValue() + "&");
            }
        }
        int size = list.size();
        String [] arrayToSort = list.toArray(new String[size]);
        Arrays.sort(arrayToSort, String.CASE_INSENSITIVE_ORDER);
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < size; i ++) {
            sb.append(arrayToSort[i]);
        }
        String result = sb.toString();
        result += "key=" + WeixinConfigReader.getInstance().getKey();

        logger.debug("Sign Before MD5: {}", result);
        //Util.log("Sign Before MD5:" + result);
        result = StandardEncoder.MD5Encode(result).toUpperCase();
        //Util.log("Sign Result:" + result);
        return result;
    }


    /**
     * 检验API返回的数据里面的签名是否合法，避免数据在传输的过程中被第三方篡改
     * @param responseString API返回的XML数据字符串
     * @return API签名是否合法
     * @throws ParserConfigurationException
     * @throws IOException
     * @throws SAXException
     */
    public static boolean checkIsSignValidFromResponseString(String responseString) throws ParserConfigurationException, IOException, SAXException {

        Map<String,Object> map = XMLParser.getMapFromXML(responseString);

        String signFromAPIResponse = map.get("sign").toString();
        if(Objects.equals(signFromAPIResponse, "") || signFromAPIResponse == null){
            return false;
        }
        //清掉返回数据对象里面的Sign数据（不能把这个数据也加进去进行签名），然后用签名算法进行签名
        map.put("sign","");
        //将API返回的数据根据用签名算法进行计算新的签名，用来跟API返回的签名进行比较
        String signForAPIResponse = Signature.getSign(map);

        return signForAPIResponse.equals(signFromAPIResponse);
    }

}
