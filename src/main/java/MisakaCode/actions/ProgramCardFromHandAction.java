package MisakaCode.actions;

import MisakaCode.patches.ProgramCardsPatch;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.UIStrings;

public class ProgramCardFromHandAction extends AbstractMisakaAction {
    private static UIStrings uiStrings = CardCrawlGame.languagePack.getUIString("misaka:ProgramAction");
    private static String[] TEXT = uiStrings.TEXT;
    private int amount;
    private CardGroup programPile = ProgramCardsPatch.ProgramPileField.programPile.get(p);

    public ProgramCardFromHandAction(int i) {
        amount = i;
        duration = Settings.ACTION_DUR_FASTER;
    }

    @Override
    public void update() {
        if (duration == Settings.ACTION_DUR_FASTER) {
            if (p.hand.size() == 0) {
                isDone = true;
            }
            if (p.hand.size() <= amount) {
                for (AbstractCard c : p.hand.group) {
                    p.hand.removeCard(c);
                    programPile.addToBottom(c);
                }
                p.hand.applyPowers();
                tickDuration();
            }
            if (p.hand.size() > amount) {
                AbstractDungeon.handCardSelectScreen.open(TEXT[0], amount, false);
                tickDuration();
            }
        }
        if (!AbstractDungeon.handCardSelectScreen.wereCardsRetrieved) {
            for (AbstractCard c : AbstractDungeon.handCardSelectScreen.selectedCards.group) {
                p.hand.removeCard(c);
                programPile.addToBottom(c);
            }
            p.hand.applyPowers();
            AbstractDungeon.handCardSelectScreen.wereCardsRetrieved = true;
            isDone = true;
        }
    }
}
