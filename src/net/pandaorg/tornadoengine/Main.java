package net.pandaorg.tornadoengine;

import net.pandaorg.tornadoengine.core.Game;
import net.pandaorg.tornadoengine.core.GameWindow;
import net.pandaorg.tornadoengine.graphics.Renderer;

public class Main {
    public static void main(String[] args) {
        Game game = new Game(800,600,"abc") {
            @Override
            public void init() {

            }

            @Override
            public void render(Renderer renderer) {

            }
        };
    }
}
