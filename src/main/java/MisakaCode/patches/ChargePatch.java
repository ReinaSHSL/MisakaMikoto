package MisakaCode.patches;

import MisakaCode.powers.NegativelyChargedPower;
import MisakaCode.powers.PositivelyChargedPower;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

@SpirePatch(
        clz = UseCardAction.class,
        method = SpirePatch.CONSTRUCTOR
)
public class ChargePatch {
    public static void Postfix(UseCardAction __instance, AbstractCard c, AbstractMonster m) {
        if (m != null && c.hasTag(MisakaCardTags.isNegative)) {
            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(m, AbstractDungeon.player, new NegativelyChargedPower(m)));
        }
        if (m != null && c.hasTag(MisakaCardTags.isPositive)) {
            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(m, AbstractDungeon.player, new PositivelyChargedPower(m)));
        }
    }
}
