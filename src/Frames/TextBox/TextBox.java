package Frames.TextBox;

import Frames.BasicPanel;
import Observer.ObserveType;
import Observer.Observer;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings({"FieldMayBeFinal", "FieldCanBeLocal"})
public class TextBox extends AbstractTextBox {

    private final int MAX_CHAR_SIZE = 36;  // this variable needs to stay a constant

    private int row = 0;
    private String message = "";
    private List<String> lines = new ArrayList<>();
    private DialogueType dialogueType;
    private Observer stateMachineObserver;

    public TextBox(Observer stateMachineObserver) {
        super();
        this.stateMachineObserver = stateMachineObserver;
        this.setVisible(false);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setFont(new Font("Arial", Font.BOLD, BasicPanel.FONT_SIZE));

        for (int i = 0; i < 3 && i < lines.size(); i++)
            g.drawString(lines.get(i + row), 20, 20 + (20 * 2 * i) + BasicPanel.FONT_SIZE);
    }

    public void setMessage(String message) {
        this.message = message;
        dialogueType = DialogueType.TEXT;
        this.createMessageList();
        this.setVisible(true);
    }

    public void setMessage(String message, DialogueType type) {
        this.message = message;
        dialogueType = type;
        this.createMessageList();
        this.setVisible(true);
    }

    private void createMessageList() {
        String[] words = this.message.split(" ");
        StringBuilder line = new StringBuilder();
        for (int i = 0; i < words.length; i++) {
            if (line.length() + words[i].length() < this.MAX_CHAR_SIZE)
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

    private void resetTextBox() {
        this.row = 0;
        this.message = "";
        this.lines.clear();
        repaint();
    }

    private void endDialogue() {
        stateMachineObserver.update(ObserveType.DIALOGUE_END, dialogueType);
    }

    // if the enter button is pressed
    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == 10) {

            if (row < lines.size() - 3) {
                int difference = lines.size() - 3 - row;
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
                endDialogue();
            }
        }
    }
}
