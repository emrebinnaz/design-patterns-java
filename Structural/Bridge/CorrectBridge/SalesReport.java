package Structural.Bridge.CorrectBridge;

public class SalesReport extends Report {

    public SalesReport(ReportFormat reportFormat) {
        super(reportFormat);
    }

    @Override
    public void display() {
        System.out.println("Sales report was generated");
        super.display();
    }
}