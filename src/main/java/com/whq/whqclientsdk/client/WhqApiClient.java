package com.whq.whqclientsdk.client;

import cn.hutool.core.util.RandomUtil;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONUtil;
import com.whq.whqclientsdk.model.User;


import java.util.HashMap;
import java.util.Map;

import static com.whq.whqclientsdk.utils.SignUtils.getSign;


/**
 * @author: whq
 * @description: 调用第三方接口的客户端
 * @time: 2023/3/21 20:52
 */
public class WhqApiClient {

    private String accessKey;

    private String secretKey;

    public WhqApiClient(String accessKey, String secretKey) {
        this.accessKey = accessKey;
        this.secretKey = secretKey;
    }

    public String getNameByGet(String name) {
        //可以单独传入http参数，这样参数会自动做URL编码，拼接在URL中
        HashMap<String, Object> paramMap = new HashMap<>();
        paramMap.put("name", "whq");
        String result = HttpUtil.get("http://localhost:8081/api/name/", paramMap);
        System.out.println(result);
        return result;
    }

    public String getNameByPost(String name) {
        HashMap<String, Object> paramMap = new HashMap<>();
        paramMap.put("name", "whq");
        String result = HttpUtil.post("http://localhost:8081/api/name/", paramMap);
        System.out.println(result);
        return result;
    }

    // 添加头信息sign
    public Map<String, String> getHeaders(String body) {
        Map<String, String> hashmap = new HashMap<>();
        hashmap.put("accessKey", accessKey);
        hashmap.put("nonce", RandomUtil.randomString(10));
        hashmap.put("body", body);
        hashmap.put("timestamp", String.valueOf(System.currentTimeMillis() / 1000));
        // 添加sign
        hashmap.put("sign", getSign(body, secretKey));
        return hashmap;
    }

    // 代理请求User接口
    public String getUserNameByPost(User user) {
        String json = JSONUtil.toJsonStr(user);
        HttpResponse result = HttpRequest.post("http://localhost:8081/api/name/user")
                .addHeaders(getHeaders(json))
                .body(json)
                .execute();
        System.out.println(result.getStatus());
        System.out.println(result.body());
        return result.body();
    }
}
