package engine;


import org.joml.Vector2f;
import org.lwjgl.BufferUtils;
import renderer.Shader;
import renderer.Texture;
import util.Time;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;

import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.opengl.GL30.*;

public class LevelEditorScene extends Scene {
    
    private float[] vertexArray = {
            //    POSITION                  COLOR
             1.0f,  1.0f,  0.0f,         1.0f, 1.0f, 1.0f, 1.0f,
             1.0f,  1.0f,  0.0f,         1.0f, 0.0f, 0.0f, 1.0f,
             1.0f,  1.0f,  0.0f,         1.0f, 0.0f, 0.0f, 1.0f,
             1.0f,  1.0f,  0.0f,         1.0f, 0.0f, 0.0f, 1.0f,
    };

    private int[] elementArray = {
        0, 1, 2, 3
    };

    private int vaoID, vboID, eboID;

    private Shader defaultShader;

    public LevelEditorScene() {

    }


    @Override
    public void init() {
        this.camera = new Camera(new Vector2f());
        defaultShader = new Shader("assets/shaders/default.glsl");
        defaultShader.compile();


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
        int vertexSizeInBytes = (positionsSize + colorSize + UVSize) * Float.BYTES;

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

        glDrawElements(GL_LINES, elementArray.length, GL_UNSIGNED_INT, 0);


        // Unbind mindent
        glDisableVertexAttribArray(0);
        glDisableVertexAttribArray(1);

        glBindVertexArray(0);

        defaultShader.detach();
    }


}
