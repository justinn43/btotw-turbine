package btotw_turbine;

public class Turbine {

    private float windSpeed; // m/s
    private float torque;
    private float power;

    private Light light;


    
    public Turbine() {
        this.light = new Light();
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


}
