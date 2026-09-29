import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

/*
 * Created by Abraham Campbell on 15/01/2020.
 *   Copyright (c) 2020  Abraham Campbell

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
   
   (MIT LICENSE ) e.g do what you want with this :-) 
 */ 

//Singeton pattern
public class Controller implements KeyListener, MouseListener, MouseMotionListener {
        
	   // Player 1
	   private static boolean KeyAPressed= false;
	   private static boolean KeySPressed= false;
	   private static boolean KeyDPressed= false;
	   private static boolean KeyWPressed= false;
	   private static boolean KeySpacePressed= false;
	   private static boolean KeyShiftPressed= false;
	   
	   private static boolean MousePressed = false;
	   private static int MouseX = 0;
	   private static int MouseY = 0;
	   
	   // Player 2
	   private static boolean KeyUPPressed= false;
	   private static boolean KeyDOWNPressed= false;
	   private static boolean KeyLEFTPressed= false;
	   private static boolean KeyRIGHTPressed= false;
	   private static boolean KeyEnterPressed= false;
	   private static boolean KeyCtrlPressed= false;
	   
	   // System
	   private static boolean KeyF5Pressed = false;
	   private static boolean KeyF9Pressed = false;
	   
	   private static final Controller instance = new Controller();
	   
	 private Controller() { 
	}
	 
	 public static Controller getInstance(){
	        return instance;
	    }
	   
	@Override
	public void keyTyped(KeyEvent e) { 
	}

	@Override
	public void keyPressed(KeyEvent e) 
	{ 
		int key = e.getKeyCode();
		switch (key) 
		{
			// Player 1
			case KeyEvent.VK_A: setKeyAPressed(true); break;  
			case KeyEvent.VK_S: setKeySPressed(true); break;
			case KeyEvent.VK_W: setKeyWPressed(true); break;
			case KeyEvent.VK_D: setKeyDPressed(true); break;
			case KeyEvent.VK_SPACE: setKeySpacePressed(true); break;
			case KeyEvent.VK_SHIFT: setKeyShiftPressed(true); break;
			
			// Player 2
			case KeyEvent.VK_UP: setKeyUPPressed(true); break;
			case KeyEvent.VK_DOWN: setKeyDownPressed(true); break;
			case KeyEvent.VK_LEFT: setKeyLeftPressed(true); break;
			case KeyEvent.VK_RIGHT: setKeyRightPressed(true); break;
			case KeyEvent.VK_ENTER: setKeyEnterPressed(true); break;
			case KeyEvent.VK_CONTROL: setKeyCtrlPressed(true); break;
			
			// System
			case KeyEvent.VK_F5: setKeyF5Pressed(true); break;
			case KeyEvent.VK_F9: setKeyF9Pressed(true); break;
		}  
	}

	@Override
	public void keyReleased(KeyEvent e) 
	{ 
		int key = e.getKeyCode();
		switch (key) 
		{
			// Player 1
			case KeyEvent.VK_A: setKeyAPressed(false); break;  
			case KeyEvent.VK_S: setKeySPressed(false); break;
			case KeyEvent.VK_W: setKeyWPressed(false); break;
			case KeyEvent.VK_D: setKeyDPressed(false); break;
			case KeyEvent.VK_SPACE: setKeySpacePressed(false); break;
			case KeyEvent.VK_SHIFT: setKeyShiftPressed(false); break;
			
			// Player 2
			case KeyEvent.VK_UP: setKeyUPPressed(false); break;
			case KeyEvent.VK_DOWN: setKeyDownPressed(false); break;
			case KeyEvent.VK_LEFT: setKeyLeftPressed(false); break;
			case KeyEvent.VK_RIGHT: setKeyRightPressed(false); break;
			case KeyEvent.VK_ENTER: setKeyEnterPressed(false); break;
			case KeyEvent.VK_CONTROL: setKeyCtrlPressed(false); break;
			
			// System
			case KeyEvent.VK_F5: setKeyF5Pressed(false); break;
			case KeyEvent.VK_F9: setKeyF9Pressed(false); break;
		}  
	}

	// Player 1 Getters/Setters
	public boolean isKeyAPressed() { return KeyAPressed; }
	public void setKeyAPressed(boolean keyAPressed) { KeyAPressed = keyAPressed; }
	public boolean isKeySPressed() { return KeySPressed; }
	public void setKeySPressed(boolean keySPressed) { KeySPressed = keySPressed; }
	public boolean isKeyDPressed() { return KeyDPressed; }
	public void setKeyDPressed(boolean keyDPressed) { KeyDPressed = keyDPressed; }
	public boolean isKeyWPressed() { return KeyWPressed; }
	public void setKeyWPressed(boolean keyWPressed) { KeyWPressed = keyWPressed; }
	public boolean isKeySpacePressed() { return KeySpacePressed; }
	public void setKeySpacePressed(boolean keySpacePressed) { KeySpacePressed = keySpacePressed; } 
	public boolean isKeyShiftPressed() { return KeyShiftPressed; }
	public void setKeyShiftPressed(boolean keyShiftPressed) { KeyShiftPressed = keyShiftPressed; }
	
