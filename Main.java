import java.util.ArrayList;

public class Main {
  
  final static int WIDTH = 1920;
  final static int HEIGHT = 1080;

  public static void main(String args[]) {

    ArrayList<Object> objects = new ArrayList<Object>();
    
    String fs = System.getProperty("file.separator");
    String texture_path = fs + "Users" + fs + "Mehdi Ammar" + fs + "Desktop" + fs + "java-ray-tracer" + fs + "2k_earth_daymap.jpg";
    
    /*
     * Add objects to the scene
     */
    
    objects.add(new Sphere(0.2, new Point(0, 0, 0), Color.toRGB("#5091C0"), true, texture_path));
    objects.add(new Sphere(0.1, new Point(-0.2, -0.3, -0.2), Color.toRGB("#224120"), true, null));
    objects.add(new Sphere(0.1, new Point(0.4, 0, 0), Color.toRGB("#FFFF00"), true, null));
    
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
