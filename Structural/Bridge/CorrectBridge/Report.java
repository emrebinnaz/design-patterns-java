package Structural.Bridge.CorrectBridge;

public class Report {
    public ReportFormat reportFormat;

    public Report(ReportFormat reportFormat) {
        this.reportFormat = reportFormat;
    }

    public void display() {
        reportFormat.generate();
    }
}
