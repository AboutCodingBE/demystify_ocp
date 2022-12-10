package be.aboutcoding;

public class Main {

    /**
     * The following is a small example program. The sole purpose of this program is to be an example for polymorphism
     * and the OCP. It is not meant ot be the best program ever written. It's sole purpose is just to be an example. And
     * yes, I had a bit of fun writing it, but I think that is allowed ;) .
     */

    public static void main(String[] args) {
        SensorValidationProcess validation = new SensorValidationProcess();
        validation.start(1100987, 1112334, 2112345, 456678, 99980, 43345);
    }
}
