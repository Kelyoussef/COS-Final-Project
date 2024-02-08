// The following function uses an initial wind speed and altitude to create a
// gradient using a logarithmic function and roughness (or scale) factor. The
// simulation can now call the findWind method and use that value to calculate
// drift. Also, DrawRocket.java calls to findWind to use it to rotate the rocket
// body.
public class Wind {
    // Scale factor for how strong the wind is
    // Value between 0.0002 and 1.6 inclusive
    private final double rFactor;
    // Initial Altitude
    private final double initialAlt;
    // Initial wind speed
    private final double initialSpeed;

    // Defines the starting conditions
    public Wind(double inputrFactor, double inputAlt, double inputSpeed) {
        rFactor = inputrFactor;
        initialAlt = inputAlt;
        initialSpeed = inputSpeed;
    }

    // Calculates wind speed based of starting conditions
    public double findWind(double alt) {
        double speed = 0;
        if (alt > 0) {
            // Logarithmic equation
            speed = initialSpeed * Math.log(alt / rFactor) / Math.log(initialAlt / rFactor);
            // Randomizer to remove perfect gradient
            speed = speed * StdRandom.uniformDouble(0.75, 1);
        }
        return speed;
    }

    public static void main(String[] args) {
        Wind test = new Wind(1.6, 40, 10);
        // Bounds of the for loop can be changed to test the gradient at different
        // altitudes
        for (int i = 0; i < 150; i += 5) {
            System.out.println(i + " " + test.findWind(i));
        }
    }
}
