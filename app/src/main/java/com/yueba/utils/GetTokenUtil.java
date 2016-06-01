package com.yueba.utils;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.security.MessageDigest;

import org.apache.commons.codec.binary.Hex;
import org.json.JSONObject;


import com.yueba.constant.AppConstant;

import javax.net.ssl.HttpsURLConnection;

/**
 * 获取融云的token
 */
public class GetTokenUtil {

    public static String getUserToken(String id, String name, String avatarUrl) {

        String token = "";
        String appKey = AppConstant.RONG_YUN_APP_KEY;
        String appScret = AppConstant.RONG_YUN_APP_SECRET;
        String url = "https://api.cn.ronghub.com/user/getToken.json";
        //随机数
        String nonce = String.valueOf(Math.random() * 1000000);
        //时间戳
        String timestamp = String.valueOf(System.currentTimeMillis() / 1000);
        StringBuilder toSign = new StringBuilder(appScret).append(nonce)
                .append(timestamp);
        String signature = generateSignature(toSign.toString());


        try {
            URL myURL = new URL(url);
            HttpsURLConnection myURLConnection = (HttpsURLConnection) myURL.openConnection();
            myURLConnection.setRequestMethod("POST");
            myURLConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            myURLConnection.setRequestProperty("App-Key", appKey);
            myURLConnection.setRequestProperty("Nonce", nonce);
            myURLConnection.setRequestProperty("Timestamp", timestamp);
            myURLConnection.setRequestProperty("Signature", signature);
            myURLConnection.setUseCaches(false);
            myURLConnection.setDoInput(true);
            myURLConnection.setDoOutput(true);
            myURLConnection.setConnectTimeout(10000);
            myURLConnection.setReadTimeout(5000);
            //传递参数
            String param = "userId=" + id + "&name=" + name + "&portraitUri=" + avatarUrl;
            OutputStream os = myURLConnection.getOutputStream();
            os.write(param.getBytes());

            myURLConnection.connect();
            int code = myURLConnection.getResponseCode();
            if (code == 200) {
                int len = 0;
                byte[] data = new byte[1024];
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                InputStream is = myURLConnection.getInputStream();

                while ((len = is.read(data)) != -1) {
                    baos.write(data, 0, len);
                }

                //解析服务器返回的json数据
                JSONObject jsonObject = new JSONObject(new String(
                        baos.toByteArray()));
                token = jsonObject.getString("token");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return token;

    }

    //将appSecret nonce timestamp 拼接的字符串sha1加密得到signature

    private static String generateSignature(String value) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-1");
            md.update(value.getBytes("utf-8"));
            byte[] digest = md.digest();
            return String.valueOf(Hex.encodeHex(digest));
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }


}
