package btotw_turbine;

import java.util.Random;

import btotw_turbine.text.Color;
import btotw_turbine.text.RichText;

public class Turbine {
    public static final float WIND_THRESHOLD = 10;
    public static final float ROTATION_THRESHOLD = 3;

    private float windSpeed; // m/s
    private float rotationSpeed; // in revolutions per second rps
    private float torque;
    private float power;

    private Light light;


    
    public Turbine() {
        this.light = new Light();


        // Randomize conditions to simulate a turbine
        Random random = new Random();
        this.windSpeed = random.nextFloat(0, 15);
        this.rotationSpeed = random.nextFloat(0, 4);
        this.torque = random.nextFloat();
        this.power = random.nextFloat();
    }


    /**
     * 
     * @return the current wind speed in m/s
     */
    public float getCurrentWindSpeed() {
        return this.windSpeed;
    }
    /**
     * 
     * @param windSpeed
     */
    public void updateWindSpeed(float windSpeed) {
        this.windSpeed = windSpeed;
    }

    public Status getStatus() {
        if (this.windSpeed > WIND_THRESHOLD || this.rotationSpeed > ROTATION_THRESHOLD) {
            return Status.DANGER;
        }
        return Status.OKAY;
    }

    public Light light() {
        return this.light;
    }

    /**
     * Prints the status of the current turbine
     */
    public void printStatus() {
        Status status = this.getStatus();
        System.out.println(RichText.text("The current status of the turbine is: ", Color.BRIGHT_WHITE)
            .append(
                RichText.text(status.name(), status.color()).bold()
            )
        );

        String windSpeed = String.format("%.4f m/s", this.windSpeed);
        String revolutions = String.format("%.4f rps", this.rotationSpeed);
        
        RichText formattedWindSpeed = RichText.text(String.format("%-13s", windSpeed));
        if (this.windSpeed > WIND_THRESHOLD) {
            formattedWindSpeed = formattedWindSpeed.foreground(Color.BRIGHT_RED);
        }

        RichText formattedRevolutions = RichText.text(String.format("%-13s", revolutions));
        if (this.rotationSpeed > ROTATION_THRESHOLD) {
            formattedRevolutions = formattedRevolutions.foreground(Color.BRIGHT_RED);
        }

        System.out.println(RichText.text("  Windspeed: ").append(formattedWindSpeed).append(String.format(" Safe Range: [%.1f, %.1f]", 0.0f, WIND_THRESHOLD)).toFormattedString());

        System.out.println(RichText.text("  Rotation:  ").append(formattedRevolutions).append(String.format(" Safe Range: [%.1f, %.1f]", 0.0f, ROTATION_THRESHOLD)).toFormattedString());

        System.out.println(RichText.text("  Torque:    ").append(String.format("%-13.4f", this.torque)).toFormattedString());

        System.out.println(RichText.text("  Power:     ").append(String.format("%-13.4f", this.power)).toFormattedString());
    }


}
