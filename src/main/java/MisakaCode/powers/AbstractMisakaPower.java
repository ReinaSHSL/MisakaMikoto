package MisakaCode.powers;

import MisakaCode.actions.MisakaSpawnMonsterAction;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import com.megacrit.cardcrawl.powers.WeakPower;

import java.util.ArrayList;

public abstract class AbstractMisakaPower extends AbstractPower {
    void act (AbstractGameAction a) { AbstractDungeon.actionManager.addToBottom(a); }

    void actt (AbstractGameAction a) { AbstractDungeon.actionManager.addToTop(a); }

    ArrayList<AbstractMonster> aq() { return AbstractDungeon.getCurrRoom().monsters.monsters; }

    DamageInfo nd (int i, boolean isNormal) {
        if (isNormal) return new DamageInfo(AbstractDungeon.player, i, DamageInfo.DamageType.NORMAL);
        else return new DamageInfo(AbstractDungeon.player, i, DamageInfo.DamageType.THORNS);
    }

    DamageAction na (AbstractMonster m, DamageInfo i) { return new DamageAction(m, i, AbstractGameAction.AttackEffect.SLASH_DIAGONAL); }

    DamageAction na (AbstractMonster m, DamageInfo i, AbstractGameAction.AttackEffect e) { return new DamageAction(m, i, e); }

    GainBlockAction nz (int i) {
        return new GainBlockAction(AbstractDungeon.player, AbstractDungeon.player, i, true);
    }

    ApplyPowerAction ns (AbstractMonster m, AbstractPower po) { return new ApplyPowerAction(m, AbstractDungeon.player, po, po.amount); }

    ApplyPowerAction ns (AbstractPower po) { return new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, po, po.amount); }

    MisakaSpawnMonsterAction nq (String m, AbstractMonster target) { return new MisakaSpawnMonsterAction(m, target); }

    WeakPower wa (AbstractMonster m, int i) { return new WeakPower(m, i, false); }

    VulnerablePower wv (AbstractMonster m, int i) { return new VulnerablePower(m, i, false); }

    PositivelyChargedPower wq (AbstractMonster m) { return new PositivelyChargedPower(m); }

    NegativelyChargedPower we (AbstractMonster m) { return new NegativelyChargedPower(m); }
}
