package MisakaCode.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import java.util.ArrayList;

public abstract class AbstractMisakaAction extends AbstractGameAction {

    public AbstractPlayer p = AbstractDungeon.player;

    void act(AbstractGameAction a) { AbstractDungeon.actionManager.addToBottom(a); }

    ArrayList<AbstractMonster> aq() { return AbstractDungeon.getCurrRoom().monsters.monsters; }

    RemoveSpecificPowerAction az(AbstractCreature c, String ID) { return new RemoveSpecificPowerAction(c, c, ID);}

    AbstractMonster re (AbstractMonster m) {
        try {
            return aq().get(aq().indexOf(m) - 1);
        } catch (IndexOutOfBoundsException e) {
            return null;
        }
    }

    AbstractMonster rt (AbstractMonster m) {
        try {
            return aq().get(aq().indexOf(m) + 1);
        } catch (IndexOutOfBoundsException e) {
            return null;
        }
    }

    DamageAction na (AbstractMonster m, DamageInfo i) { return new DamageAction(m, i, AbstractGameAction.AttackEffect.SLASH_DIAGONAL); }

    DamageAction na (AbstractMonster m, DamageInfo i, AbstractGameAction.AttackEffect e) { return new DamageAction(m, i, e); }

    DamageInfo nd (int i) {
        return new DamageInfo(AbstractDungeon.player, i, DamageInfo.DamageType.NORMAL);
    }

}
