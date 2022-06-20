package Behavioral.State;

public final class MyPair<U, V>
{
    private final U first;       // the first field of a pair
    private final V second;      // the second field of a pair

    // Constructs a new pair with specified values
    private MyPair(U first, V second)
    {
        this.first = first;
        this.second = second;
    }

    @Override
    // Checks specified object is "equal to" the current object or not
    public boolean equals(Object o)
    {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        MyPair<?, ?> myPair = (MyPair<?, ?>) o;

        // call `equals()` method of the underlying objects
        if (!first.equals(myPair.first)) {
            return false;
        }
        return second.equals(myPair.second);
    }

    @Override
    // Computes hash code for an object to support hash tables
    public int hashCode()
    {
        // use hash codes of the underlying objects
        return 31 * first.hashCode() + second.hashCode();
    }

    @Override
    public String toString() {
        return "(" + first + ", " + second + ")";
    }

    public U getFirst() {
        return first;
    }

    public V getSecond() {
        return second;
    }

    // Factory method for creating a typed Pair immutable instance
    public static <U, V> MyPair<U, V> of(U a, V b)
    {
        // calls private constructor
        return new MyPair<>(a, b);
    }
}
