package component;

import org.joml.Vector2f;
import org.joml.Vector4f;

import java.util.ArrayList;

public class Square {

    private int id;
    private Vector2f pos;
    private Vector2f size;
    private Vector4f color;
    private ArrayList<Vector2f> points;

    public Square(int id, Vector2f pos, Vector2f size) {
        this.id = id;
        this.pos = pos;
        this.size = size;
        this.color = new Vector4f(1.0f, 0.0f, 0.0f, 1.0f);
        this.points = uploadLines(pos, size);
    }

    /*
         1_________2
         |         |
         |         |
        0|_________|3
     */

    public ArrayList<Vector2f> uploadLines (Vector2f pos, Vector2f size){
        ArrayList<Vector2f> points = new ArrayList<>();

        Vector2f leftDownCorner = new Vector2f(pos.x, pos.y);
        Vector2f rightDownCorner = new Vector2f(pos.x + size.x, pos.y);
        Vector2f leftUpCorner = new Vector2f(pos.x, pos.y + size.y);
        Vector2f rightUpCorner = new Vector2f(pos.x + size.x, pos.y + size.y);

        points.add(leftDownCorner);
        points.add(leftUpCorner);
        points.add(rightUpCorner);
        points.add(rightDownCorner);

        return points;
    }

    public void changePos(Vector2f delta) {
        for (Vector2f point : points) {
            point.add(delta);
        }
    }

    public ArrayList<Vector2f> getPoints() {
        return points;
    }
}
