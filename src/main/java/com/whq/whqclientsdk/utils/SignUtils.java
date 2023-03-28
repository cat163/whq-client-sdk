package com.whq.whqclientsdk.utils;

import cn.hutool.crypto.digest.DigestAlgorithm;
import cn.hutool.crypto.digest.Digester;

import java.util.Map;

/**
 * @author: whq
 * @description: 生成签名工具
 * @time: 2023/3/21 22:40
 */
public class SignUtils {
    /**
     * 生成签名
     * @param body
     * @param secretKey
     * @return
     */
    public static String getSign(String body, String secretKey) {
        Digester md5 = new Digester(DigestAlgorithm.SHA256);
        String contend = body + "." + secretKey;
        String digestHex = md5.digestHex(contend);
        return digestHex;
    }
}
