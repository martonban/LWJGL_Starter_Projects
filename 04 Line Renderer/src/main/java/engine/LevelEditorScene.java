package engine;

import component.Square;
import core.converter.SquareToLineRenderer;
import org.joml.Vector2f;
import org.lwjgl.BufferUtils;
import renderer.Shader;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;

import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.opengl.GL30.*;

public class LevelEditorScene extends Scene {
    
    private float[] vertexArray = {};

    private int[] elementArray = {};

    private int vaoID, vboID, eboID;

    private Shader defaultShader;

    private Square square;
    private SquareToLineRenderer squareToLineRenderer;

    public LevelEditorScene() {

    }


    @Override
    public void init() {
        this.camera = new Camera(new Vector2f());
        defaultShader = new Shader("assets/shaders/default.glsl");
        defaultShader.compile();

        square = new Square(1, new Vector2f(0.0f, 0.0f),
                new Vector2f(0.2f, 0.2f));
        squareToLineRenderer = new SquareToLineRenderer(square);
        this.vertexArray = squareToLineRenderer.getVertexArray();
        this.elementArray = squareToLineRenderer.getElementArray();

        // VAO létrehozása
        vaoID = glGenVertexArrays();
        glBindVertexArray(vaoID);

        // FloatBuffer Létrehozása
        FloatBuffer vertexBuffer = BufferUtils.createFloatBuffer(vertexArray.length);
        vertexBuffer.put(vertexArray).flip();

        // VBO Létrehozása majd a Buffer feltöltése
        vboID = glGenBuffers();
        glBindBuffer(GL_ARRAY_BUFFER, vboID);
        glBufferData(GL_ARRAY_BUFFER, vertexBuffer, GL_STATIC_DRAW);

        IntBuffer elementBuffer = BufferUtils.createIntBuffer(elementArray.length);
        elementBuffer.put(elementArray).flip();

        eboID = glGenBuffers();
        glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, eboID);
        glBufferData(GL_ELEMENT_ARRAY_BUFFER, elementBuffer, GL_STATIC_DRAW);

        int positionsSize = 3;
        int colorSize = 4;
        int UVSize = 2;
        int vertexSizeInBytes = (positionsSize + colorSize) * Float.BYTES;

        glVertexAttribPointer(0, positionsSize, GL_FLOAT, false, vertexSizeInBytes, 0);
        glEnableVertexAttribArray(0);

        glVertexAttribPointer(1, colorSize, GL_FLOAT, false, vertexSizeInBytes, positionsSize * Float.BYTES);
        glEnableVertexAttribArray(1);
    }


    @Override
    public void update(float dt) {
        camera.position.x -= dt * 50.0f;
        camera.position.y -= dt * 30.0f;

        defaultShader.use();


        // Binduljuk a VAO-t
        glBindVertexArray(vaoID);

        // Elérhetővé tesszük a vertex atribute pointerst
        glEnableVertexAttribArray(0);
        glEnableVertexAttribArray(1);

        glDrawElements(GL_LINE_STRIP, vertexArray.length, GL_UNSIGNED_INT, 0);


        // Unbind mindent
        glDisableVertexAttribArray(0);
        glDisableVertexAttribArray(1);

        glBindVertexArray(0);

        defaultShader.detach();
    }
}
