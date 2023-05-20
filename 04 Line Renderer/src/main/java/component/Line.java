package component;

import org.joml.Vector2f;
import org.joml.Vector4f;

public class Line {
    private Vector2f startPoint;
    private Vector2f endPoint;
    private Vector4f color;


    public Line(Vector2f startPoint, Vector2f endPoint) {
        this.startPoint = startPoint;
        this.endPoint = endPoint;
        this.color = new Vector4f(1.0f, 0.0f, 0.0f, 1.0f);
    }

    public Line(Vector2f startPoint, Vector2f endPoint, Vector4f color) {
        this.startPoint = startPoint;
        this.endPoint = endPoint;
        this.color = color;
    }

    public void changePos (Vector2f deltaPos) {
        this.startPoint.add(deltaPos);
        this.endPoint.add(deltaPos);
    }

    public Vector2f getStartPoint() {
        return startPoint;
    }

    public Vector2f getEndPoint() {
        return endPoint;
    }
}
