package MisakaCode.powers;

import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;

public class MomentumPower extends AbstractMisakaPower {
    //TODO unstackable
    public static final String POWER_ID = "misaka:MomentumPower";
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    private int cardAmt;
    private int energyAmt;

    public MomentumPower(AbstractCreature p, int i, int j) {
        owner = p;
        cardAmt = i;
        energyAmt = j;
        amount = 0;
    }

    @Override
    public void onUseCard(AbstractCard c, UseCardAction a) {
        amount++;
        if (amount >= cardAmt) {
            act(new GainEnergyAction(energyAmt));
            amount = 0;
        }
    }

    @Override
    public void atEndOfRound() {
        act(new RemoveSpecificPowerAction(owner, owner, this));
    }

    @Override
    public void updateDescription() {
        description = DESCRIPTIONS[0] + cardAmt + DESCRIPTIONS[1] + energyAmt + DESCRIPTIONS[2];
    }
}
