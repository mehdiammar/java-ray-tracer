import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Image {
  int w, h;
  Color[][] pixels;

  public Image(int w, int h) {
    this.w = w;
    this.h = h;
    pixels = init(w, h);
  }

  /*
   * Initialize every pixel black
   */

  public Color[][] init(int w, int h) {
    Color[][] pixels = new Color[w][h];
    for (int i = 0; i < pixels.length; i++) {
      for (int j = 0; j < pixels[0].length; j++) {
        pixels[i][j] = new Color(0, 0, 0);
      }
    }
    return pixels;
  }

  public void setPixel(int x, int y, Color color) {
    this.pixels[x][y] = color;
  }

  public void create() {
    String fs = System.getProperty("file.separator");
    String path = fs + "Users" + fs + "Mehdi Ammar" + fs + "Desktop" + fs + "java-ray-tracer" + fs + "file.ppm";
    try {
      BufferedWriter br = new BufferedWriter(new FileWriter(path));
      br.write(generate(this));
      br.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  /*
   * This method writes to the ppm file using the pixels 2D array filled with the colors from the render
   */

  private static String generate(Image image) {
    StringBuffer str = new StringBuffer("P3 " + image.w + " " + image.h + " \n" + "255 \n"); // Header
    for (int j = 0; j < image.pixels[0].length; j++) {
      for (int i = 0; i < image.pixels.length; i++) {
        str.append(Math.min(255, (int) image.pixels[i][j].getX()) + " " + Math.min(255, (int) image.pixels[i][j].getY())
            + " " + Math.min(255, (int) image.pixels[i][j].getZ()) + " ");
      }
      str.append("\n");
    }
    return str.toString();
  }
}
