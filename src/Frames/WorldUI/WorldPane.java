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
import Frames.InventoryUI.ItemInventoryUI;
import Frames.TextBox.MenuBox;
import Frames.TextBox.MenuType;
import Frames.TextBox.TextBox;
import Observer.ObserveType;
import Observer.Observer;
import Worlds.Tiles.*;
import Worlds.World;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

public class WorldPane extends JLayeredPane implements KeyListener {

	private int typecomb;
	private static final int TILE_SIZE = 60;

	private final ItemInventoryUI itemInventoryUI;
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

	private boolean editMode;

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

		editMode = false;
		world.setEditMode(editMode);
		typecomb = 0;

		//Inventories Init
		fighterInventoryUI = new FighterInventoryUI(player.getPlayerFighters(), MenuType.WORLD);
		add(fighterInventoryUI, Integer.valueOf(5));

		itemInventoryUI = new ItemInventoryUI(fighterInventoryUI, player.getInventory(), MenuType.WORLD);
		add(itemInventoryUI, Integer.valueOf(4));

		fighterInventoryUI.setItemInventoryUI(itemInventoryUI);

		//Menu Init
		menuBox = new MenuBox(fighterInventoryUI, itemInventoryUI, MenuType.WORLD);
		menuBox.setSaveRequirements(player, world);
		menuBox.setVisible(false);
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
			case 'a' -> {
					moveAction(3);
					typecomb = 0;
			}
			case 'w' -> {
					moveAction(0);
					typecomb = 0;
			}
			case 'd' -> {
					moveAction(1);
					if(typecomb == 1) {typecomb++;} else {typecomb = 0;}
			}
			case 's' -> {
					moveAction(2);
					typecomb = 0;
			}
			case 'e' -> {
				if(typecomb == 0) {typecomb++;} else {typecomb = 0;}
			}
			case 'i' -> {
				if(typecomb == 2) {typecomb++;} else {typecomb = 0;}
			}
			case 't' -> {
				if(typecomb == 3) {editMode = !editMode; world.setEditMode(editMode);}
				typecomb = 0;
			}
			default -> typecomb = 0;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if (fighterInventoryUI.isVisible())
			fighterInventoryUI.keyReleased(e);
		else if (itemInventoryUI.isVisible())
			itemInventoryUI.keyReleased(e);
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
			if(!editMode)
				moveCooldown = 10;
		} else player.setFacing(direction);
		if (world.getTileArr()[player.getCoordinates().getX()][player.getCoordinates().getY()] instanceof HighGrassTile && !editMode)
			randomChanceEncounter();
		else if (world.getTileArr()[player.getCoordinates().getX()][player.getCoordinates().getY()] instanceof DoorTile)
				;
		//TODO please tell the statemachine to change world to wolrd saved in Doortile
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
		if(editMode) {
			switchTile();
			return;
		}
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
	private void switchTile() {
		Tile tile;
		switch(world.getTileArr()[player.getCoordinates().getX()][player.getCoordinates().getY()].getTileType()) {
			case "HighGrassTile" -> tile = new LowGrassTile();
			case "LowGrassTile" -> tile = new RockTile();
			case "RockTile" -> tile = new VoidTile();
			case "VoidTile" -> tile = new WaterTile();
			case "WaterTile" -> tile = new HighGrassTile();
			default -> tile = null;
		}
		if(tile != null)
			world.setTile(player.getCoordinates(), tile);
	}

}