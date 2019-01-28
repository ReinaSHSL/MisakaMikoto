package MisakaCode.actions;

import MisakaCode.powers.NegativelyChargedPower;
import MisakaCode.powers.PositivelyChargedPower;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import java.util.Collections;

public class AttractAction extends AbstractMisakaAction {
    private AbstractMonster m1;
    private AbstractMonster m2;

    public AttractAction(AbstractCreature leftM, AbstractMonster rightM) {
        if (m1 instanceof AbstractMonster) {
            m1 = (AbstractMonster)leftM;
        }
        m2 = rightM;
        duration = Settings.ACTION_DUR_FASTER;
    }

    @Override
    public void update() {
        if (duration == Settings.ACTION_DUR_FASTER) {
            AbstractMonster positiveM;
            AbstractMonster negativeM;
            if (m1.hasPower(PositivelyChargedPower.POWER_ID)) {
                positiveM = m1;
                negativeM = m2;
            } else {
                positiveM = m2;
                negativeM = m1;
            }
            int distance = aq().indexOf(positiveM) - aq().indexOf(negativeM);
            if (distance > 0) {
                int positionToPullTo = aq().indexOf(negativeM) + 1;
                Collections.swap(aq(), aq().indexOf(positiveM), positionToPullTo);
            } else if (distance < 0) {
                int positionToPullTo = aq().indexOf(negativeM) - 1;
                Collections.swap(aq(), aq().indexOf(positiveM), positionToPullTo);
            }
            tickDuration();
        }
    }
}
