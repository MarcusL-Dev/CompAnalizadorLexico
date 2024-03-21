package ufc.common.helpers;

public class Tuple2<T1, T2> {

	public final T1 Item1;
	public final T2 Item2;

	public Tuple2(T1 item1, T2 item2) {
		this.Item1 = item1;
		this.Item2 = item2;
	}

	public final T1 getItem1() {
		return Item1;
	}

	public final T2 getItem2() {
		return Item2;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == this) {
			return true;
		}
		else if (!(obj instanceof Tuple2<?, ?>)) {
			return false;
		}

		Tuple2<?, ?> other = (Tuple2<?, ?>)obj;
		return Tuple.equals(this.Item1, other.Item1)
			&& Tuple.equals(this.Item2, other.Item2);
	}

	@Override
	public int hashCode() {
		int hash = 5;
		hash = 79 * hash + (this.Item1 != null ? this.Item1.hashCode() : 0);
		hash = 79 * hash + (this.Item2 != null ? this.Item2.hashCode() : 0);
		return hash;
	}

	@Override
	public String toString() {
		return String.format("(%s, %s)", Item1, Item2);
	}

}
