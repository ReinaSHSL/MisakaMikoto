package MisakaCode.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import java.util.ArrayList;

public abstract class AbstractMisakaAction extends AbstractGameAction {

    void act(AbstractGameAction a) { AbstractDungeon.actionManager.addToBottom(a); }

    ArrayList<AbstractMonster> aq() { return AbstractDungeon.getCurrRoom().monsters.monsters; }
}
