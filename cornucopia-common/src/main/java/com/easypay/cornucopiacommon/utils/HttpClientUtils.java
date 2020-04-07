package com.easypay.cornucopiacommon.utils;


import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpStatus;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.security.GeneralSecurityException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@Slf4j
@Service
public class HttpClientUtils {
    private static PoolingHttpClientConnectionManager connMgr;
    private static RequestConfig requestConfig;
    private static final int MAX_TIMEOUT = 600000;

    @Value("${base_url}")
    String baseUrl ;
//
//    static {
//        Registry<ConnectionSocketFactory> socketFactoryRegistry = RegistryBuilder.<ConnectionSocketFactory>create()
//                .register("http", PlainConnectionSocketFactory.INSTANCE)
//                .register("https", createSSLConnSocketFactory())
//                .build();
//        // 设置连接池
//        connMgr = new PoolingHttpClientConnectionManager(socketFactoryRegistry);
//        // 设置连接池大小
//        connMgr.setMaxTotal(100);
//        connMgr.setDefaultMaxPerRoute(connMgr.getMaxTotal());
//        RequestConfig.Builder configBuilder = RequestConfig.custom();
//        // 设置连接超时
//        configBuilder.setConnectTimeout(MAX_TIMEOUT);
//        // 设置读取超时
//        configBuilder.setSocketTimeout(MAX_TIMEOUT);
//        // 设置从连接池获取连接实例的超时
//        configBuilder.setConnectionRequestTimeout(MAX_TIMEOUT);
//        // 在提交请求之前 测试连接是否可用
//        configBuilder.setStaleConnectionCheckEnabled(true);
//        requestConfig = configBuilder.build();
//    }


    /**
     * 发送 POST 请求
     *
     * @param url API接口URL
     * @param paramsMap 参数map
     * @return
     */
    public  JSONObject doPost(String url, Map<String,String> paramsMap) {
        url = baseUrl+url;
        log.info("调用通道请求地址：{}",url);
        log.info("调用通道请求参数：{}",paramsMap);
        CloseableHttpClient httpclient = HttpClients.custom().setConnectionManager(connMgr).setDefaultRequestConfig(requestConfig).build();
        HttpPost httpPost = new HttpPost(url);
        CloseableHttpResponse response = null;
        String out = null;
        JSONObject jsonObject = null;//接收结果
        try {
            httpPost.setConfig(requestConfig);

            //        表单方式
            List<BasicNameValuePair> pairList = new ArrayList<BasicNameValuePair>(2);
            for (String key:paramsMap.keySet()){
                pairList.add(new BasicNameValuePair(key, paramsMap.get(key)));
            }
            httpPost.setEntity(new UrlEncodedFormEntity(pairList, "utf-8"));

            response = httpclient.execute(httpPost);
            int statusCode = response.getStatusLine().getStatusCode();
            if (statusCode != HttpStatus.SC_OK) {
                return null;
            }
            out = EntityUtils.toString(response.getEntity(), "utf-8");
            jsonObject = JSONObject.parseObject(out);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (httpPost != null) {
                httpPost.releaseConnection();
            }
        }
        log.info("调用通道应答结果：{}",jsonObject);
        return jsonObject;
    }
//
//    /**
//     * 创建SSL安全连接
//     *
//     * @return
//     */
//    private static SSLConnectionSocketFactory createSSLConnSocketFactory() {
//        SSLConnectionSocketFactory sslsf = null;
//        try {
//            SSLContext ctx = SSLContext.getInstance("SSL");
//            X509TrustManager tm = new X509TrustManager() {
//                @Override
//                public void checkClientTrusted(X509Certificate[] chain,
//                                               String authType) throws CertificateException {
//                }
//
//                @Override
//                public void checkServerTrusted(X509Certificate[] chain,
//                                               String authType) throws CertificateException {
//                }
//
//                @Override
//                public X509Certificate[] getAcceptedIssuers() {
//                    return null;
//                }
//            };
//            ctx.init(null, new TrustManager[]{tm}, null);
//            sslsf = new SSLConnectionSocketFactory(ctx, SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
//        } catch (GeneralSecurityException e) {
//            e.printStackTrace();
//        }
//        return sslsf;
//    }
}
