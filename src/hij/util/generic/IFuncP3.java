package hij.util.generic;

/**
 * 三参有返回值接口
 * @author XuminRong
 *
 * @param <P1>
 * @param <P2>
 * @param <P3>
 * @param <T>
 */
public interface IFuncP3<P1, P2, P3, T> {
	public T handle(P1 p1, P2 p2, P3 p3);
}
