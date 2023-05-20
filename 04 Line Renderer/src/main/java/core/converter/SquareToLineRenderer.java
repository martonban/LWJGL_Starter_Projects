package core.converter;

import component.Line;
import component.Square;
import org.joml.Vector2f;

import java.util.ArrayList;

public class SquareToLineRenderer {

    private Square square;
    private float[] vertexArray = new float[28];
    private int[] elementArray = {
          0, 1,
          1, 2,
          2, 3,
          0, 3
    };

    public SquareToLineRenderer(Square square) {
        ArrayList<Vector2f> points = square.getPoints();
        ArrayList<Float> buffer = new ArrayList<>();
        for(Vector2f point: points) {
            buffer.add(point.x);
            buffer.add(point.y);
            buffer.add(0.0f);
            buffer.add(1.0f);
            buffer.add(0.0f);
            buffer.add(0.0f);
            buffer.add(1.0f);
        }
        convert(buffer);
    }

    public void convert(ArrayList<Float> array) {
        for(int i = 0; i < this.vertexArray.length; i++) {
            this.vertexArray[i] = array.get(i);
        }
    }

    public float[] getVertexArray() {
        return vertexArray;
    }

    public int[] getElementArray() {
        return elementArray;
    }
}
