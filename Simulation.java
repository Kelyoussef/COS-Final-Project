// Simulation is the main class which houses all the physics, calculations, and
// calls to the other helper .java files. In Simulation the constructor
// initializes all the starting inputs and sets them as the instance variables
// These variable are then used to calculate other important flight parameters
// that update through the flight (seen as other instance variables). During the
// calculations Simulation calls to AirDensity and ThrustCurve to find
// important data for the position of the rocket. Then at the end of each
// calculation it calls to DrawRocket to create a simplified visual of the rocket.
public class Simulation {
    // Rocket Variables
    private double inRocketDiameter;
    private double inCd;
    private double inWetMass;
    // Parachute Variables
    private double inParaOneDiameter;
    private String inParaTwoToggle;
    private double inParaTwoDiameter;
    // Wind Variables
    private double inRFactor;
    private double inInitAlt;
    private double inInitSpeed;
    // Flight Events
    private double inFirstRelease;
    private double inSecondRelease;
    // Iterated variables
    private double time, timeInt, altitude, velocity, drag, acceleration, thrust, diameter,
            dragDirection = 0;

    public Simulation(double rocketDiameter, double cd, double wetMass, double paraOneDiameter,
                      String paraTwoToggle, double paraTwoDiameter, double rFactor, double initAlt,
                      double initSpeed) {
        inRocketDiameter = rocketDiameter;
        inCd = cd;
        inWetMass = wetMass;
        inParaOneDiameter = paraOneDiameter;
        inParaTwoToggle = paraTwoToggle;
        inParaTwoDiameter = paraTwoDiameter;
        inRFactor = rFactor;
        inInitAlt = initAlt;
        inInitSpeed = initSpeed;
    }

    private double findDrag(int phase) {
        AirDensity density = new AirDensity();
        drag = 0;
        // Burn and coasting phases
        if (phase == 1 || phase == 2 || phase == 4 || phase == 6) {
            drag = dragDirection * 0.5 * density.lookup(altitude) * (velocity * velocity) * inCd
                    * 3.1415926 * ((diameter / 2) * (diameter / 2));
        }
        // First parachute opening phase
        else if (phase == 3) {
            drag = dragDirection * 0.5 * density.lookup(altitude) * (velocity * velocity) * inCd
                    * 3.1415926 * ((diameter / 2) * (diameter / 2)) * (time / (inFirstRelease
                    + 1.5));
        }
        // Second parachute opening phase
        else if (phase == 5) {
            drag = dragDirection * 0.5 * density.lookup(altitude) * (velocity * velocity) * inCd
                    * 3.1415926 * ((diameter / 2) * (diameter / 2)) * (time / (inSecondRelease
                    + 2));
        }
        return drag;
    }

    public void step(int phase) {
        // Phase specific parameters
        ThrustCurve engine = new ThrustCurve();
        if (phase == 1) {
            thrust = engine.lookup(time);
            timeInt = 0.05;
            diameter = inRocketDiameter;
            dragDirection = -1;
        }
        else if (phase == 2) {
            thrust = 0;
            diameter = inRocketDiameter;
        }
        else if (phase == 3) {
            thrust = 0;
            timeInt = 0.1;
            inCd = 0.97;
            diameter = inParaOneDiameter;
            dragDirection = 1;
        }
        else if (phase == 4) {
            thrust = 0;
            timeInt = 0.5;
        }
        else if (phase == 5) {
            thrust = 0;
            timeInt = 0.01;
            inCd = 2.2;
            diameter = inParaTwoDiameter;
        }
        else if (phase == 6)
            timeInt = 0.5;

        // time
        time += timeInt;
        // drag

        drag = findDrag(phase);
        // acceleration
        acceleration = (drag + thrust + (inWetMass * -9.81)) / inWetMass;
        // velocity
        velocity += acceleration * timeInt;
        // altitude
        altitude += velocity * timeInt + acceleration * (timeInt * timeInt);
        // Wind
        Wind wspeed = new Wind(1.6, 100, 10);
        double windSpeed = wspeed.findWind(altitude);
        // Printing out values to terminal
        if ((int) time % 20 == 0) {
            // Printing out time, altitude, velocity, acceleration, thrust, drag, wind speed
            StdOut.println("Time: " + time);
            StdOut.println("Altitude: " + altitude);
            StdOut.println("Velocity: " + velocity);
            StdOut.println("Acceleration: " + acceleration);
            StdOut.println("Thrust: " + thrust);
            StdOut.println("Drag: " + drag);
            StdOut.println("Wind Speed: " + windSpeed);
            StdOut.println("\n");
        }
        // draw
        DrawRocket drawRocket = new DrawRocket(phase, altitude, velocity, time, windSpeed, thrust,
                                               acceleration);
        drawRocket.draw();
    }

    public void simulate() {
        // Phase specific iterations
        while (time <= 6.408) {
            step(1);
        }
        while (velocity > 0) {
            step(2);
        }
        inFirstRelease = time;
        if (inParaTwoToggle.equals("True")) {
            while (time < inFirstRelease + 1.5) {
                step(3);
            }
            while (altitude > 200) {  // main dep. alt 200m
                step(4);
            }
            inSecondRelease = time;
            while (time < inSecondRelease + 2) {
                step(5);
            }
            while (altitude > 0) {
                step(6);
            }
        }
        else {
            while (altitude > 0) {
                step(3);
            }
        }
    }

    public static void main(String[] args) {
        Simulation test = new Simulation(0.1, 0.5, 5, 0.7, "True", 2, 1.6, 40, 10);
        test.simulate();
    }
}
