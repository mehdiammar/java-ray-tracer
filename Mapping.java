import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Mapping {

  BufferedImage texture;
  int w;
  int h;
  int pixels[][];

  public Mapping(String path) {
    if (path != null) {
      try {
        texture = ImageIO.read(new File(path));
      } catch (IOException e) {
        e.printStackTrace();
      }
      w = texture.getWidth();
      h = texture.getHeight();
      pixels = toArray(texture);
    }
  }

  public int getWidth() {
    return this.w;
  }

  public int getHeight() {
    return this.h;
  }
  
  /*
   * Store texture pixels into array
   */

  public int[][] toArray(BufferedImage texture) {
    int[][] arr = new int[texture.getWidth()][texture.getHeight()];
    for (int i = 0; i < texture.getWidth(); i++)
      for (int j = 0; j < texture.getHeight(); j++)
        arr[i][j] = texture.getRGB(i, j);
    return arr;
  }

  public Color findTextureColorAt(int w, int h, Sphere sphere, Mapping texture, Vector hit_pos) {
    Vector position = (hit_pos.sub(sphere.getCenter())).norm();
    double u, v;
    double phi = Math.acos(1 * sphere.getNorth().dot(position));
    v = (phi / Math.PI);
    double theta = (Math.acos(position.dot(sphere.getEquator()) / Math.sin(phi))) / (2 * Math.PI);
    if ((position.dot(sphere.getNorth().cross(sphere.getEquator()))) > 0) {
      u = theta;
    } else
      u = (1 - theta);
    return new Color((texture.pixels[(int) (u * w)][(int) (v * h)] & 0xFF0000) >> 16,
        (texture.pixels[(int) (u * w)][(int) (v * h)] & 0xFF00) >> 8,
        (texture.pixels[(int) (u * w)][(int) (v * h)]) & 0xFF);
  }
}
