
public class Color extends Vector {

  private double r, g, b;

  public Color(double r, double g, double b) {
    super(r, g, b);
  }

  public double getR() {
    return r;
  }

  public double getG() {
    return g;
  }

  public double getB() {
    return b;
  }
  
  public double getRGB() {
    return this.r + this.g + this.b;
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
