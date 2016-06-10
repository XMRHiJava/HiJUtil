package hij.util;

import java.lang.reflect.Method;

import hij.util.generic.IFuncP1;
import hij.util.generic.IFuncP2;

/**
 * 根据数据填充对象
 * @author 徐敏荣
 *
 */
public class HiCBO {	
	
    /**
     * 根据回调填充对象
     * @param obj
     * @param type
     * @param handler
     * @return
     */
    public static <T> boolean fillObject(T obj, Class<T> type, IFuncP1<String, String> handler)  {
    	return fillObject(obj, type, new IFuncP2<Class<?>, String, Object>(){
    		public Object handle(Class<?> cls, String property) {
    			return handler.handle(property);
    		}
    	});
    }

	
    /**
     * 根据回调填充对象
     * @param obj
     * @param type
     * @param handler
     * @return
     */
    public static <T> boolean fillObjectEx(T obj, Class<T> type, IFuncP1<String, Object> handler)   {
    	return fillObject(obj, type, new IFuncP2<Class<?>, String, Object>(){
    		public Object handle(Class<?> cls, String property) {
    			return handler.handle(property);
    		}
    	});
    }  
    
    /**
     * 根据提供数据装载对象
     * @param obj
     * @param type
     * @param handler
     * @return
     */
    private static <T> boolean fillObject(T obj, Class<T> type, IFuncP2<Class<?>, String, Object> handler)   {
    	if (obj == null || handler == null) {
    		return false;
    	}
    	
    	Method[] methods = type.getMethods();
    	
    	for (int i = 0; i < methods.length; i++) {
    		Method method = methods[i];
    		String name = method.getName();
    		if (!name.startsWith("set")) {
    			continue;
    		}
    		
    		if (method.getParameters().length != 1) {
    			continue;
    		}
    		
    		Class<?> cls = method.getParameterTypes()[0];
    		if (cls == null) {
    			continue;
    		}
    		String property = name.substring(3, name.length());
    		
    		try {     
        		Object value = handler.handle(cls, property);  
    			Object ret =  HiTypeHelper.cast(cls, value);
				method.invoke(obj, ret); 			
    		} catch(Exception ex) {
    			ex.printStackTrace();
    		}
    	}
        return true;
    }
 }
