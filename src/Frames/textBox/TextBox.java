package Frames.textBox;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings({"FieldMayBeFinal", "FieldCanBeLocal"})
public class TextBox extends AbstractTextBox implements KeyListener{

    private final int FONT_SIZE = 30;  // this variable will change relative to frame size
    private final int MAX_CHAR_SIZE = 36;  // this variable needs to stay a constant

    private int row = 0;
    private String message = "";
    private List<String> lines = new ArrayList<>();

    public TextBox(){
        super();
        this.setFocusable(true);
        this.addKeyListener(this);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setFont(new Font("Arial", Font.BOLD, this.FONT_SIZE));

        for(int i = 0; i < 3 && i < lines.size(); i++)
            g.drawString(lines.get(i + row), 20, 20 + (20 * 2 * i) + this.FONT_SIZE);
    }

    public void setMessage(String message) {
        this.message = message;
        createMessageList();
    }

    private void createMessageList(){
        String[] words = this.message.split(" ");
        StringBuilder line = new StringBuilder();
        for(int i = 0; i < words.length; i++){
            if(line.length() + words[i].length() < this.MAX_CHAR_SIZE)
                line.append(words[i]).append(" ");
            else{
                lines.add(line.toString());
                line = new StringBuilder(words[i] + " ");
            }
            if(i == words.length - 1){
                lines.add(line.toString());
                line = new StringBuilder();
            }
        }
    }

    private void resetTextBox(){
        this.row = 0;
        this.message = "";
        this.lines.clear();
        repaint();
    }

    // if the enter button is pressed
    @Override
    public void keyReleased(KeyEvent e) {
        if(e.getKeyCode() == 10){

            if(row < lines.size() - 3){
                int difference = lines.size()-3 - row;
                switch (difference) {
                    case 1 -> row++;
                    case 2 -> row = row + 2;
                    default -> row = row + 3;
                }
                repaint();
            }
            else{
                this.resetTextBox();
                this.setVisible(false);
            }
        }
    }

    // These two methods are not needed in this case
    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {}

}
