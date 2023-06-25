package Frames.BattleUI;

import BattleSystem.BattleSystem;
import BattleSystem.Fighter;
import Entity.FighterInventory;
import Frames.BasicPanel;
import Frames.TextBox.BattleMenuBox;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class BattlePane extends JLayeredPane implements KeyListener, BattleObserver {

	private final BattleMenuBox battleMenuBox;
	private final FighterUI playerUI;
	private final FighterUI opponentUI;
	private final FighterInventoryUI fighterInventoryUI;

	/**
	 * Starts the graphical battle.
	 */
	public BattlePane(FighterInventory playerFighters) {
		// FighterInventoryUI
		fighterInventoryUI = new FighterInventoryUI(playerFighters);
		this.add(fighterInventoryUI, Integer.valueOf(2));

		// Adds the battle menu box
		this.battleMenuBox = new BattleMenuBox(fighterInventoryUI);
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
	public void updateHitpoints(BattleParticipant defender, int newHitpoints) {
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
	public void showFighterinventoryUI() {
		fighterInventoryUI.showUI(false);
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if (fighterInventoryUI.isVisible())
			fighterInventoryUI.keyReleased(e);
		else battleMenuBox.keyReleased(e);
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

	@Override
	public void keyPressed(KeyEvent e) {}
}
