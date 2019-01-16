package MisakaCode.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import java.util.ArrayList;

public abstract class AbstractMisakaAction extends AbstractGameAction {

    void act(AbstractGameAction a) { AbstractDungeon.actionManager.addToBottom(a); }

    ArrayList<AbstractMonster> aq() { return AbstractDungeon.getCurrRoom().monsters.monsters; }

    RemoveSpecificPowerAction az(AbstractCreature c, String ID) { return new RemoveSpecificPowerAction(c, c, ID);}

    AbstractMonster re (AbstractMonster m) { return aq().get(aq().indexOf(m) - 1); }

    AbstractMonster rt (AbstractMonster m) { return aq().get(aq().indexOf(m) + 1); }
}
