import java.awt.Color;

// The following file take into account the phase of the rocket (or the stage
// it is at during flight 1=burn, 2=coast, 3=drogue parachute, 4=main parachute)
// and use that to draw out what to rocket looks like during that phase. Across
// the DrawRocket method the rocket will be drawn out in multiple states, flame
// cone, drogue parachute with shock cord and attachment lines, and the main
// parachute with shock cord and attachment lines. It also takes into account
// the altitude and uses that to have a variable background. The background
// includes the ground shown at different level, sky, clouds, and if a high
// enough altitude is reached (20,000 feet) the screen goes black to simulate
// space (this is done because the rocket should never actually reach that
// altitude unless extremely unrealistic conditions are imputed). Wind speed is
// also taken into account and is treated as a degree number used to rotate the
// rocket and its respective bodies to simulate the vibrations seen during
// flight. Finally, the velocity and time are taken from the simulation, so they
// can be printed on the screen to give some sense as to what is happening during
// the flight.
public class DrawRocket {
    // The phase is what defines what rocket aspects get drawn
    private int inphase;
    // The altitude decides what background is shown
    private double inAlt;
    // String representation of altitude to print onto the screen
    private String sinAlt;
    // Velocity of the rocket to print onto the screen
    private String inVelocity;
    // Time the simulation is at to print onto the screen
    private String inTime;
    // The wind decides how much tilt is given to the rocket
    private double inwind;
    // The thrust value to print onto the screen
    private String inthrust;
    // The acceleration to print onto the screen
    private String inAcceleration;

    public DrawRocket(int phase, double altitude, double velocity, double time,
                      double wind, double thrust, double acceleration) {
        // Filling the instance variables
        inphase = phase;
        inAlt = altitude;
        sinAlt = "Altitude: " + Double.toString(altitude);
        inVelocity = "Velocity: " + Double.toString(velocity);
        inTime = "Time: " + Double.toString(time);
        inwind = wind;
        inthrust = "Thrust: " + Double.toString(thrust);
        inAcceleration = "Acceleration: " + Double.toString(acceleration);
    }

