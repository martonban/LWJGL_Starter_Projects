package component;

import org.joml.Vector2f;
import org.joml.Vector4f;

import java.util.ArrayList;

public class Square {

    private int id;
    private Vector2f pos;
    private Vector2f size;
    private Vector4f color;
    private ArrayList<Line> lines;

    public Square(int id, Vector2f pos, Vector2f size) {
        this.id = id;
        this.pos = pos;
        this.size = size;
        this.color = new Vector4f(1.0f, 0.0f, 0.0f, 1.0f);
        this.lines = uploadLines(pos, size);
    }


    /*
              4
         ___________
        |           |
    1   |           |   3
        |           |
        |___________|
              2
     */

    public ArrayList<Line> uploadLines (Vector2f pos, Vector2f size){
        ArrayList<Line> lines = new ArrayList<>();

        Vector2f leftDownCorner = new Vector2f(pos.x, pos.y);
        Vector2f rightDownCorner = new Vector2f(pos.x + size.x, pos.y);
        Vector2f leftUpCorner = new Vector2f(pos.x, pos.y + size.y);
        Vector2f rightUpCorner = new Vector2f(pos.x + size.x, pos.y + size.y);

        Line line1 = new Line(leftDownCorner, leftUpCorner);
        Line line2 = new Line(leftDownCorner, rightDownCorner);
        Line line3 = new Line(rightDownCorner, rightUpCorner);
        Line line4 = new Line(leftUpCorner, rightUpCorner);

        lines.add(line1);
        lines.add(line2);
        lines.add(line3);
        lines.add(line4);

        return lines;
    }
}
