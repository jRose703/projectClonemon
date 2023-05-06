package Frames.textBox;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class TextBox extends AbstractTextBox{

    private final int FONT_SIZE = 30;  // this variable will change relative to frame size
    private final int MAX_CHAR_SIZE = 39;  // this variable needs to stay a constant
    private String message;
    private List<String> lines;

    public TextBox(){
        super();
        message = "";
        lines = new ArrayList<>();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setFont(new Font("Arial", Font.BOLD, this.FONT_SIZE));

        for(int i = 0; i < lines.size(); i++)
            g.drawString(lines.get(i), 20, 20 + (20 * 2 * i) + this.FONT_SIZE);
    }

    public void setMessage(String message) {
        this.message = message;
        createMessageList();
    }

    public void createMessageList(){
        String[] words = this.message.split(" ");
        String line = "";
        for(int i = 0; i < words.length; i++){
            if(line.length() + words[i].length() < this.MAX_CHAR_SIZE)
                line += words[i] + " ";
            else{
                lines.add(line);
                line = words[i] + " ";
            }
            if(i == words.length - 1){
                lines.add(line);
                line = "";
            }
        }

        // solely for testing
        for(String text : lines){
            System.out.println(text);
        }

    }

}