    public void draw() {
        // Setting the scale
        StdDraw.setXscale(0, 40);
        StdDraw.setYscale(0, 40);
        // Double buffering
        StdDraw.enableDoubleBuffering();
        // Defining custom colors
        Color rocket = new Color(204, 87, 0);
        Color flameCone = new Color(183, 0, 0);
        Color parachute = new Color(2, 169, 107);
        Color parachutein = new Color(2, 222, 107);
        // Defining initial rocket part shape positions
        double[] body1x = { 18.5, 21.5, 21.5, 18.5 };
        double[] body1y = { 17.5, 17.5, 9.5, 9.5 };
        double[] body2x = { 18.5, 21.5, 21.5, 18.5 };
        double[] body2y = { 22.5, 22.5, 14.5, 14.5 };
        double[] fin1x = { 18.5, 18.5, 16.5 };
        double[] fin1y = { 9.5, 12.5, 8.5 };
        double[] fin2x = { 21.5, 21.5, 23.5 };
        double[] fin2y = { 9.5, 12.5, 8.5 };
        double[] noseConex = { 18.5, 21.5, 20 };
        double[] noseConey = { 22.5, 22.5, 25.5 };
        double[] flameConex = { 19, 21, 22, 21, 20, 19, 18 };
        double[] flameConey = { 9.5, 9.5, 6.5, 7.25, 5, 7.25, 6.5 };
        // Phase one includes the rocket and the flame cone
        if (inphase == 1) {
            // Adding in the tilt
            if (inwind != 0) {
                rotate(body1x, body1y, inwind / 2);
                rotate(body2x, body2y, inwind / 2);
                rotate(fin1x, fin1y, inwind / 2);
                rotate(fin2x, fin2y, inwind / 2);
                rotate(noseConex, noseConey, inwind / 2);
                rotate(flameConex, flameConey, inwind / 2);
            }
            // Drawing the background
            background();
            // Drawing the rocket
            StdDraw.setPenColor(rocket);
            StdDraw.filledPolygon(body1x, body1y);
            StdDraw.filledPolygon(body2x, body2y);
            StdDraw.filledPolygon(fin1x, fin1y);
            StdDraw.filledPolygon(fin2x, fin2y);
            StdDraw.filledPolygon(noseConex, noseConey);
            // Drawing the flame cone
            StdDraw.setPenColor(flameCone);
            StdDraw.filledPolygon(flameConex, flameConey);
            // text
            StdDraw.setPenColor(Color.GRAY);
            StdDraw.textRight(39, 39, inTime);
            StdDraw.textRight(39, 38, sinAlt);
            StdDraw.textRight(39, 37, inVelocity);
            StdDraw.textRight(39, 36, inAcceleration);
            StdDraw.textRight(39, 35, inthrust);
            // Iterations
            StdDraw.show();
            StdDraw.pause(50);
            StdDraw.clear();
        }
        // Phase two includes the rocket
        if (inphase == 2) {
            // Adding in the tilt
            if (inwind != 0) {
                rotate(body1x, body1y, inwind / 3);
                rotate(body2x, body2y, inwind / 3);
                rotate(fin1x, fin1y, inwind / 3);
                rotate(fin2x, fin2y, inwind / 3);
                rotate(noseConex, noseConey, inwind / 3);
            }
            // Drawing the background
            background();
            // Drawing the rocket
            StdDraw.setPenColor(rocket);
            StdDraw.filledPolygon(body1x, body1y);
            StdDraw.filledPolygon(body2x, body2y);
            StdDraw.filledPolygon(fin1x, fin1y);
            StdDraw.filledPolygon(fin2x, fin2y);
            StdDraw.filledPolygon(noseConex, noseConey);
            // Text
            StdDraw.setPenColor(Color.GRAY);
            StdDraw.textRight(39, 39, inTime);
            StdDraw.textRight(39, 38, sinAlt);
            StdDraw.textRight(39, 37, inVelocity);
            StdDraw.textRight(39, 36, inAcceleration);
            StdDraw.textRight(39, 35, inthrust);
            // Iterations
            StdDraw.show();
            StdDraw.pause(50);
            StdDraw.clear();
        }
        // Defining new rocket part shapes
        double[] newbody1x = { 15.5, 18.5, 18.5, 15.5 };
        double[] newbody2x = { 15.5, 18.5, 18.5, 15.5 };
        double[] newfin1x = { 15.5, 15.5, 13.5 };
        double[] newfin2x = { 18.5, 18.5, 20.5 };
        double[] newnoseConex = { 23, 26, 24.5 };
        double[] newnoseConey = { 18.5, 18.5, 15.5 };
        double[] bodyscx = { 17 };
        double[] bodyscy = { 22.5 };
        double[] noseconescx = { 24.5 };
        double[] noseconescy = { 18.5 };
        // Phase three includes the rocket (in a separated state) and the drogue parachute
        if (inphase == 4 || inphase == 3) {
            // Adding in the tilt
            if (inwind != 0) {
                rotate(newbody1x, body1y, inwind / 3);
                rotate(newbody2x, body2y, inwind / 3);
                rotate(newfin1x, fin1y, inwind / 3);
                rotate(newfin2x, fin2y, inwind / 3);
                rotate(newnoseConex, newnoseConey, inwind / 3);
                rotate(bodyscx, bodyscy, inwind / 3);
                rotate(noseconescx, noseconescy, inwind / 3);
            }
            // Drawing the background
            background();
            // Drawing the parachute
            StdDraw.setPenColor(parachute);
            StdDraw.filledEllipse(20, 35, 6, 2);
            StdDraw.setPenColor(parachutein);
            StdDraw.filledEllipse(20, 34.25, 5, 1.25);
            // Drawing the drogue lines
            StdDraw.setPenColor(Color.GRAY);
            StdDraw.line(15, 35, 20, 29);
            StdDraw.line(25, 35, 20, 29);
            StdDraw.line(19, 33, 20, 29);
            StdDraw.line(21, 33, 20, 29);
            StdDraw.line(17, 35.5, 20, 29);
            StdDraw.line(23, 35.5, 20, 29);
            StdDraw.line(20, 36, 20, 29);
            // Drawing the move-able lines
            StdDraw.line(bodyscx[0], bodyscy[0], 20, 29);
            StdDraw.line(noseconescx[0], noseconescy[0], 20, 29);
            // Drawing the rocket
            StdDraw.setPenColor(rocket);
            StdDraw.filledPolygon(newbody1x, body1y);
            StdDraw.filledPolygon(newbody2x, body2y);
            StdDraw.filledPolygon(newfin1x, fin1y);
            StdDraw.filledPolygon(newfin2x, fin2y);
            StdDraw.filledPolygon(newnoseConex, newnoseConey);
            // Text
            StdDraw.setPenColor(Color.GRAY);
            StdDraw.textRight(39, 39, inTime);
            StdDraw.textRight(39, 38, sinAlt);
            StdDraw.textRight(39, 37, inVelocity);
            StdDraw.textRight(39, 36, inAcceleration);
            StdDraw.textRight(39, 35, inthrust);
            // Iterations
            StdDraw.show();
            StdDraw.pause(50);
            StdDraw.clear();
        }
        // Defining new shape boundaries
        double[] finalbody1x = { 12, 15, 15, 12 };
        double[] finalbody2x = { 18, 18, 22, 22 };
        double[] finalbody2y = { 21.5, 18.5, 18.5, 21.5 };
        double[] finalfin1x = { 12, 12, 10 };
        double[] finalfin2x = { 15, 15, 17 };
        double[] finalncx = { 28.5, 31.5, 30 };
        double[] finalbody1scx = { 13.5 };
        double[] finalbody1scy = { 17.5 };
        double[] finalbody2scxleft = { 18 };
        double[] finalbody2scyleft = { 20 };
        double[] finalbody2scxright = { 22 };
        double[] finalbody2scyright = { 20 };
        double[] finalncscx = { 30 };
        double[] finalncscy = { 18.5 };
        // Phase four includes the rocket (in a separated state) with the drogue and main parachutes
        if (inphase == 6 || inphase == 5) {
            // Adding in the tilt
            if (inwind != 0) {
                rotate(finalbody1x, body1y, inwind / 3);
                rotate(finalbody2x, finalbody2y, inwind / 3);
                rotate(finalfin1x, fin1y, inwind / 3);
                rotate(finalfin2x, fin2y, inwind / 3);
                rotate(finalncx, newnoseConey, inwind / 3);
                rotate(finalbody1scx, finalbody1scy, inwind / 3);
                rotate(finalbody2scxleft, finalbody2scyleft, inwind / 3);
                rotate(finalbody2scxright, finalbody2scyright, inwind / 3);
                rotate(finalncscx, finalncscy, inwind / 3);
            }
            // Drawing the background
            background();
            // Drawing the parachutes
            StdDraw.setPenColor(parachute);
            StdDraw.filledEllipse(26, 30, 6, 2);
            StdDraw.filledEllipse(13, 35, 10, 4);
            StdDraw.setPenColor(parachutein);
            StdDraw.filledEllipse(26, 29.25, 5, 1.25);
            StdDraw.filledEllipse(13, 33.5, 9, 2.5);
            // Drogue lines
            StdDraw.setPenColor(Color.GRAY);
            StdDraw.line(25, 28, 26, 24);
            StdDraw.line(27, 28, 26, 24);
            StdDraw.line(31, 30, 26, 24);
            StdDraw.line(21, 30, 26, 24);
            StdDraw.line(29, 30.5, 26, 24);
            StdDraw.line(23, 30.5, 26, 24);
            StdDraw.line(26, 31, 26, 24);
            // Main Lines
            StdDraw.line(13, 36.25, 13, 24);
            StdDraw.line(3.75, 33.75, 13, 24);
            StdDraw.line(22.25, 33.75, 13, 24);
            StdDraw.line(7.5, 35.75, 13, 24);
            StdDraw.line(18.5, 35.75, 13, 24);
            StdDraw.line(11, 31, 13, 24);
            StdDraw.line(15, 31, 13, 24);
            StdDraw.line(8, 31.5, 13, 24);
            StdDraw.line(18, 31.5, 13, 24);
            // Move-able lines
            StdDraw.line(finalbody1scx[0], finalbody1scy[0], 13, 24);
            StdDraw.line(finalbody2scxleft[0], finalbody2scyleft[0], 13, 24);
            StdDraw.line(finalbody2scxright[0], finalbody2scyright[0], 26, 24);
            StdDraw.line(finalncscx[0], finalncscy[0], 26, 24);
            // Drawing the rocket
            StdDraw.setPenColor(rocket);
            StdDraw.filledPolygon(finalbody1x, body1y);
            StdDraw.filledPolygon(finalbody2x, finalbody2y);
            StdDraw.filledPolygon(finalfin1x, fin1y);
            StdDraw.filledPolygon(finalfin2x, fin2y);
            StdDraw.filledPolygon(finalncx, newnoseConey);
            // Text
            StdDraw.setPenColor(Color.GRAY);
            StdDraw.textRight(39, 39, inTime);
            StdDraw.textRight(39, 38, sinAlt);
            StdDraw.textRight(39, 37, inVelocity);
            StdDraw.textRight(39, 36, inAcceleration);
            StdDraw.textRight(39, 35, inthrust);
            // Iterations
            StdDraw.show();
            StdDraw.pause(50);
            StdDraw.clear();
        }
    }

