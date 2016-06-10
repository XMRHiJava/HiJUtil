package hij.util.generic;

/**
 * 在匿名类中调用局部变量并赋值处理
 * @author XuminRong
 *
 * @param <T> 要访问局部变量的类型
 */
public class HiResult<T>  {
	public boolean success() {
		return isOK;
	}

	public void success(boolean isSuccess) {
		isOK = isSuccess;		
	}

	public T get() {
		return t;
	}

	public void set(T t) {
		this.t = t;
	}
	
	boolean isOK = false;
	T t;
}
