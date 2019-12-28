import java.util.ArrayList;

public class Main {

  final static int WIDTH = 1920;
  final static int HEIGHT = 1080;

  public static void main(String args[]) {

    ArrayList<Object> objects = new ArrayList<Object>();

    String fs = System.getProperty("file.separator");
    String earth =
        fs + "Users" + fs + "Mehdi Ammar" + fs + "Desktop" + fs + "java-ray-tracer" + fs + "tex" + fs + "2k_earth_daymap.jpg";

    /*
     * Add objects to the scene
     */
    objects.add(new Sphere(0.2, new Point(0, 0, 0), Color.toRGB("#42f557"), true, true, null));
    objects.add(new Sphere(0.1, new Point(-0.2, -0.3, -0.2), Color.toRGB("#f54296"), true, true, null));
    objects.add(new Sphere(0.1, new Point(0.4, 0, 0), Color.toRGB("#3061ff"), true, true, null));
    objects.add(new Sphere(0.04, new Point(0.1, 0.1, -0.3), Color.toRGB("#ff7f30"), true, true, null));

    objects.add(new Sphere(1.5, new Point(0.2, -0.4, 3), Color.toRGB("#42f557"), false, false, earth));

    /*
     * Lightning setup
     */

    objects.add(new Vector(-2.0, -0.4, -3));

    /*
     * Scene creation and rendering
     */

    Scene scene = new Scene(new Point(0, 0, -1), WIDTH, HEIGHT, objects);

    new Engine<Object>().render(scene).create();

  }
}