    private void background() {
        // Defining custom colors
        Color ground = new Color(2, 145, 22);
        Color clouds = new Color(167, 234, 255);
        // All screen is ground
        if (inAlt <= 0) {
            StdDraw.setPenColor(ground);
            StdDraw.filledRectangle(20, 20, 20, 20);
        }
        // 1/2 screen is ground
        else if (inAlt <= 5) {
            StdDraw.setPenColor(ground);
            StdDraw.filledRectangle(20, 10, 20, 10);
            StdDraw.setPenColor(Color.BLUE);
            StdDraw.filledRectangle(20, 30, 20, 10);
        }
        // 1/4 screen is ground
        else if (inAlt > 5 && inAlt <= 15) {
            StdDraw.setPenColor(ground);
            StdDraw.filledRectangle(20, 10, 20, 10);
            StdDraw.setPenColor(Color.BLUE);
            StdDraw.filledRectangle(20, 30, 20, 20);
        }
        // 1/8 screen is ground
        else if (inAlt > 15 && inAlt <= 30) {
            StdDraw.setPenColor(ground);
            StdDraw.filledRectangle(20, 2.5, 20, 2.5);
            StdDraw.setPenColor(Color.BLUE);
            StdDraw.filledRectangle(20, 22.5, 20, 17.5);
        }
        // 1/16 screen is ground
        else if (inAlt > 30 && inAlt <= 50) {
            StdDraw.setPenColor(ground);
            StdDraw.filledRectangle(20, 1.25, 20, 1.25);
            StdDraw.setPenColor(Color.BLUE);
            StdDraw.filledRectangle(20, 21.25, 20, 18.75);
        }
        // 1/8 screen is ground
        else if (inAlt > 50 && inAlt <= 75) {
            StdDraw.setPenColor(ground);
            StdDraw.filledRectangle(20, 0.625, 20, 0.625);
            StdDraw.setPenColor(Color.BLUE);
            StdDraw.filledRectangle(20, 20.625, 20, 19.375);
        }
        // All sky
        else if (inAlt > 75 && inAlt <= 305) {
            StdDraw.setPenColor(Color.BLUE);
            StdDraw.filledRectangle(20, 20, 20, 20);
        }
        // All sky with one cloud
        else if (inAlt > 305 && inAlt <= 610) {
            StdDraw.setPenColor(Color.BLUE);
            StdDraw.filledRectangle(20, 20, 20, 20);
            StdDraw.setPenColor(clouds);
            StdDraw.filledCircle(10, 25, 2.5);
            StdDraw.filledCircle(12.5, 27.5, 3);
        }
        // All sky
        else if (inAlt > 610 && inAlt <= 1525) {
            StdDraw.setPenColor(Color.BLUE);
            StdDraw.filledRectangle(20, 20, 20, 20);
        }
        // All sky with two clouds
        else if (inAlt > 1525 && inAlt <= 3050) {
            StdDraw.setPenColor(Color.BLUE);
            StdDraw.filledRectangle(20, 20, 20, 20);
            StdDraw.setPenColor(clouds);
            StdDraw.filledCircle(25, 25, 4);
            StdDraw.filledCircle(27, 25, 4);
            StdDraw.filledCircle(29, 27, 4);
            StdDraw.filledCircle(5, 10, 4);
            StdDraw.filledCircle(7, 12, 4);
            StdDraw.filledCircle(9, 10, 4);
            StdDraw.filledCircle(7, 8, 4);
            StdDraw.filledCircle(11, 8, 4);
        }
        // All sky with three clouds
        else if (inAlt > 3050 && inAlt <= 6100) {
            StdDraw.setPenColor(Color.BLUE);
            StdDraw.filledRectangle(20, 20, 20, 20);
            StdDraw.setPenColor(clouds);
            StdDraw.filledCircle(15, 25, 6);
            StdDraw.filledCircle(18, 28, 6);
            StdDraw.filledCircle(21, 25, 6);
            StdDraw.filledCircle(24, 28, 6);
            StdDraw.filledCircle(27, 25, 6);
            StdDraw.filledCircle(34, 10, 5);
            StdDraw.filledCircle(36.5, 12.5, 5);
            StdDraw.filledCircle(39, 10, 5);
            StdDraw.filledCircle(5, 5, 7);
            StdDraw.filledCircle(7, 5, 6);
            StdDraw.filledCircle(5, 7, 6);
        }
        // All black (space but not really)
        else if (inAlt > 6100 && inAlt <= 100000) {
            StdDraw.setPenColor(Color.BLACK);
            StdDraw.filledRectangle(20, 20, 20, 20);
        }
        else if (inAlt > 100000) {
            StdDraw.setPenColor(ground);
            StdDraw.filledRectangle(20, 20, 20, 20);
        }
    }

