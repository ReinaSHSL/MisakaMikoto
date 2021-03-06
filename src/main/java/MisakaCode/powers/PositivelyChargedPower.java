package MisakaCode.powers;

import MisakaCode.actions.AttractAction;
import MisakaCode.actions.RepelAction;
import MisakaCode.tools.TextureLoader;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;

import java.util.ArrayList;

public class PositivelyChargedPower extends AbstractMisakaPower {
    public static final String POWER_ID = "misaka:Positive";
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public PositivelyChargedPower(AbstractCreature target) {
        name = NAME;
        ID = POWER_ID;
        owner = target;
        updateDescription();
        img = TextureLoader.getTexture("MisakaResources/images/powers/Positive.png");
        canGoNegative = false;
    }

    @Override
    public void onInitialApplication() {
        if (owner.hasPower(NegativelyChargedPower.POWER_ID)) {
            act(new RemoveSpecificPowerAction(owner, owner, NegativelyChargedPower.POWER_ID));
        }
    }

    @Override
    public float atDamageReceive(float d, DamageInfo.DamageType t) {
        if (t == DamageInfo.DamageType.NORMAL) {
            for (AbstractMonster m : aq()) {
                if (m.hasPower(NegativelyChargedPower.POWER_ID)) {
                    act(na(m, nd((int)d, true)));
                }
            }
            if (AbstractDungeon.player.hasPower(PositivelyChargedPower.POWER_ID)) {
                act(nz((int)d));
            }
        }
        return d;
    }

    @Override
    public void updateDescription() {
        description = DESCRIPTIONS[0];
    }
}

