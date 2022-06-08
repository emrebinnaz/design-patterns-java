package Structural.Bridge;

public class ViolatedBridge {

    interface Report {
        void display();
    }
    class SalesReport implements Report {

        @Override
        public void display() {
            System.out.println("Sales report was displayed");
        }
    }
    class WebSalesReport extends SalesReport {
        @Override
        public void display() {
            System.out.println("Web sales report was displayed");
        }
    }


    class EmployeePerformanceReport implements Report{

        @Override
        public void display() {
            System.out.println("Employee report was displayed");
        }
    }

    class WebEmpPerformanceReport extends SalesReport {
        @Override
        public void display() {
            System.out.println("Web employee performance report was displayed");
        }
    }
    // What if I have to generate report for mobile ? I would have to generate 2 classes both employees and sales.
    // Also If I had wanted to create Manager Report, I would  ave to generate web and mobile performance reports.
    // This structure causes cartesian complexity.
}
