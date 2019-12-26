
public class Sphere {
  private double rad;
  private Point center;
  private Color color;
  private boolean isReflective;

  public Sphere(double rad, Point center, Color color, boolean isReflective) {
    this.rad = rad;
    this.center = center;
    this.color = color;
    this.isReflective = isReflective;
  }

  public Color getColor() {
    return this.color;
  }
  
  public Point getCenter() {
    return this.center;
  }
  
  public boolean isReflective() {
    return this.isReflective;
  }

  public double intersects(Ray ray) {
    
    Vector to_ray = ray.getPoint().sub(this.center);
    double a = ray.getDir().dot(ray.getDir());
    double b = 2 * ray.getDir().dot(to_ray);
    double c = to_ray.dot(to_ray) - Math.pow(this.rad, 2);
    double discriminant = b * b - 4 * a * c;
    
    if (discriminant >= 0) { // Intersected once (tangent ray) or twice an object, return the distance
      double dist = (-1 * b - Math.sqrt(discriminant)) / (2 * a);
      if (dist > 0) 
        return dist;
    }
    return -1; 
  }
}
