package MisakaCode.patches;

import com.evacipated.cardcrawl.modthespire.lib.SpireField;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;


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
}