	// Player 2 Getters/Setters
	public boolean isKeyUPPressed() { return KeyUPPressed; }
	public void setKeyUPPressed(boolean keyUPPressed) { KeyUPPressed = keyUPPressed; }
	public boolean isKeyDownPressed() { return KeyDOWNPressed; }
	public void setKeyDownPressed(boolean keyDownPressed) { KeyDOWNPressed = keyDownPressed; }
	public boolean isKeyLeftPressed() { return KeyLEFTPressed; }
	public void setKeyLeftPressed(boolean keyLeftPressed) { KeyLEFTPressed = keyLeftPressed; }
	public boolean isKeyRightPressed() { return KeyRIGHTPressed; }
	public void setKeyRightPressed(boolean keyRightPressed) { KeyRIGHTPressed = keyRightPressed; }
	public boolean isKeyEnterPressed() { return KeyEnterPressed; }
	public void setKeyEnterPressed(boolean keyEnterPressed) { KeyEnterPressed = keyEnterPressed; }
	public boolean isKeyCtrlPressed() { return KeyCtrlPressed; }
	public void setKeyCtrlPressed(boolean keyCtrlPressed) { KeyCtrlPressed = keyCtrlPressed; }
	
	// System Getters/Setters
	public boolean isKeyF5Pressed() { return KeyF5Pressed; }
	public void setKeyF5Pressed(boolean keyF5Pressed) { KeyF5Pressed = keyF5Pressed; }
	public boolean isKeyF9Pressed() { return KeyF9Pressed; }
	public void setKeyF9Pressed(boolean keyF9Pressed) { KeyF9Pressed = keyF9Pressed; }
	 
	// Mouse Getters/Setters
	public boolean isMousePressed() { return MousePressed; }
	public void setMousePressed(boolean mousePressed) { MousePressed = mousePressed; }
	public int getMouseX() { return MouseX; }
	public int getMouseY() { return MouseY; }

	@Override
	public void mouseClicked(MouseEvent e) {}

	@Override
	public void mousePressed(MouseEvent e) { setMousePressed(true); }

	@Override
	public void mouseReleased(MouseEvent e) { setMousePressed(false); }

	@Override
	public void mouseEntered(MouseEvent e) {}

	@Override
	public void mouseExited(MouseEvent e) {}

	@Override
	public void mouseDragged(MouseEvent e) {
		MouseX = e.getX();
		MouseY = e.getY();
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		MouseX = e.getX();
		MouseY = e.getY();
	}
	 
}

/*
 * 
 * KEYBOARD :-) . can you add a mouse or a gamepad 

 *@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ @@@@@@@@@@@@@@@

  @@@     @@@@    @@@@    @@@@    @@@@     @@@     @@@     @@@     @@@     @@@  

  @@@     @@@     @@@     @@@@     @@@     @@@     @@@     @@@     @@@     @@@  

  @@@     @@@     @@@     @@@@    @@@@     @@@     @@@     @@@     @@@     @@@  

@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@

@     @@@     @@@     @@@      @@      @@@     @@@     @@@     @@@     @@@     @

@     @@@   W   @@@     @@@      @@      @@@     @@@     @@@     @@@     @@@     @

@@    @@@@     @@@@    @@@@    @@@@    @@@@     @@@     @@@     @@@     @@@     @

@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@N@@@@@@@@@@@@@@@@@@@@@@@@@@@

@@@     @@@      @@      @@      @@      @@@     @@@     @@@     @@@     @@@    

@@@   A   @@@  S     @@  D     @@      @@@     @@@     @@@     @@@     @@@     @@@    

@@@@ @  @@@@@@@@@@@@ @@@@@@@    @@@@@@@@@@@@    @@@@@@@@@@@@     @@@@   @@@@@   

    @@@     @@@@    @@@@    @@@@    $@@@     @@@     @@@     @@@     @@@     @@@

    @@@ $   @@@      @@      @@ /Q   @@ ]M   @@@     @@@     @@@     @@@     @@@

    @@@     @@@      @@      @@      @@      @@@     @@@     @@@     @@@     @@@

@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@

@       @@@                                                @@@       @@@       @

@       @@@              SPACE KEY       @@@        @@ PQ     

@       @@@                                                @@@        @@        

@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
 * 
 * 
 * 
 * 
 * 
 */
