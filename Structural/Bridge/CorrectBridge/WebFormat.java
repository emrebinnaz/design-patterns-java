package Structural.Bridge.CorrectBridge;

public class WebFormat implements ReportFormat {

    @Override
    public void generate() {
        System.out.println("Report was generated as web format.");
    }
}
