import java.util.TreeMap;

// The following file read data from a file I got from Digital Dutch, a website
// that the air density as a function of altitude, and stores in them in a
// treemap so when that the simulation can call to the lookup method at any
// altitude and find the air density to add an extra layer of real life
// simulation into the program.
public class AirDensity {
    // Stores the air densities with altitude being the key
    private TreeMap<Double, Double> table = new TreeMap<Double, Double>();

    public AirDensity() {
        // Taking in the file
        In data = new In("AtmosphericData.txt");
        // Reading the files and populating the treemap
        while (!data.isEmpty()) {
            double alt = data.readDouble();
            double density = data.readDouble();
            table.put(alt, density);
        }
    }

    public double lookup(double alt) {
        // Finding the first key to have a starting reference
        double tempalt = 0;
        double airResistance = 1.225;
        // Finding the correct value
        if (alt > 0) {
            for (int i = 0; i < table.size(); i++) {
                if (alt >= tempalt && alt < table.higherKey(tempalt)) {
                    airResistance = table.get(tempalt);
                }
                tempalt++;
            }
        }
        return airResistance;
    }

    public static void main(String[] args) {
        AirDensity test = new AirDensity();
        // Bounds of the for loop can change to test if the correct altitudes
        // give the correct air densities
        for (int i = 50; i < 55; i++) {
            System.out.println(i + " " + test.lookup(i));
        }
    }
}
