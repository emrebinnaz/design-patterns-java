package Behavioral.State;

//Every state is class.

class State {
    void on(LightSwitch ls) {

        System.out.println("Light is already on");
    }
    void off(LightSwitch ls) {

        System.out.println("Light is already off");
    }
}

class LightSwitch{
    private State state; // onState or offState

    public LightSwitch() {
        state = new OffState(); // it offs at the beginning.
    }

    void on() {
        state.on(this);
    }

    void off() {
        state.off(this);
    }

    void setState(State st) {
        this.state = st;
    }
}

class OnState extends State {

    public OnState() {
        System.out.println("Light turned on");
    }

    @Override
    void off(LightSwitch ls) {
        System.out.println("switching light off..");
        ls.setState(new OffState());
    }
}

class OffState extends State {

    public OffState() {
        System.out.println("Light turned off");
    }

    @Override
    void on(LightSwitch ls) {
        System.out.println("switching light on..");
        ls.setState(new OnState());
    }

}
public class ClassicImplementation {

    public static void main(String[] args) {
        LightSwitch lightSwitch = new LightSwitch();
        lightSwitch.on();
        lightSwitch.off();
        lightSwitch.off(); // when it is already closed, it invokes to "off" method of base class.
    }
}
