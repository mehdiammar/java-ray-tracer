public class Vector {
  
  public double x, y, z;

  public Vector(double x, double y, double z) {
    this.x = x;
    this.y = y;
    this.z = z;
  }
  
  public Vector(double n) {
    this.x = n;
    this.y = n;
    this.z = n;
  }

  public double getmag() {
    return Math.sqrt(this.dot(this));
  }

  public Vector norm() {
    return this.div(this.getmag());
  }

  public double dot(Vector v) {
    return this.x * v.x + this.y * v.y + this.z * v.z;
  }

  public Vector add(Vector v) {
    return new Vector(this.x + v.x, this.y + v.y, this.z + v.z);
  }

  public Vector sub(Vector v) {
    return new Vector(this.x - v.x, this.y - v.y, this.z - v.z);
  }

  public Vector mult(double n) {
    return new Vector(this.x * n, this.y * n, this.z * n);

  }

  public Vector div(double n) {
    return new Vector(this.x / n, this.y / n, this.z / n);
  }

  public String toString() {
    return "(" + this.x + ", " + this.y + ", " + this.z + ")";
  }

  public String toWhiteSpaceString() {
    return (int) this.x + " " + (int) this.y + " " + (int) this.z;
  }

  public double getX() {
    return x;
  }

  public double getY() {
    return y;
  }

  public double getZ() {
    return z;
  }
}
