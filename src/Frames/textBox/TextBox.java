package Frames.textBox;

import Frames.textBox.interfaces.DialogBox;

import java.awt.*;

public class TextBox extends AbstractTextBox implements DialogBox {

    private final int FONT_SIZE = 30;
    private String message;

    public TextBox(){
        super();
        message = "";
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setFont(new Font("Arial", Font.BOLD, this.FONT_SIZE));

        g.drawString(message, 20, 20 + this.FONT_SIZE);
    }

    @Override
    public void setMessage(String message) {
        this.message = message;
    }
}
