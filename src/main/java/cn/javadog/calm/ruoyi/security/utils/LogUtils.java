package cn.javadog.calm.ruoyi.security.utils;

/**
 * @author 余勇
 * @date 2020-01-04 22:11
 *
 * 处理并记录日志文件
 */
public class LogUtils
{
    public static String getBlock(Object msg)
    {
        if (msg == null)
        {
            msg = "";
        }
        return "[" + msg.toString() + "]";
    }
}
