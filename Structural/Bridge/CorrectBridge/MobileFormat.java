package Structural.Bridge.CorrectBridge;

public class MobileFormat implements ReportFormat {

    @Override
    public void generate() {
        System.out.println("Report was generated as mobile format.");
    }
}
