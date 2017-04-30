package el.onetoone.back;

/**
 * 一个二元元组，好用的容器
 * @author iznauy
 *
 * @param <A>
 * @param <B>
 */
public class TwoTuple<A, B> {
	
	public A first;
	public B second;
	
	public TwoTuple(A a, B b) {
		first = a;
		second = b;
	}
}
