package btotw_turbine;

import java.util.Random;

import btotw_turbine.text.Color;
import btotw_turbine.text.RichText;

public class Turbine {
    public static final float WIND_THRESHOLD = 10;
    public static final float ROTATION_THRESHOLD = 3;

    private float windSpeed; // m/s
    private float rotationSpeed;
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



    /**
     * Used to query information about the mounted light
     * @param args
     */
    public void lightCommand(String[] args) {

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

        System.out.println(RichText.text("  Windspeed: ").append(String.format("%.4f m/s", this.windSpeed)).toFormattedString());

        System.out.println(RichText.text("  Rotation:  ").append(String.format("%.4f rps", this.rotationSpeed)).toFormattedString());

        System.out.println(RichText.text("  Torque:    ").append(String.format("%.4f", this.torque)).toFormattedString());

        System.out.println(RichText.text("  Power:     ").append(String.format("%.4f", this.power)).toFormattedString());
    }


}
