package hij.util.generic;

/**
 * ���������е��þֲ���������ֵ����
 * @author XuminRong
 *
 * @param <T> Ҫ���ʾֲ�����������
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
