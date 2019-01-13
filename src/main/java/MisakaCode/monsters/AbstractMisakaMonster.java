package MisakaCode.monsters;

import MisakaCode.powers.NegativelyChargedPower;
import MisakaCode.powers.PositivelyChargedPower;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;

import java.util.ArrayList;

public abstract class AbstractMisakaMonster extends AbstractMonster {

    public AbstractMisakaMonster(String name, String id, int maxHealth, float hb_x, float hb_y, float hb_w, float hb_h, String imgUrl, float offsetX, float offsetY, boolean ignoreBlights) {
        super(name, id, maxHealth, hb_x, hb_y, hb_w, hb_h, imgUrl, offsetX, offsetY, ignoreBlights);
    }

    void act (AbstractGameAction a) { AbstractDungeon.actionManager.addToBottom(a); }

    void actt (AbstractGameAction a) { AbstractDungeon.actionManager.addToTop(a); }

    ApplyPowerAction ns (AbstractPower po) { return new ApplyPowerAction(this, this, po, po.amount); }

    PositivelyChargedPower wq () { return new PositivelyChargedPower(this); }

    NegativelyChargedPower we () { return new NegativelyChargedPower(this); }

    ArrayList<AbstractMonster> aq() { return AbstractDungeon.getCurrRoom().monsters.monsters; }

    AbstractMonster xz() {
        if (aq().get(aq().indexOf(this) - 1) != null) {
            return aq().get(aq().indexOf(this) - 1);
        }
        return null;
    }

    AbstractMonster xc() {
        if (aq().get(aq().indexOf(this) + 1) != null) {
            return aq().get(aq().indexOf(this) + 1);
        }
        return null;
    }

    ArrayList<AbstractMonster> xx() {
        ArrayList<AbstractMonster> retVal = new ArrayList<>();
        if (xz() != null) retVal.add(xz());
        if (xc() != null) retVal.add(xc());
        return retVal;
    }
}
