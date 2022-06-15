package Behavioral.Iterator.Exercise;

import java.util.ArrayList;
import java.util.List;

interface Iterator<T> {
    T getCurrent();
    boolean hasNext();
    T getNext();
}
interface Creator<T> {
    Iterator<T> createIterator();
}
class Lesson {
    String name;
    String code;

    public Lesson(String name, String code) {
        this.name = name;
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
class LessonCreator implements Creator<Lesson> {

    List<Lesson> lessonList = new ArrayList<>();

    public void addLesson(Lesson l) {
        lessonList.add(l);
    }

    @Override
    public Iterator<Lesson> createIterator() {
        return new LessonIterator(this);
    }
}

class LessonIterator implements Iterator<Lesson> {

    private LessonCreator lessonCreator;
    private int index = -1;
    private Lesson current;

    public LessonIterator(LessonCreator lessonCreator) {
        this.lessonCreator = lessonCreator;
    }

    @Override
    public Lesson getCurrent() {
        return current;
    }

    @Override
    public boolean hasNext() {
        return lessonCreator.lessonList.size() > index + 1;
    }

    @Override
    public Lesson getNext() {
            index++;
            current = lessonCreator.lessonList.get(index);
            return current;
    }
}
public class LessonIteratorDemo {
    public static void main(String[] args) {
        LessonCreator lessonCreator = new LessonCreator();
        lessonCreator.addLesson(new Lesson("Math","m1001"));
        lessonCreator.addLesson(new Lesson("Physic", "p1001"));

        final Iterator<Lesson> iterator = lessonCreator.createIterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.getNext().getCode());

        }

    }
}
