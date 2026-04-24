package btotw_turbine;

public class Light {
    private float brightness;
    private float hue;

    public Light() {

    }

    public float getBrightness() {
        return this.brightness;
    }
    public float getHue() {
        return this.hue;
    }

    public void setHue(float hue) {
        this.hue = (hue % 360 + 360) % 360;
    }
    public void setBrightness(float brightness) {
        this.brightness = Math.min(1, Math.max(brightness, 0));
    }
}
