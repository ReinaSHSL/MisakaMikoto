package MisakaCode.patches;

import MisakaCode.MisakaModInitializer;
import com.evacipated.cardcrawl.modthespire.lib.*;
import com.evacipated.cardcrawl.modthespire.patcher.PatchingException;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import javassist.CannotCompileException;
import javassist.CtBehavior;


public class ProgramCardsPatch {

    @SpirePatch(
            clz = AbstractPlayer.class,
            method = SpirePatch.CLASS
    )
    public static class ProgramPileField {
        public static SpireField<CardGroup> programPile = new SpireField<>(() -> null);
    }

    @SpirePatch(
            clz = AbstractPlayer.class,
            method = SpirePatch.CONSTRUCTOR
    )
    public static class InitProgramPile {
        public static void Prefix(AbstractPlayer __instance) {
            ProgramPileField.programPile.set(__instance, new CardGroup(CardGroup.CardGroupType.UNSPECIFIED));
        }
    }

    @SpirePatch(
            clz = AbstractPlayer.class,
            method = "preBattlePrep"
    )
    public static class ClearProgramPile {
        public static void Prefix(AbstractPlayer __instance) {
            ProgramPileField.programPile.get(__instance).clear();
        }
    }

    @SpirePatch(
            clz = AbstractDungeon.class,
            method = "update"
    )
    public static class ProgramUpdate {
        @SpireInsertPatch(
                locator = UpdateLocator.class
        )
        public static void Insert(AbstractDungeon __instance) {
            if (AbstractDungeon.screen == PROGRAM_VIEW) {
                MisakaModInitializer.programView.update();
            }
        }
    }

    private static class UpdateLocator extends SpireInsertLocator {
        @Override
        public int[] Locate(CtBehavior ctBehavior) throws CannotCompileException, PatchingException, PatchingException {
            Matcher matcher = new Matcher.FieldAccessMatcher(
                    AbstractDungeon.class, "turnPhaseEffectActive"
            );

            int[] line = LineFinder.findInOrder(ctBehavior, matcher);

            return line;
        }
    }

    @SpireEnum
    public static AbstractDungeon.CurrentScreen PROGRAM_VIEW;
}
