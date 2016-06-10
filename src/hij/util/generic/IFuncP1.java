package hij.util.generic;

/**
 * 单参有返回值接口
 * @author XuminRong
 *
 * @param <P>
 * @param <T>
 * 
 */
public interface IFuncP1<P, T> {
	public T handle(P p);
}
