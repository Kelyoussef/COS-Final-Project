import java.util.TreeMap;

// This file reads from a file I got from Hypertek Motors, a company that makes
// rocket motors and supplies data about them, and stores them in a treemap so
// when that the simulation can call to the lookup method at any
// time and find the thrust (specifically that of an L550 motor) to add an extra
// layer of real life simulation into the program.
public class ThrustCurve {
    // Stores the thrust with time being the key
    private TreeMap<Double, Double> table = new TreeMap<Double, Double>();

    public ThrustCurve() {
        // Taking in the file
        In data = new In("L550ThrustCurve.txt");
        // Reading the files and populating the treemap
        while (!data.isEmpty()) {
            double alt = data.readDouble();
            double density = data.readDouble();
            table.put(alt, density);
        }
    }

    public double lookup(double inTime) {
        // Finding the first key to have a starting reference
        double time = table.firstKey();
        double tvalue = 0;
        // Finding the correct value
        if (inTime != 0) {
            if (inTime < 0.124) {
                tvalue = table.get(0.124);
            }
            if (inTime > 6.408 || inTime == 6.408) {
                tvalue = 0;
            }
            else {
                for (int i = 0; i < table.size(); i++) {
                    if (inTime > time && inTime < table.higherKey(time)) {
                        tvalue = table.get(time);
                    }
                    if (time < 6.408) {
                        time = table.higherKey(time);
                    }
                }
            }
        }
        return tvalue;
    }

    public static void main(String[] args) {
        ThrustCurve test = new ThrustCurve();
        // Thrust ends at t=6.4 so bounds end at 6.5
        for (double i = 0; i < 6.5; i = i + 0.05) {
            System.out.println(i + " " + test.lookup(i));
        }
    }
}
