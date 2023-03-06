package engine;

import org.lwjgl.Version;
import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.opengl.GL;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.system.MemoryUtil.NULL;

public class Window {

    // Alap adatok megadása
    private int widt, height;
    private String title;


    private static Window window = null;

    // Ez egy memória cím lesz
    private long glfwWindow;

    // A konstruktor azért private, mert azt szeretnénk, hogy Singelton legyen
    private Window() {
        this.widt = 1920;
        this.height = 1080;
        this.title = "2D Texture Loader";
    }

    // Singelton miatt amikor meghívjuk akkor egyeszerre létre is hozzuk
    public static Window get() {
        if (Window.window == null){
            Window.window = new Window();
        }

        return Window.window;
    }

    public void run(){
        System.out.println("Hello LWJGL " + Version.getVersion() + "!");

        init();
        loop();
    }

    public void init(){
        // Ha hiba lép fel le kell tudnunk kezelni.
        GLFWErrorCallback.createPrint(System.err).set();

        // Inicializáljuk a GLFW-t
        if(!glfwInit()){
            throw new IllegalStateException("Unable to initialize GLFW");
        }

        // Konfiguráljuk a GLFW-t
        glfwDefaultWindowHints();
        glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE);
        glfwWindowHint(GLFW_RESIZABLE, GLFW_TRUE);
        glfwWindowHint(GLFW_MAXIMIZED, GLFW_TRUE);


        // Létrehozzuk az abalakot
        glfwWindow = glfwCreateWindow(this.widt, this.height, this.title, NULL, NULL);

        if(glfwWindow == NULL){
            throw new IllegalStateException("Failed to load GLFW !");
        }

        // Létrhozzuk az OpenGL kontextust
        glfwMakeContextCurrent(glfwWindow);

        // Engedélyezzük a 'v-sync'-et
        glfwSwapInterval(1);

        // Láthatóvá tesszük az abalkot
        glfwShowWindow(glfwWindow);

        // FONTOS!!!!
        GL.createCapabilities();

    }


    public void loop(){

        while(!glfwWindowShouldClose(glfwWindow)) {
            // Poll Events
            glfwPollEvents();

            glClearColor(1.0f, 0.0f, 0.0f, 1.0f);
            glClear(GL_COLOR_BUFFER_BIT);

            glfwSwapBuffers(glfwWindow);
        }
    }
}