    private void rotate(double[] x, double[] y, double wind) {
        // Converting degrees to radians
        double radian = Math.toRadians(wind);
        // Coping the original array to use in equations
        if (radian != 0) {
            double[] ccx = copy(x);
            double[] ccy = copy(y);
            // For loop to rotate all x values
            for (int i = 0; i < x.length; i++) {
                x[i] = ccx[i] * Math.cos(radian) - ccy[i] * Math.sin(radian);
            }
            // For loop to rotate all y values
            for (int i = 0; i < y.length; i++) {
                y[i] = ccy[i] * Math.cos(radian) + ccx[i] * Math.sin(radian);
            }
        }
    }

    private double[] copy(double[] array) {
        // Defining new array
        double[] newArray = new double[array.length];
        // For loop to equate all values of old and new arrays
        for (int i = 0; i < array.length; i++) {
            newArray[i] = array[i];
        }
        return newArray;
    }

    public static void main(String[] args) {
        Wind test = new Wind(1.6, 100, 10);
        // For statements are set up to test both the drawing of the rocket and
        // other parts plus the background
        for (int i = 0; i < 80; i += 5) {
            DrawRocket testt = new DrawRocket(1, i, 100, 10, test.findWind(i), 100, 5);
            testt.draw();
        }
        for (int i = 80; i < 300; i += 10) {
            DrawRocket testt = new DrawRocket(2, i, 100, 10, test.findWind(i), 100, 10);
            testt.draw();
        }
        for (int i = 300; i < 610; i += 10) {
            DrawRocket testt = new DrawRocket(2, i, 100, 10, test.findWind(i), 100, 15);
            testt.draw();
        }
        for (int i = 610; i < 1530; i += 10) {
            DrawRocket testt = new DrawRocket(2, i, 100, 10, test.findWind(i), 100, 20);
            testt.draw();
        }
        for (int i = 1530; i < 3050; i += 10) {
            DrawRocket testt = new DrawRocket(2, i, 100, 10, test.findWind(i), 100, 25);
            testt.draw();
        }
        for (int i = 3050; i < 6500; i += 10) {
            DrawRocket testt = new DrawRocket(2, i, 100, 10, test.findWind(i), 100, 30);
            testt.draw();
        }
        for (int i = 6500; i > 650; i -= 10) {
            DrawRocket testt = new DrawRocket(4, i, 100, 10, test.findWind(i), 100, 35);
            testt.draw();
        }
        for (int i = 650; i >= 0; i -= 10) {
            DrawRocket testt = new DrawRocket(6, i, 100, 10, test.findWind(i), 100, 40);
            testt.draw();
        }
    }
}
