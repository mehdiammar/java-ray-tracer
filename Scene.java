import java.util.ArrayList;

public class Scene {
  
  private Point camera;
  private int w, h;
  private ArrayList<Object> objects;

  public Scene(Point camera, int w, int h, ArrayList<Object> objects) {
    this.camera = camera;
    this.w = w;
    this.h = h;
    this.objects = objects;
  }

  public Point getCamera() {
    return this.camera;
  }

  public ArrayList<Object> getObjects() {
    return this.objects;
  }

  public int getW() {
    return w;
  }

  public int getH() {
    return h;
  }
}
