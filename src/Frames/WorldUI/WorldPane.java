package Frames.WorldUI;

import BattleSystem.Fighter;
import BattleSystem.Fighters.Citizen;
import BattleSystem.Fighters.Exorcist;
import BattleSystem.Fighters.Undead;
import BattleSystem.FightingType;
import Entity.OpponentEntity;
import Entity.PlayerEntity;
import Frames.BattleUI.BattleParticipant;
import Frames.TextBox.DialogueType;
import Frames.TextBox.TextBox;
import Observer.ObserveType;
import Observer.Observer;
import Worlds.World;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

public class WorldPane extends JLayeredPane implements KeyListener {

	private static final int TILE_SIZE = 60;

	private final PlayerEntity player;
	private final Observer stateMachineObserver;
	private final TextBox dialogueBox;
	private final World world;
	private final TerrainPanel terrain;
	private final EntityPanel entities;
	private final boolean FIXED_SIZE;

	private int moveCooldown;
	private int keyListenerCooldown;
	private OpponentEntity battleEntity;

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

		terrain = new TerrainPanel(world, player, TILE_SIZE, world.getXLength(), world.getYLength());
		entities = new EntityPanel(world, player, TILE_SIZE, world.getXLength(), world.getYLength());
		this.world = world;
		add(terrain, Integer.valueOf(0));
		add(entities, Integer.valueOf(1));

		//Player + Player Label Init
		this.player = player;
		//this.player.setCoordinates(3, 4);
		moveCooldown = 0;

		//playerLabel.setBounds(player.getCoordinates().getX() * TILE_SIZE, player.getCoordinates().getY() * TILE_SIZE, TILE_SIZE, TILE_SIZE);

		//Textbox Init
		keyListenerCooldown = 0;
		dialogueBox = new TextBox(stateMachineObserver);
		add(dialogueBox, Integer.valueOf(3));

		this.FIXED_SIZE = true;
	}

	public void tickMoveCooldown(){
		if (moveCooldown > 0)
			moveCooldown--;
	}

	// an dieser Stelle sollte der Dialogtyp mit übergeben werden können
	public void startDialogue(String text, OpponentEntity entity) {
		keyListenerCooldown = 0;
		dialogueBox.setMessage(text, entity);
	}

	private void startCombat(Fighter enemy) {
		stateMachineObserver.update(ObserveType.BATTLE_START, enemy);
	}

	public void reloadWorld() {
		terrain.reload();
	}

	public void reloadEntities() {
		entities.reload();
	}

	/**
	 * Updates the player location if the movement cooldown equals zero.
	 */
	private void moveAction(int direction) {
		if (moveCooldown == 0 && player.getFacing() == direction) {
			player.move(player.getFacing(), world);
			moveCooldown = 10;
		} else player.setFacing(direction);
		randomChanceEncounter();
		//updatePlayerLabel();
	}

	private void randomChanceEncounter() {
		Random random = new Random();
		if (random.nextInt(1, 100) > 95) {
			int fighterChoice = random.nextInt(1, 3);
			switch (fighterChoice) {
				case 1 ->
						startCombat(new Undead("OpponentOne", FightingType.UNDEAD, 9000, BattleParticipant.OPPONENT, random.nextInt(16, 20), random.nextInt(2, 5), random.nextInt(2, 5), random.nextInt(5, 10)));
				case 2 ->
						startCombat(new Citizen("OpponentOne", FightingType.CITIZEN, 8000, BattleParticipant.OPPONENT, random.nextInt(16, 20), random.nextInt(2, 5), random.nextInt(2, 5), random.nextInt(5, 10)));
				case 3 ->
						startCombat(new Exorcist("OpponentOne", FightingType.EXORCIST, 7000, BattleParticipant.OPPONENT, random.nextInt(16, 20), random.nextInt(2, 5), random.nextInt(2, 5), random.nextInt(5, 10)));
			}
		}
	}

	/**
	 * Updates the graphical player location.
	 */
	private void updatePlayerLabel() {
		//playerLabel.setLocation(player.getCoordinates().getX() * TILE_SIZE, player.getCoordinates().getY() * TILE_SIZE);
		ImageIcon image;
		switch (player.getFacing()) {
			case 1 -> image = new ImageIcon("assets/entities/player_e.png");
			case 2 -> image = new ImageIcon("assets/entities/player_s.png");
			case 3 -> image = new ImageIcon("assets/entities/player_w.png");
			default -> image = new ImageIcon("assets/entities/player_n.png");
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
			{
				if (battleEntity.getDialogueType() == null)
					battleEntity.setDialogueType(DialogueType.BATTLE);
				battleEntity.setMessage("I wanted to battle people since I got my first fighter!");
			} // TODO: This should be read out instead of changed here
			startDialogue(battleEntity.getMessage(), battleEntity);
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {;
		if (dialogueBox.isVisible()) return;
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
		if (dialogueBox.isVisible())
			if (keyListenerCooldown != 0)
				dialogueBox.keyReleased(e);
			else
				keyListenerCooldown = 1;
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (dialogueBox.isVisible()) return;
		switch (e.getKeyCode()) {
			case 10 -> doCombat();
			case 37 -> moveAction(3);
			case 38 -> moveAction(0);
			case 39 -> moveAction(1);
			case 40 -> moveAction(2);
		}
	}

	public void setOpponentDefeated() {
		battleEntity.setDialogueType(DialogueType.TEXT);
		battleEntity = null;
	}
}