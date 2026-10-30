
package com.example.spaceshooter;

import static org.lwjgl.glfw.GLFW.glfwGetPrimaryMonitor;
import static org.lwjgl.glfw.GLFW.glfwGetVideoMode;
import static org.lwjgl.glfw.GLFW.glfwInit;
import static org.lwjgl.glfw.GLFW.glfwMakeContextCurrent;
import static org.lwjgl.glfw.GLFW.glfwPollEvents;
import static org.lwjgl.glfw.GLFW.glfwSetErrorCallback;
import static org.lwjgl.glfw.GLFW.glfwShowWindow;
import static org.lwjgl.glfw.GLFW.glfwSwapBuffers;
import static org.lwjgl.glfw.GLFW.glfwSwapInterval;
import static org.lwjgl.glfw.GLFW.glfwTerminate;
import static org.lwjgl.glfw.GLFW.glfwWindowShouldClose;
import static org.lwjgl.opengl.GL11.GL_COLOR_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.GL_DEPTH_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.glClear;
import static org.lwjgl.opengl.GL11.glClearColor;
import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.glfw.GLFWVidMode;
import org.lwjgl.opengl.GL;

public class SpaceShooter {

    private GLFWErrorCallback errorCallback;
    private Window window;

    public void run() {
        try {
            glfwSetErrorCallback(errorCallback = GLFWErrorCallback.createPrint(System.err));

            if (!glfwInit()) {
                throw new IllegalStateException("Unable to initialize GLFW");
            }

            GLFWVidMode vidmode = glfwGetVideoMode(glfwGetPrimaryMonitor());
            window = new Window(vidmode.width() - 100, vidmode.height() - 100, "Space Shooter");
            
            glfwMakeContextCurrent(window.getWindowHandle());
            glfwSwapInterval(1);

            glfwShowWindow(window.getWindowHandle());

            loop();

            window.destroy();
        } finally {
            glfwTerminate();
            errorCallback.free();
        }
    }


    private void loop() {
        GL.createCapabilities();
        glClearColor(0.0f, 1.0f, 0.0f, 0.0f);

        while (!glfwWindowShouldClose(window.getWindowHandle())) {
            glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);

            glfwSwapBuffers(window.getWindowHandle());
            glfwPollEvents();
        }
    }


}