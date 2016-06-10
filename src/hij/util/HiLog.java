package hij.util;

import hij.util.generic.IActionP2;

/**
 * ��־��
 * @author XuminRong
 *
 */
public final class HiLog {
    /// <summary>
    /// ��־ö��
    /// </summary>
    public enum LogType {
        LT_Info,
        LT_Debug,
        LT_Alarm,
        LT_Error
    }
    
    /**
     * ������־�ľ���ʵ��
     * @param logfun
     */
    public static void setLogFun(IActionP2<String, Integer> logfun)  {
        onlog = logfun;
    }
    
    /**
     * д��־
     * @param script
     * @param type
     */
    public static void write(String script, int type) {
        if (onlog != null) {
            onlog.action(script, type);
        }
    }
    
    /**
     * 
     * @param script
     */
    public static void write(String script) {
    	write(script, 1);
    }
    public static void debug(String script) {
    	write(script, 1);
    }
    
    public static void info(String script){
    	write(script, 0);
    }
    public static void alarm(String script) {
    	write(script, 2);
    }
    public static void error(String format, Object...args){
    	write(String.format(format, args), 3);
    }
    private static IActionP2<String, Integer> onlog = null;
}
