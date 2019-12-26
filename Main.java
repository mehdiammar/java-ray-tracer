import java.util.ArrayList;

public class Main {
  
  static int WIDTH = 1920;
  static int HEIGHT = 1080;

  public static void main(String args[]) {

    ArrayList<Object> objects = new ArrayList<Object>();
    
    objects.add(new Sphere(0.2, new Point(0, 0, 0), Color.toRGB("#5091C0"), true));
    objects.add(new Sphere(0.1, new Point(0.4, 0, 0), Color.toRGB("#FFFF00"), true));
    objects.add(new Vector(-5.0, -15.0, -1)); // scene point light
    
    Scene scene = new Scene(new Point(0, 0, -1), WIDTH, HEIGHT, objects);
    
    new Engine<Object>().render(scene).create();
    
  }
}
