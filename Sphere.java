
public class Sphere {
  private double rad;
  private Point center;
  private Color color;
  private boolean isReflective;
  private Vector north;
  private Vector equator;
  private Mapping texture;

  public Sphere(double rad, Point center, Color color, boolean isReflective, String texture) {
    this.rad = rad;
    this.center = center;
    this.color = color;
    this.isReflective = isReflective;
    this.north = (new Vector(center.getX(), center.getY() - 1 * rad, center.getZ())).norm();
    this.equator = (new Vector(center.getX() + rad, center.getY(), center.getZ())).norm();
    if (texture != null)
      this.texture = new Mapping(texture);
    else
      texture = null;
  }

  public boolean isTextured() {
    return this.texture != null;
  }

  public Mapping getMapping() {
    return this.texture;
  }

  public Color getColor() {
    return this.color;
  }

  public Vector getNorth() {
    return this.north;
  }

  public Vector getEquator() {
    return this.equator;
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

    if (discriminant >= 0) { // Intersected once (tangent ray) or twice an object, return the closest distance
      double dist = (-1 * b - Math.sqrt(discriminant)) / (2 * a);
      if (dist > 0)
        return dist;
    }
    return -1;
  }
}
