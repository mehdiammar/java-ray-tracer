
public class Ray {
  private Point point;
  private Vector dir;
  
  public Ray(Point origin, Vector dir) {
    this.point = origin;
    this.dir = dir.norm();
  }

  public Vector getDir() {
    return dir;
  }

  public Point getPoint() {
    return point;
  }
  
}
