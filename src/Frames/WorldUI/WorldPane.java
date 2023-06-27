package Frames.WorldUI;

import BattleSystem.Fighter;
import BattleSystem.Fighters.Citizen;
import BattleSystem.Fighters.Exorcist;
import BattleSystem.Fighters.Undead;
import BattleSystem.enums.FightingSide;
import Entity.Entities.InteractionType;
import Entity.Entities.OpponentEntity;
import Entity.Entities.PlayerEntity;
import Frames.InventoryUI.FighterInventoryUI;
import Frames.TextBox.MenuBox;
import Frames.TextBox.MenuType;
import Frames.TextBox.TextBox;
import Observer.ObserveType;
import Observer.Observer;
import Worlds.Tiles.HighGrassTile;
import Worlds.World;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

public class WorldPane extends JLayeredPane implements KeyListener {

	private static final int TILE_SIZE = 60;

	private final FighterInventoryUI fighterInventoryUI;
	private final MenuBox menuBox;
	private final TextBox dialogueBox;
	private final TerrainPanel terrain;
	private final EntityPanel entities;

	private final Observer stateMachineObserver;
	private final World world;
	private final PlayerEntity player;

	private OpponentEntity battleEntity;
	private int moveCooldown;
	private int keyListenerCooldown;

	/**
	 * Starts the graphical world.
	 * Terrain displays the world and entities displays all entities except the player.
	 */
	public WorldPane(World world, PlayerEntity player, Observer stateMachineObserver) {
		//Observer Init
		this.stateMachineObserver = stateMachineObserver;

		//Pane Init
		setVisible(true);
		setLayout(null);
		setFocusable(true);
		addKeyListener(this);

		//Physical world init
		terrain = new TerrainPanel(world, player, TILE_SIZE);
		entities = new EntityPanel(world, player, TILE_SIZE);
		this.world = world;
		add(terrain, Integer.valueOf(0));
		add(entities, Integer.valueOf(1));

		//Player + Player Label Init
		this.player = player;
		moveCooldown = 0;

		//Textbox Init
		keyListenerCooldown = 0;
		dialogueBox = new TextBox(stateMachineObserver);
		add(dialogueBox, Integer.valueOf(2));

		fighterInventoryUI = new FighterInventoryUI(player.getPlayerFighters(), MenuType.WORLD);
		add(fighterInventoryUI, Integer.valueOf(4));

		menuBox = new MenuBox(fighterInventoryUI, MenuType.WORLD);
		//menuBox.setVisible(false);
		add(menuBox, Integer.valueOf(3));
	}

	public void reloadWorld() {
		terrain.reload();
	}

	public void reloadEntities() {
		entities.reload();
	}

	public void tickMoveCooldown() {
		if (moveCooldown > 0)
			moveCooldown--;
	}

	public void setOpponentDefeated() {
		battleEntity.setInteractionType(InteractionType.TEXT);
		battleEntity = null;
	}

	@Override
	public void keyTyped(KeyEvent e) {
		if (dialogueBox.isVisible() || menuBox.isVisible()) return;
		switch (e.getKeyChar()) {
			case '\n' -> doCombat();
			case 'a' -> moveAction(3);
			case 'w' -> moveAction(0);
			case 'd' -> moveAction(1);
			case 's' -> moveAction(2);
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if (fighterInventoryUI.isVisible())
			fighterInventoryUI.keyReleased(e);
		else if (menuBox.isVisible())
			menuBox.keyReleased(e);

		else if (dialogueBox.isVisible())
			if (keyListenerCooldown != 0)
				dialogueBox.keyReleased(e);
			else
				keyListenerCooldown = 1;

		else if (e.getKeyCode() == 27)
			menuBox.setVisible(true);

	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (dialogueBox.isVisible() || menuBox.isVisible()) return;
		switch (e.getKeyCode()) {
			case 10 -> doCombat();
			case 37 -> moveAction(3);
			case 38 -> moveAction(0);
			case 39 -> moveAction(1);
			case 40 -> moveAction(2);
		}
	}

	private void startCombat(Fighter enemy) {
		stateMachineObserver.update(ObserveType.BATTLE_START, enemy);
	}

	private void startDialogue(String message, OpponentEntity entity) {
		keyListenerCooldown = 0;
		dialogueBox.setMessage(message, entity);
	}

	/**
	 * Updates the player location if the movement cooldown equals zero.
	 */
	private void moveAction(int direction) {
		if (moveCooldown == 0 && player.getFacing() == direction) {
			player.move(player.getFacing(), world);
			moveCooldown = 10;
		} else player.setFacing(direction);
		if (world.getTileArr()[player.getCoordinates().getX()][player.getCoordinates().getY()] instanceof HighGrassTile)
			randomChanceEncounter();
		//updatePlayerLabel();
	}

	private void randomChanceEncounter() {
		Random random = new Random();
		if (random.nextInt(1, 100) > 90) {
			int fighterChoice = random.nextInt(1, 3);
			switch (fighterChoice) {
				case 1 ->
						startCombat(new Undead("Wild Undead", 9000, FightingSide.OPPONENT, random.nextInt(16, 20), random.nextInt(2, 5), random.nextInt(2, 5), random.nextInt(5, 10)));
				case 2 ->
						startCombat(new Citizen("Wild Citizen", 8000, FightingSide.OPPONENT, random.nextInt(16, 20), random.nextInt(2, 5), random.nextInt(2, 5), random.nextInt(5, 10)));
				case 3 ->
						startCombat(new Exorcist("Wild Exorcist", 7000, FightingSide.OPPONENT, random.nextInt(16, 20), random.nextInt(2, 5), random.nextInt(2, 5), random.nextInt(5, 10)));
			}
		}
	}

	private void doCombat() {
		int x = player.getCoordinates().getX();
		int y = player.getCoordinates().getY();
		switch (player.getFacing()) {
			case 0 -> y -= 1;
			case 1 -> x += 1;
			case 2 -> y += 1;
			case 3 -> x -= 1;
		}

		if (x < 0 || x > world.getXLength() - 1 || y < 0 || y > world.getYLength() - 1)
			return;

		if (world.getEntityArr()[x][y] != null) {
			battleEntity = (OpponentEntity) world.getEntityArr()[x][y];
			startDialogue(battleEntity.getMessage(), battleEntity);
		}
	}

}