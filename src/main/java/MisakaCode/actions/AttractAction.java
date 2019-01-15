package MisakaCode.actions;

import MisakaCode.powers.PositivelyChargedPower;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class AttractAction extends AbstractMisakaAction {
    private AbstractMonster m1;
    private AbstractMonster m2;

    public AttractAction(AbstractCreature m1, AbstractMonster m2) {
        if (m1 instanceof AbstractMonster) {
            this.m1 = (AbstractMonster)m1;
        }
        this.m2 = m2;
        this.duration = Settings.ACTION_DUR_FASTER;
    }

    @Override
    public void update() {
        if (this.duration == Settings.ACTION_DUR_FASTER) {
            AbstractMonster positiveM = null;
            AbstractMonster negativeM = null;
            if (m1.hasPower(PositivelyChargedPower.POWER_ID)) {
                positiveM = m1;
                negativeM = m2;
            } else {
                positiveM = m2;
                negativeM = m1;
            }
            int index = aq().indexOf(positiveM) - aq().indexOf(negativeM);
            if (index > 0) {

            }
        }
    }
}