package MisakaCode.patches;

import basemod.patches.com.megacrit.cardcrawl.core.CardCrawlGame.CustomRewardLoad;
import com.evacipated.cardcrawl.modthespire.lib.*;
import com.megacrit.cardcrawl.actions.common.SpawnMonsterAction;
import com.megacrit.cardcrawl.actions.unique.CanLoseAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.monsters.exordium.AcidSlime_L;
import com.megacrit.cardcrawl.monsters.exordium.SlimeBoss;
import com.megacrit.cardcrawl.monsters.exordium.SpikeSlime_L;
import javassist.CtBehavior;

@SpirePatch(
        clz = SlimeBoss.class,
        method = "takeTurn"
)
public class FuckYouMegacritWhyIsntThisFixedYetPatch {
    @SpireInsertPatch (
            locator = Locator.class
    )
    public static SpireReturn Insert(SlimeBoss __instance) {
        AbstractDungeon.actionManager.addToBottom(new SpawnMonsterAction(new SpikeSlime_L(120.0F, 20.0F, 0, __instance.currentHealth), false));
        AbstractDungeon.actionManager.addToBottom(new SpawnMonsterAction(new AcidSlime_L(-385.0F, -8.0F, 0, __instance.currentHealth), false));
        AbstractDungeon.actionManager.addToBottom(new CanLoseAction());
        __instance.setMove(SlimeBoss.MOVES[2], (byte)3, AbstractMonster.Intent.UNKNOWN);
        return SpireReturn.Return(null);
    }

    private static class Locator extends SpireInsertLocator {
        @Override
        public int[] Locate(CtBehavior ctMethodToPatch) throws Exception {
            Matcher finalMatcher = new Matcher.NewExprMatcher("SpikeSlime_L");
            return LineFinder.findInOrder(ctMethodToPatch, finalMatcher);
        }
    }
}
