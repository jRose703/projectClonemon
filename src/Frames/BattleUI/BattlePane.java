package Frames.BattleUI;

import BattleSystem.BattleSystem;
import BattleSystem.Fighter;
import BattleSystem.enums.FightingSide;
import Entity.FighterInventory;
import Entity.ItemInventory;
import Frames.BasicPanel;
import Frames.InventoryUI.FighterInventoryUI;
import Frames.InventoryUI.ItemInventoryUI;
import Frames.TextBox.MenuBox;
import Frames.TextBox.MenuType;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class BattlePane extends JLayeredPane implements KeyListener, BattleObserver {

	private final MenuBox battleMenuBox;
	private final FighterUI playerUI;
	private final FighterUI opponentUI;
	private final FighterInventoryUI fighterInventoryUI;
	private final ItemInventoryUI itemInventoryUI;

	/**
	 * Starts the graphical battle.
	 */
	public BattlePane(FighterInventory playerFighters, ItemInventory inventory) {
		// InventoryUI setup
		fighterInventoryUI = new FighterInventoryUI(playerFighters, MenuType.BATTLE);
		this.add(fighterInventoryUI, Integer.valueOf(3));

		itemInventoryUI = new ItemInventoryUI(fighterInventoryUI, inventory, MenuType.BATTLE);
		add(itemInventoryUI, Integer.valueOf(2));

		// Adds the battle menu box
		this.battleMenuBox = new MenuBox(fighterInventoryUI, itemInventoryUI, MenuType.BATTLE);
		this.add(battleMenuBox, Integer.valueOf(1));

		// Height of (screen - menu box)
		int fightingPlaceHeight = BasicPanel.SCREENHEIGHT - battleMenuBox.getHeight();

		// Player UI
		playerUI = new FighterUI();
		playerUI.setBounds(0, fightingPlaceHeight / 2, BasicPanel.SCREENWIDTH, fightingPlaceHeight / 2);
		this.add(playerUI, Integer.valueOf(0));

		// Opponent UI
		opponentUI = new FighterUI();
		opponentUI.setBounds(0, 0, BasicPanel.SCREENWIDTH, fightingPlaceHeight / 2);
		this.add(opponentUI, Integer.valueOf(0));

		// Pane setup
		this.setVisible(false);
		this.setLayout(null);
	}

	public void setBattle(BattleSystem battle) {
		this.battleMenuBox.setBattle(battle);
		this.fighterInventoryUI.setBattle(battle);
	}

	@Override
	public void updateHitpoints(FightingSide defender, int newHitpoints) {
		switch (defender) {
			case PLAYER -> playerUI.updateHitpointbar(newHitpoints);
			case OPPONENT -> opponentUI.updateHitpointbar(newHitpoints);
		}
	}

	@Override
	public void setFighter(Fighter fighter) {
		switch (fighter.getFightingSide()) {
            case PLAYER ->
                    playerUI.updateNewFighter(fighter.getName(), fighter.getMaxHitpoints(), fighter.getHitpoints(), fighter.getBackSprite(), null);
            case OPPONENT ->
                    opponentUI.updateNewFighter(fighter.getName(), fighter.getMaxHitpoints(), fighter.getHitpoints(), null, fighter.getFrontSprite());
        }
	}

	@Override
	public void showFighterInventoryUI() {
		fighterInventoryUI.showUI(false, false, 0);
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if (fighterInventoryUI.isVisible())
			fighterInventoryUI.keyReleased(e);
		else if (itemInventoryUI.isVisible())
			itemInventoryUI.keyReleased(e);
		else battleMenuBox.keyReleased(e);
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

	@Override
	public void keyPressed(KeyEvent e) {}
}
