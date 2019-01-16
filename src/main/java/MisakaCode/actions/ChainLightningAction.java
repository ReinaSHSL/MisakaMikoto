package MisakaCode.actions;

import MisakaCode.powers.NegativelyChargedPower;
import MisakaCode.powers.PositivelyChargedPower;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import java.util.ArrayList;

public class ChainLightningAction extends AbstractMisakaAction {
    private AbstractMonster target;
    private int count;
    private int damage;

    public ChainLightningAction(AbstractMonster m, int i, int dmg) {
        target = m;
        count = i;
        damage = dmg;
        duration = Settings.ACTION_DUR_FASTER;
    }

    @Override
    public void update() {
        if (duration == Settings.ACTION_DUR_FASTER) {
            ArrayList<AbstractMonster> targetedMonsters = new ArrayList<>();
            ArrayList<AbstractMonster> chargedMonsters = new ArrayList<>();
            ArrayList<AbstractMonster> eligibleMonsters = new ArrayList<>();
            int lowestDistance = 999;
            int currentTargetIndex = aq().indexOf(target);
            targetedMonsters.add(target);
            if (target.hasPower(PositivelyChargedPower.POWER_ID) || target.hasPower(NegativelyChargedPower.POWER_ID)) {
                while (count > 0) {
                    for (AbstractMonster m : aq()) {
                        if (target.hasPower(PositivelyChargedPower.POWER_ID) && m.hasPower(NegativelyChargedPower.POWER_ID)) {
                            chargedMonsters.add(m);
                        }
                        if (target.hasPower(NegativelyChargedPower.POWER_ID) && m.hasPower(PositivelyChargedPower.POWER_ID)) {
                            chargedMonsters.add(m);
                        }
                    }
                    for (AbstractMonster m : chargedMonsters) {
                        int distance = Math.abs(currentTargetIndex - aq().indexOf(m));
                        if (distance < lowestDistance) {
                            lowestDistance = distance;
                        }
                    }
                    if (aq().get(aq().indexOf(target) - lowestDistance) != null) {
                        eligibleMonsters.add(aq().get(aq().indexOf(target) - lowestDistance));
                    }
                    if (aq().get(aq().indexOf(target) + lowestDistance) != null) {
                        eligibleMonsters.add(aq().get(aq().indexOf(target) + lowestDistance));
                    }
                    int rng = AbstractDungeon.monsterRng.random(eligibleMonsters.size() - 1);
                    target = aq().get(rng);
                    act(na(target, nd(damage)));
                    chargedMonsters.clear();
                    eligibleMonsters.clear();
                    count--;
                }
            }
            tickDuration();
        }
    }
}
