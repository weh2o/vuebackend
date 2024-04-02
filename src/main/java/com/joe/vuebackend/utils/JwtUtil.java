package com.joe.vuebackend.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.joe.vuebackend.bean.HttpResult;

import java.util.Date;

/**
 * JWT工具類
 */
public class JwtUtil {

    /**
     * 過期時間: 5分鐘
     */
    private static final long EXPIRE_TIME = 1 * 60 * 1000;


    private static final long EXPIRE_TIME_1_DAY = 1 * 24 * 60 * 60 * 1000;

    private static final long EXPIRE_TIME_7_DAY = 7 * 24 * 60 * 60 * 1000;

    /**
     * jwt 密鑰
     */
    private static final String SECRET = "jwt_secret";

    /**
     * 生成簽名(token)，五分鐘後過期
     * @param userId
     * @return
     */
    public static String sign(String userId) {
        try {
            // 當前時間 + 5分鐘
            Date date = new Date(System.currentTimeMillis() + EXPIRE_TIME_1_DAY);
            // 設置密鑰，並選擇加密方式
            Algorithm algorithm = Algorithm.HMAC256(SECRET);
            // 生成token
            return JWT.create()
                    // 將 user id 保存到 token 裡面
                    .withAudience(userId)
                    // 五分鐘後token過期
                    .withExpiresAt(date)
                    // token 的密鑰
                    .sign(algorithm);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 根據token獲取userId
     * @param token
     * @return
     */
    public static String getUserId(String token) {
        try {
            // 據token獲取userId
            String userId = JWT.decode(token).getAudience().get(0);
            return userId;
        } catch (JWTDecodeException e) {
            return null;
        }
    }

    /**
     * 校驗token
     * @param token
     * @return
     */
    public static HttpResult<String> checkSign(String token) {
        // 如果以下代碼沒出現異常代表token是相同的
        try {
            // 根據指定密鑰、加密方式，創建驗證對象
            Algorithm algorithm = Algorithm.HMAC256(SECRET);
            JWTVerifier verifier = JWT.require(algorithm).build();
            // 驗證token，如果不相同會出現異常
            DecodedJWT jwt = verifier.verify(token);
            // 獲取token中payload保存的資料。 注意: 要調用對應類型的asXxx方法
            System.out.println(jwt.getClaim("userId").asString());
            System.out.println(jwt.getClaim("username").asString());
            // 獲取過期時間
            System.out.println(jwt.getExpiresAt());
            return HttpResult.success("token 有效");
        } catch (JWTVerificationException exception) {
            return HttpResult.fail("token 無效");
        }
    }
}
