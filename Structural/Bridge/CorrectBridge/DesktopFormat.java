package Structural.Bridge.CorrectBridge;

public class DesktopFormat implements ReportFormat{
    @Override
    public void generate() {
        System.out.println("Report was generated as desktop format.");
    }
}
