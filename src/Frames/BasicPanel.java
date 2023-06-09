package Frames;

import BattleSystem.BattleSystem;
import BattleSystem.Fighter;
import BattleSystem.Fighters.Exorcist;
import BattleSystem.enums.FightingSide;
import Entity.Entities.OpponentEntity;
import Entity.Entities.PlayerEntity;
import Entity.FighterInventory;
import Frames.BattleUI.BattlePane;
import Frames.WorldUI.WorldPane;
import Observer.Observer;
import ReadAndWrite.PlayerOperations.ReadPlayerFromJson;
import Worlds.World;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Timer;
import java.util.TimerTask;

public class BasicPanel extends JPanel implements KeyListener {

	// Screen variables setup
	public static final int SCREENHEIGHT = 540;
	public static final int SCREENWIDTH = SCREENHEIGHT;
	public static final int FONT_SIZE = SCREENWIDTH / 18;
	public static final Dimension SCREENSIZE = new Dimension(SCREENWIDTH, SCREENHEIGHT);

	private final WorldPane worldPane;
	private final BattlePane battlePane;
	private final Observer stateMachineObserver;
	private final Timer timer;
	private final PlayerEntity player;
	private int keyListenerCooldown = 0;

	/**
	 * Container with the scenes: worldPane, battlePane.
	 * The main KeyListener and the tickable method are called from here.
	 * Creates and holds the player.
	 */
	public BasicPanel(World world, Observer stateMachineObserver) {
		this.stateMachineObserver = stateMachineObserver;

		// Creates the tick with 20 ticks per second
		timer = new Timer();
		this.startTickable();

		// Creates the player
		player = ReadPlayerFromJson.readPlayerFromFile("player");
		if (player.getPlayerFighters().getSize() < 1)
            player.addToFighterInventory(new Exorcist("PlayerOne", 0, FightingSide.PLAYER, 10, 5, 2, 5));

		// Creates the graphical world
		this.worldPane = new WorldPane(world, player, stateMachineObserver);
		this.worldPane.setBounds(0, 0, SCREENWIDTH, SCREENHEIGHT);
		this.add(worldPane);

		// Creates the graphical battle
		this.battlePane = new BattlePane(player.getPlayerFighters(), player.getInventory());
        this.battlePane.setBounds(0, 0, SCREENWIDTH, SCREENHEIGHT);
		this.add(battlePane);

        // Window setup
        this.setPreferredSize(SCREENSIZE);
        this.setVisible(true);
        this.setLayout(null);
        this.setFocusable(true);
        this.addKeyListener(this);
        this.changeToWorldScene();
    }

    public static void drawCursor(Graphics g, int cursor_x, int cursor_y) {
        Polygon triangle = new Polygon(new int[]{cursor_x, cursor_x + BasicPanel.FONT_SIZE * 2 / 3, cursor_x},
                new int[]{cursor_y, cursor_y + BasicPanel.FONT_SIZE / 3, cursor_y + BasicPanel.FONT_SIZE * 2 / 3}, 3);
        g.drawPolygon(triangle);
        g.fillPolygon(triangle);
    }

	public void changeToBattleScene(OpponentEntity opponent, Fighter wildFighter, boolean isTrainerBattle) {
		worldPane.setVisible(false);
		keyListenerCooldown = 0;
		if (opponent == null)
			battlePane.setBattle(new BattleSystem(stateMachineObserver, battlePane, player.getPlayerFighters(), new FighterInventory(new Fighter[]{wildFighter}), isTrainerBattle));
		else
			battlePane.setBattle(new BattleSystem(stateMachineObserver, battlePane, player.getPlayerFighters(), opponent.getFighterInventory(), isTrainerBattle));
		battlePane.setVisible(true);
	}

	public void changeToWorldScene() {
		battlePane.setVisible(false);
		worldPane.setVisible(true);
	}

	public void reloadWorld() {
		this.worldPane.reloadWorld();
	}

	public void reloadEntities() {
		this.worldPane.reloadEntities();
	}

	public void setOpponentDefeated() {
		worldPane.setOpponentDefeated();
	}

	@Override
	public void keyTyped(KeyEvent e) {
		if (worldPane.isVisible()) worldPane.keyTyped(e);
		if (battlePane.isVisible()) battlePane.keyTyped(e);
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if (worldPane.isVisible()) worldPane.keyReleased(e);
		if (battlePane.isVisible())
			if (keyListenerCooldown != 0)
				battlePane.keyReleased(e);
			else
				keyListenerCooldown = 1;
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (worldPane.isVisible()) worldPane.keyPressed(e);
	}

	private void startTickable() {
		timer.schedule(new TimerTask() {
			@Override
			public void run() {
				if (worldPane != null && worldPane.isVisible()) {
					reloadWorld();
					reloadEntities();
					worldPane.tickMoveCooldown();
				}
			}
		}, 0, 50);//wait 0 milliseconds before doing the action and do it every 50ms (0.05 seconds)
	}
}
