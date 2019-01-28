package MisakaCode.powers;

import MisakaCode.actions.AttractAction;
import MisakaCode.actions.RepelAction;
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

    public NegativelyChargedPower(AbstractCreature target) {
        name = NAME;
        ID = POWER_ID;
        owner = target;
        updateDescription();
        img = TextureLoader.getTexture("MisakaResources/images/powers/Negative.png");
        canGoNegative = false;
    }

    @Override
    public void onInitialApplication() {
        ArrayList<AbstractMonster> attractedMonsters = new ArrayList<>();
        ArrayList<AbstractMonster> repelledMonsters = new ArrayList<>();
        if (owner.hasPower(PositivelyChargedPower.POWER_ID)) {
            act(new RemoveSpecificPowerAction(owner, owner, PositivelyChargedPower.POWER_ID));
        }
        if (owner instanceof AbstractMonster) {
            AbstractMonster chargedMonster = (AbstractMonster)owner;
            for (AbstractMonster m : aq()) {
                int ownerIndex = aq().indexOf(chargedMonster);
                int monsterIndex = aq().indexOf(m);
                if (m.hasPower(PositivelyChargedPower.POWER_ID) && Math.abs(ownerIndex - monsterIndex) > 1) {
                    attractedMonsters.add(m);
                }
                if (m.hasPower(NegativelyChargedPower.POWER_ID) && Math.abs(ownerIndex - monsterIndex) == 1) {
                    if (
                            (aq().get(monsterIndex - 1) != null && aq().get(monsterIndex - 1) != owner)
                                    || (aq().get(monsterIndex + 1) != null && aq().get(monsterIndex + 1) != owner)
                                    || (aq().get(ownerIndex - 1) != null && aq().get(ownerIndex - 1) != m)
                                    || (aq().get(ownerIndex + 1) != null && aq().get(ownerIndex + 1) != m)
                    ) {
                        repelledMonsters.add(m);
                    }
                }
            }
        }
        if (!repelledMonsters.isEmpty()) {
            for (AbstractMonster m : repelledMonsters) {
                act(new RepelAction(owner, m));
            }
        }
        if (!attractedMonsters.isEmpty()) {
            for (AbstractMonster m : attractedMonsters) {
                act(new AttractAction(owner, m));
            }
        }
    }

    @Override
    public void updateDescription() {
        description = DESCRIPTIONS[0];
    }
}
