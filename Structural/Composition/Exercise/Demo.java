package Structural.Composition.Exercise;

import java.util.*;
import java.util.function.Consumer;

public class Demo {

    interface ValueContainer extends Iterable<Integer> {
        int sum();
    }

    class SingleValue implements ValueContainer
    {
        public int value;

        public SingleValue(int value)
        {
            this.value = value;
        }

        @Override
        public Iterator<Integer> iterator() {
            return Collections.singleton(value).iterator();
        }

        @Override
        public void forEach(Consumer<? super Integer> action) {
            ValueContainer.super.forEach(action);
        }

        @Override
        public Spliterator<Integer> spliterator() {
            return ValueContainer.super.spliterator();
        }

        @Override
        public int sum() {
            return value;
        }
    }

    class ManyValues extends ArrayList<Integer> implements ValueContainer
    {


        @Override
        public int sum() {
            return this.stream().mapToInt(integer -> integer).sum();
        }
    }

    class MyList extends ArrayList<ValueContainer>
    {
        public MyList(Collection<? extends ValueContainer> c)
        {
            super(c);
        }

        public int sum()
        {

            final List<ValueContainer> containerList = new ArrayList<>(this);
            int sum = 0;
            for(ValueContainer valueContainer : containerList){
                sum += valueContainer.sum();
            }

            return sum;
        }
    }
}

/* Composite pattern is a partitioning design pattern and describes a group of objects that is treated the same way as a single instance of the same type of object.
The intent of a composite is to “compose” objects into tree structures to represent part-whole hierarchies.
It allows you to have a tree structure and ask each node in the tree structure to perform a task.*/
