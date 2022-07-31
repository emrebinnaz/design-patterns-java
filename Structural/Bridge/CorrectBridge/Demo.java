package Structural.Bridge.CorrectBridge;

 interface ReportFormat {
    void generate();
}

class WebFormat implements ReportFormat {

    @Override
    public void generate() {
        System.out.println("Report was generated as web format.");
    }
}

class MobileFormat implements ReportFormat {

    @Override
    public void generate() {
        System.out.println("Report was generated as mobile format.");
    }
}

class DesktopFormat implements ReportFormat{
    @Override
    public void generate() {
        System.out.println("Report was generated as desktop format.");
    }
}

 class Report {
    public ReportFormat reportFormat;

    public Report(ReportFormat reportFormat) {
        this.reportFormat = reportFormat;
    }

    public void display() {
        reportFormat.generate();
    }
}
 class SalesReport extends Report {

    public SalesReport(ReportFormat reportFormat) {
        super(reportFormat);
    }

    @Override
    public void display() {
        System.out.println("Sales report was generated");
        super.display();
    }
}
class EmployeePerformanceReport extends Report {

    public EmployeePerformanceReport(ReportFormat reportFormat) {
        super(reportFormat);
    }

    @Override
    public void display() {
        System.out.println("Employee performance report was generated");
        super.display();
    }
}

public class Demo {
    public static void main(String[] args) {

        Report report2 = new SalesReport(new DesktopFormat());
        report2.display();

    }
}


