
public class Color extends Vector {

  public Color(double r, double g, double b) {
    super(r, g, b);
  }

  public double getR() {
    return x;
  }

  public double getG() {
    return y;
  }

  public double getB() {
    return z;
  }
  
  public double getRGB() {
    return this.x + this.y + this.z;
  }
  
  public static Color toRGB(String h) {
    if (h.length() != 7) {
      return null; // Wrong hexidecimal color format
    }
    int r = Integer.decode("#" + h.substring(1, 3));
    int g = Integer.decode("#" + h.substring(3, 5));
    int b = Integer.decode("#" + h.substring(5, 7));
    return new Color(r, g, b);
  }
}
