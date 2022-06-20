package Behavioral.State;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

enum PhoneState {
    OFF_HOOK, // starting to phone call
    ON_HOOK, // stop the process, place phone on the hook
    CONNECTING,
    CONNECTED,
    ON_HOLD
}

enum Trigger {
    CALL_DIALED,
    HUNG_UP,
    CALL_CONNECTED,
    PLACED_ON_HOLD,
    TAKEN_OFF_HOLD,
    LEFT_MESSAGE,
    STOP_USING_PHONE
}

// We need rules for state machine
public class HandmadeStateMachine {
    private static Map< PhoneState, List <MyPair<Trigger, PhoneState>> > rules = new HashMap<>();
    static {
        rules.put(PhoneState.OFF_HOOK, List.of(
                MyPair.of(Trigger.CALL_DIALED, PhoneState.CONNECTING),
                MyPair.of(Trigger.STOP_USING_PHONE, PhoneState.ON_HOOK)
        ));

        rules.put(PhoneState.CONNECTING, List.of(
                MyPair.of(Trigger.HUNG_UP, PhoneState.OFF_HOOK),
                MyPair.of(Trigger.CALL_CONNECTED, PhoneState.CONNECTED)
        ));

        rules.put(PhoneState.CONNECTED, List.of(
                MyPair.of(Trigger.LEFT_MESSAGE, PhoneState.OFF_HOOK),
                MyPair.of(Trigger.HUNG_UP, PhoneState.OFF_HOOK),
                MyPair.of(Trigger.PLACED_ON_HOLD, PhoneState.ON_HOLD)
        ));

        rules.put(PhoneState.ON_HOLD, List.of(
                MyPair.of(Trigger.TAKEN_OFF_HOLD, PhoneState.CONNECTED),
                MyPair.of(Trigger.HUNG_UP, PhoneState.OFF_HOOK)
        ));
    }

    private static PhoneState currentState = PhoneState.OFF_HOOK;
    private static PhoneState exitState = PhoneState.ON_HOOK;

    public static void main(String[] args) {

        BufferedReader console = new BufferedReader(new InputStreamReader(System.in));
        while(true) {
            System.out.println("The phone is currently " + currentState);
            System.out.println("Select the trigger");
            for (int i = 0; i < rules.get(currentState).size(); i++) {
                final Trigger trigger = rules.get(currentState).get(i).getFirst();
                System.out.println("" + i + ". " + trigger);
            }
            boolean parseOk;
            int choice = 0;
            do {
                try {
                    System.out.println("Please enter choice:");
                    choice = Integer.parseInt(console.readLine());
                    parseOk = choice >= 0 && choice < rules.get(currentState).size();
                } catch (IOException e) {
                    parseOk = false;
                }
            }while(!parseOk);
            currentState = rules.get(currentState).get(choice).getSecond();
            if(currentState == exitState) {
                break;
            }
            System.out.println("We are done");
        }

    }
}
