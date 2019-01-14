package MisakaCode.powers;

import MisakaCode.tools.TextureLoader;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;

import java.util.ArrayList;

public class NegativelyChargedPower extends AbstractMisakaPower {
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
        ArrayList<AbstractMonster> attractedMonsters = new ArrayList<>();
        ArrayList<AbstractMonster> repelledMonsters = new ArrayList<>();
        if (this.owner.hasPower(PositivelyChargedPower.POWER_ID)) {
            act(new RemoveSpecificPowerAction(this.owner, this.owner, PositivelyChargedPower.POWER_ID));
        }
        for (AbstractMonster m : aq()) {
            if (this.owner instanceof AbstractMonster) {
                AbstractMonster chargedMonster = (AbstractMonster)this.owner;
                int ownerIndex = aq().indexOf(chargedMonster);
                int monsterIndex = aq().indexOf(m);
                if (m.hasPower(PositivelyChargedPower.POWER_ID) && Math.abs(ownerIndex - monsterIndex) > 1) {
                    attractedMonsters.add(m);
                }
                if (m.hasPower(NegativelyChargedPower.POWER_ID) && Math.abs(ownerIndex - monsterIndex) == 1) {
                    repelledMonsters.add(m);
                }
            }
        }
    }
}
