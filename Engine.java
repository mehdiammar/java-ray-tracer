import java.util.ArrayList;
import java.util.List;

public class Engine<E> {

  private static double ambient_coef = 0.2;
  private static double ambient_control = 0.4;
  private static double specular_coef = 32;
  private static double reflectivty_factor = 0.2;
  private static int MAX = 5;
  private int ctrl = 0;

  public Image render(Scene scene) {
    int w = scene.getW();
    int h = scene.getH();

    double aspect_ratio = (double) w / (double) h;

    double x0 = -1.0;
    double x1 = 1.0;
    double xstep = (x1 - x0) / (double) (w - 1);

    double y0 = -1.0 / aspect_ratio;
    double y1 = 1.0 / aspect_ratio;
    double ystep = (y1 - y0) / (double) (h - 1);

    Point camera = scene.getCamera();
    Image pixels = new Image(w, h);

    double x, y;

    /*
     * Generate rays from observer (camera)
     */

    for (int j = 0; j < h; j++) {
      y = y0 + j * ystep;
      for (int i = 0; i < w; i++) {
        ctrl = 0;
        x = x0 + i * xstep;
        Ray ray = new Ray(camera, (new Point(x, y, 0).sub(camera)));
        pixels.setPixel(i, j, this.ray_trace(ray, scene));
      }
    }
    return pixels;
  }

  public Color ray_trace(Ray ray, Scene scene) {

    Color color = new Color(0, 0, 0);
    Color reflected_color = new Color(0, 0, 0);
    Sphere obj = (Sphere) this.find_nearest_obj(ray, scene).get(0);
    Vector l = null;
    double obj_dist = (double) this.find_nearest_obj(ray, scene).get(1);

    if (obj == null)
      return color;

    for (Object o : scene.getObjects()) {
      if (o instanceof Vector) {
        l = (Vector) o;
      }
    }

    /*
     * Calculate specific fragment elements
     */

    Vector hit_pos = ray.getPoint().add(ray.getDir().mult(obj_dist));
    Vector normal = hit_pos.sub(obj.getCenter());
    Ray reflected_ray = get_reflected_ray(normal, ray, hit_pos, l);
    Ray reflected_light = get_reflected_light_ray(normal, ray, hit_pos, l);
    Vector view = normal.norm().mult(2).mult(-1 * normal.norm().dot(ray.getDir()));

    if (obj.isReflective() && ctrl < MAX) {
      ctrl++;
      reflected_color = ray_trace(reflected_ray, scene);
    }

    color = colorAt(obj, scene, normal, reflected_color, view, reflected_light, hit_pos); // return fragment color

    return color;
  }

  public List<Object> find_nearest_obj(Ray ray, Scene scene) {
    double obj_dist = 0;
    Sphere obj_hit = null;
    List<Object> pair = new ArrayList<Object>();
    for (Object s : scene.getObjects()) { // Find scene objects
      if (s instanceof Sphere) {
        double dist = ((Sphere) s).intersects(ray);
        if (dist > 0 && (obj_hit == null || dist < obj_dist)) {
          obj_dist = dist;
          obj_hit = (Sphere) s;
        }
      }
    }
    pair.add(obj_hit);
    pair.add(obj_dist);
    return pair;
  }

  public Ray get_reflected_ray(Vector normal, Ray ray, Vector hit_pos, Vector l) {
    return new Ray(new Point(hit_pos.x, hit_pos.y, hit_pos.z),
        ray.getDir().sub(normal.norm().mult(2).mult(normal.norm().dot(ray.getDir()))));
  }

  public Ray get_reflected_light_ray(Vector normal, Ray ray, Vector hit_pos, Vector l) {
    return new Ray(new Point(hit_pos.x, hit_pos.y, hit_pos.z), l.sub(normal.norm().mult(2).mult(normal.norm().dot(l))));
  }

  public Color colorAt(Sphere obj, Scene scene, Vector normal, Color reflected_color, Vector view, Ray reflected,
      Vector hit_pos) {
    Vector l = null;
    Vector frag_color = null;
    for (Object o : scene.getObjects()) {
      if (o instanceof Vector) {
        l = (Vector) o;
        if (l != null) {
          double diffuse = l.norm().dot(normal.norm());
          double specular = Math.pow(view.norm().dot(reflected.getDir()), specular_coef);
          if (diffuse < 0) {
            diffuse = 0;
            specular = 0;
          }
          if (obj.isTextured()) {
            frag_color = (obj.getMapping().findTextureColorAt(obj.getMapping().getWidth(), obj.getMapping().getHeight(),
                obj, obj.getMapping(), hit_pos))
                    .mult(ambient_control * ambient_coef + (1 - ambient_coef) * diffuse + specular)
                    .add((reflected_color).mult(reflectivty_factor));
          } else
            frag_color = (obj.getColor()).mult(ambient_control * ambient_coef + (1 - ambient_coef) * diffuse + specular)
                .add((reflected_color).mult(reflectivty_factor));
        }
      }
    }
    return new Color(frag_color.x, frag_color.y, frag_color.z);
  }
}
