package Structural.Bridge.CorrectBridge;

public class Demo {
    public static void main(String[] args) {

        Report report2 = new SalesReport(new DesktopFormat());
        report2.display();

    }
}


