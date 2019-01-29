package MisakaCode.monsters;

import MisakaCode.powers.NegativelyChargedPower;
import MisakaCode.powers.PositivelyChargedPower;
import MisakaCode.tools.TextureLoader;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;

import java.util.ArrayList;

public abstract class AbstractMisakaMonster extends AbstractMonster {

    public AbstractMisakaMonster(String name, String id, int maxHealth, float hb_x, float hb_y, float hb_w, float hb_h, String imgDir, float offsetX, float offsetY) {
        super(name, id, maxHealth, hb_x, hb_y, hb_w, hb_h, "", offsetX, offsetY);
        img = TextureLoader.getTexture(imgDir);
    }

    void act (AbstractGameAction a) { AbstractDungeon.actionManager.addToBottom(a); }

    void actt (AbstractGameAction a) { AbstractDungeon.actionManager.addToTop(a); }

    ApplyPowerAction ns (AbstractPower po) { return new ApplyPowerAction(this, this, po, po.amount); }

    PositivelyChargedPower wq () { return new PositivelyChargedPower(this); }

    NegativelyChargedPower we () { return new NegativelyChargedPower(this); }

    ArrayList<AbstractMonster> aq() { return AbstractDungeon.getCurrRoom().monsters.monsters; }

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

    DamageAction na (AbstractCreature m, DamageInfo i) { return new DamageAction(m, i, AbstractGameAction.AttackEffect.SLASH_DIAGONAL); }
    DamageAction na (AbstractCreature m, DamageInfo i, AbstractGameAction.AttackEffect e) { return new DamageAction(m, i, e); }

    ArrayList<AbstractMonster> xx() {
        ArrayList<AbstractMonster> retVal = new ArrayList<>();
        if (re(this) != null) retVal.add(re(this));
        if (rt(this) != null) retVal.add(rt(this));
        return retVal;
    }
}
