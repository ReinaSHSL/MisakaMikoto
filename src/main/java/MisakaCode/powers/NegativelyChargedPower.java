package MisakaCode.powers;

import MisakaCode.tools.TextureLoader;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class NegativelyChargedPower extends AbstractPower {
    public static final String POWER_ID = "misaka:Negative";
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public NegativelyChargedPower(AbstractCreature owner) {
        this.name = NAME;
        this.ID = POWER_ID;
        this.owner = owner;
        updateDescription();
        this.img = TextureLoader.getTexture("MisakaResources/images/powers/Negative.png");
        this.canGoNegative = false;
    }

    @Override
    public void onInitialApplication() {
        if (this.owner.hasPower(PositivelyChargedPower.POWER_ID)) {
            act(new RemoveSpecificPowerAction(this.owner, this.owner, PositivelyChargedPower.POWER_ID));
        }
        for (wq())
    }
}
