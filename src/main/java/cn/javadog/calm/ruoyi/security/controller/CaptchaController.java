package cn.javadog.calm.ruoyi.security.controller;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import cn.javadog.calm.ruoyi.security.constant.Constants;
import cn.javadog.calm.ruoyi.security.redis.RedisCache;
import cn.javadog.calm.ruoyi.security.utils.AjaxResult;
import cn.javadog.calm.ruoyi.security.utils.Base64;
import cn.javadog.calm.ruoyi.security.utils.IdUtils;
import cn.javadog.calm.ruoyi.security.utils.VerifyCodeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 余勇
 * @date 2020-01-04 21:20
 *
 * 验证码操作处理
 */
@RestController
public class CaptchaController {
    @Autowired
    private RedisCache redisCache;

    /**
     * 生成验证码
     * 生成的base64字符串加上 data:image/jpg;base64, 开头即可
     */
    @GetMapping("/captchaImage")
    public AjaxResult getCode(HttpServletResponse response) throws IOException {
        // 生成随机字串
        String verifyCode = VerifyCodeUtils.generateVerifyCode(4);
        // 唯一标识
        String uuid = IdUtils.simpleUUID();
        String verifyKey = Constants.CAPTCHA_CODE_KEY + uuid;

        redisCache.setCacheObject(verifyKey, verifyCode, Constants.CAPTCHA_EXPIRATION, TimeUnit.MINUTES);
        // 生成图片
        int w = 111, h = 36;
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        VerifyCodeUtils.outputImage(w, h, stream, verifyCode);
        try {
            AjaxResult ajax = AjaxResult.success();
            ajax.put("uuid", uuid);
            ajax.put("img", Base64.encode(stream.toByteArray()));
            return ajax;
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.error(e.getMessage());
        } finally {
            stream.close();
        }
    }
}
