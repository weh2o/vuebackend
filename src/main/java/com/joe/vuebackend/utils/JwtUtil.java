package com.joe.vuebackend.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.joe.vuebackend.bean.HttpResult;
import com.joe.vuebackend.vo.UserInfo;

import java.util.Date;
import java.util.List;

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
     * 生成簽名(token)，一天後過期
     *
     * @param userId 使用者識別碼
     * @return 密鑰token
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
                    .withClaim("identity", "xx")
                    // token過期時間
                    .withExpiresAt(date)
                    // token 的密鑰
                    .sign(algorithm);
        } catch (Exception e) {
            return null;
        }
    }

    public static String sign(UserInfo userInfo) {
        try {
            // 當前時間 + 5分鐘
            Date date = new Date(System.currentTimeMillis() + EXPIRE_TIME_1_DAY);
            // 設置密鑰，並選擇加密方式
            Algorithm algorithm = Algorithm.HMAC256(SECRET);
            // 生成token
            return JWT.create()
                    // 將 user id 保存到 token 裡面
                    .withAudience(userInfo.getId())
                    .withClaim("identity", userInfo.getIdentity())
                    .withClaim("roles", userInfo.getRoles())
                    // token過期時間
                    .withExpiresAt(date)
                    // token 的密鑰
                    .sign(algorithm);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 校驗token
     *
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
            return HttpResult.success("token 有效");
        } catch (JWTVerificationException exception) {
            return HttpResult.fail("token 無效");
        }
    }

    /**
     * 根據token獲取userId
     *
     * @param token
     * @return 使用者識別碼
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
     * 獲取Identity身分
     *
     * @param token
     * @return
     */
    public static String getIdentity(String token) {
        try {
            return JWT.decode(token).getClaim("identity").asString();
        } catch (JWTDecodeException e) {
            return null;
        }
    }

    /**
     * 獲取角色權限英文名List
     *
     * @param token
     * @return
     */
    public static List<String> getRolesName(String token) {
        try {
            String rolesStr = JWT.decode(token).getClaim("roles").asString();
            return RoleHelper.splitRolesStr(rolesStr);
        } catch (JWTDecodeException e) {
            return null;
        }
    }
}
