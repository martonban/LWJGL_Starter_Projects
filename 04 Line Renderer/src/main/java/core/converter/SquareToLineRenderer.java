package core.converter;

import component.Square;

public class SquareToLineRenderer {

    private Square square;
    private float[] vertexArray = new float[96];
    private float[] elementArray = {
          0, 1,
          1, 2,
          3, 2,
          0, 3
    };


    public void SquareToLineRenderer (Square square) {

    }

    public float[] getVertexArray() {
        return vertexArray;
    }

    public float[] getElementArray() {
        return elementArray;
    }
}
