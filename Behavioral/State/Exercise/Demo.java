package Behavioral.State.Exercise;

enum Status{

    LOCKED,
    OPEN,
    ERROR
}
class CombinationLock
{
    private int [] combination;

    public String status;
    private int digitsEntered = 0;
    private boolean failed = false;

    public CombinationLock(int[] combination)
    {
        this.combination = combination;
        reset();
    }

    private void reset()
    {
        status = Status.LOCKED.name();
        digitsEntered = 0;
        failed = false;
    }

    public void enterDigit(int digit)
    {
        if (status.equals(Status.LOCKED.name()))
            status = "";

        status += digit;
        if (combination[digitsEntered] != digit) {
            failed = true;
        }
        digitsEntered++;

        if (digitsEntered == combination.length)
            status = failed ? Status.ERROR.name() : Status.OPEN.name();
    }
}
public class Demo {
    public static void main(String[] args) {
        CombinationLock cl = new CombinationLock(new int[]{1, 2, 3, 4});
        System.out.println( cl.status);

        cl.enterDigit(1);
        System.out.println( cl.status);

        cl.enterDigit(2);
        System.out.println( cl.status);

        cl.enterDigit(3);
        System.out.println( cl.status);

        cl.enterDigit(4);
        System.out.println( cl.status);
    }
}
