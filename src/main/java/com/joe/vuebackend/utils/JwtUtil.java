package com.joe.vuebackend.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.joe.vuebackend.vo.UserInfo;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

/**
 * JWT工具類
 */
@Component
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
     * 創建JwtToken
     *
     * @param userInfo    使用者資料物件
     * @param userInfoStr 使用者資料字串類型
     * @return
     */
    public static String createJwt(UserInfo userInfo, String userInfoStr) {
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
                    .withClaim("userInfo", userInfoStr)
                    // token過期時間
                    .withExpiresAt(date)
                    // token 的密鑰
                    .sign(algorithm);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 驗驗token
     *
     * @param token JWT Token
     * @return 驗證結果
     */
    public boolean verifyToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(SECRET);
            JWTVerifier verifier = JWT.require(algorithm).build();
            DecodedJWT jwt = verifier.verify(token);
            return true;
        } catch (JWTVerificationException exception) {
            return false;
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
     * 根據token獲取用戶資料
     *
     * @param token Jwt Token
     * @return 用戶資料
     */
    public String getUserInfo(String token) {
        try {
            return JWT.decode(token).getClaim("userInfo").asString();
        } catch (JWTDecodeException e) {
            return null;
        }
    }

    /**
     * 根據token獲取權限列表
     *
     * @param token Jwt Token
     * @return 權限列表
     */
    public List<String> getUserAuth(String token) {
        try {
            return JWT.decode(token).getClaim("userAuth").asList(String.class);
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
