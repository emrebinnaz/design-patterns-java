package SOLID.OpenClosedPrinciple.Correct;

public interface Specification<T>{

    boolean isSatisfied(T t);
}
