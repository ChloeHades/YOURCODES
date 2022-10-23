 package com.example.utils;

/**
 * @Author frankie
 * @Date2022/10/7 10:05
 * @PackageName: ${PACKAGE.NAME}
 * @Description:
 * @Version 1.0
 */

import cn.hutool.core.codec.Base64;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import okhttp3.*;


import java.io.IOException;
import java.math.BigInteger;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class ApiRequestDemo {
    static final String APP_ID = "1183";
    static final String APP_SECRET = "0aaa176cf1a489cbc2dc07bd346e7ad5";

    public static JsonResult getcode(String code,String lang) throws IOException {
        if(lang.equals("java")){
            lang=  Language.JVAV;
        }else if(lang.equals("c")){
            lang=  Language.C;
        }else{
            lang =Language.PYTHON;
        }

        String input= "";
        String sign = sign(code,input);

        FormBody body = new FormBody.Builder()
                .add("appId", APP_ID)
                .add("sign", sign)
                .add("lang", lang)
                .add("code", URLEncoder.encode(Base64.encode(code)))
                .add("input", URLEncoder.encode(Base64.encode(input)))
                .build();
        String result = post("http://jsrun.net/api/run/v2", body);
        JSONObject responseJSON = JSONUtil.parseObj(result);
        JsonResult jsonResult= JSONUtil.toBean(responseJSON, JsonResult.class);

        return jsonResult;
    }
    static String sign(String code,String input) {
        return md5(code + input + APP_SECRET);
    }

    static String md5(String str) {

        try {
            byte[] secretBytes = null;
            secretBytes = MessageDigest.getInstance("md5").digest(str.getBytes());

            String md5code = new BigInteger(1, secretBytes).toString(16);
            for (int i = 0; i < 32 - md5code.length(); i++) {
                md5code = "0" + md5code;
            }
            return md5code;
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("There is no md5 algorithm!");
        }
    }

    static String post(String url, RequestBody body) throws IOException {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(url).post(body).build();

        try {
            Response response = client.newCall(request).execute();
            if (response.isSuccessful()) {
                return response.body().string();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
