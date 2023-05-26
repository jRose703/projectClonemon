package Frames.TextBox;

import Frames.BasicPanel;
import BattleSystem.BattleSystem;

import java.awt.*;
import java.awt.event.KeyEvent;

@SuppressWarnings({"FieldMayBeFinal", "FieldCanBeLocal"})
public class BattleMenuBox extends AbstractTextBox{
    private BattleSystem battle;

    // for code readability
    private final int LEFT = TEXT_BOX_CENTER_X * 2 / 5 - BasicPanel.FONT_SIZE;
    private final int RIGHT = TEXT_BOX_CENTER_X * 4 / 3 - BasicPanel.FONT_SIZE;
    private final int TOP = TEXT_BOX_CENTER_Y * 5 / 6 - BasicPanel.FONT_SIZE * 2 / 3;
    private final int BOTTOM = TEXT_BOX_CENTER_Y * 3 / 2 - BasicPanel.FONT_SIZE * 2 / 3;

    // cursor coordinates
    private int cursor_x = LEFT;
    private int cursor_y = TOP;
    
    public BattleMenuBox(){
        super();
        this.addKeyListener(this);
    }
    
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setFont(new Font("Arial", Font.BOLD, BasicPanel.FONT_SIZE));

        g.drawString("Fight", TEXT_BOX_CENTER_X * 2 / 5, TEXT_BOX_CENTER_Y * 5 / 6);
        g.drawString("Clmn", TEXT_BOX_CENTER_X * 4 / 3, TEXT_BOX_CENTER_Y * 5 / 6);
        g.drawString("Items", TEXT_BOX_CENTER_X * 2 / 5, TEXT_BOX_CENTER_Y * 3 / 2);
        g.drawString("Run", TEXT_BOX_CENTER_X * 4 / 3, TEXT_BOX_CENTER_Y * 3 / 2);

        Polygon triangle = new Polygon(new int[] {this.cursor_x, this.cursor_x + BasicPanel.FONT_SIZE * 2 / 3 , this.cursor_x},
                new int[] {this.cursor_y, this.cursor_y + BasicPanel.FONT_SIZE / 3, this.cursor_y + BasicPanel.FONT_SIZE * 2 / 3}, 3);
        g.drawPolygon(triangle);
        g.fillPolygon(triangle);
    }

    private void chooseAction(){
        if(cursor_x ==  LEFT && cursor_y == TOP)
            this.battle.round("fight");
        else if(cursor_x ==  LEFT && cursor_y == BOTTOM)
            System.out.println("ITEMS");
        else if(cursor_x == RIGHT && cursor_y == TOP)
            System.out.println("CLONEMONS");
        else if(cursor_x == RIGHT && cursor_y == BOTTOM)
            this.battle.round("run");
        else throw new IllegalArgumentException("Cursor coorinates are out of bound!");
    }

    public void setBattle(BattleSystem newBattle){
        this.battle = newBattle;
    }


    // if the enter button is pressed
    @Override
    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()) {
            case 10 -> chooseAction();
            case 37 -> {
                cursor_x = LEFT;
                repaint();
            }
            case 38 -> {
                cursor_y = TOP;
                repaint();
            }
            case 39 -> {
                cursor_x = RIGHT;
                repaint();
            }
            case 40 -> {
                cursor_y = BOTTOM;
                repaint();
            }
        }
    }

    // These two methods are not needed in this case
    @Override
    public void keyPressed(KeyEvent e) {}

    @Override
    public void keyTyped(KeyEvent e) {}

}
