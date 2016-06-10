package hij.util.generic;

/**
 * 双参有返回值接口
 * @author XuminRong
 *
 * @param <P1>
 * @param <P2>
 * @param <T>
 */
public interface IFuncP2<P1, P2, T> {
	public T handle(P1 p1, P2 p2);
}
