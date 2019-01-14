package MisakaCode.actions;

import MisakaCode.powers.NegativelyChargedPower;
import MisakaCode.powers.PositivelyChargedPower;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import java.util.Collections;

public class RepelAction extends AbstractMisakaAction {
    private AbstractCreature m1;
    private AbstractMonster m2;

    public RepelAction(AbstractCreature m1, AbstractMonster m2) {
        this.m1 = m1;
        this.m2 = m2;
        this.duration = Settings.ACTION_DUR_FASTER;
    }

    @Override
    public void update() {
        if (this.duration == Settings.ACTION_DUR_FASTER) {
            AbstractMonster m1Neighbor = null;
            AbstractMonster m2Neighbor = null;
            AbstractMonster potentialNeighborLeft = aq().get(aq().indexOf(m1) - 1);
            AbstractMonster potentialNeighborRight = aq().get(aq().indexOf(m1) + 1);
            AbstractMonster potentialNeighborLeftTwo = aq().get(aq().indexOf(m2) - 1);
            AbstractMonster potentialNeighborRightTwo = aq().get(aq().indexOf(m2) + 1);
            if (m1.hasPower(NegativelyChargedPower.POWER_ID)) {
                if (potentialNeighborLeft != null && potentialNeighborLeft.hasPower(NegativelyChargedPower.POWER_ID)) {
                    if (potentialNeighborRight != null) {
                        m1Neighbor = potentialNeighborRight;
                    }
                } else {
                    m1Neighbor = potentialNeighborLeft;
                }
            } else if (m1.hasPower(PositivelyChargedPower.POWER_ID)) {
                if (potentialNeighborLeft.hasPower(PositivelyChargedPower.POWER_ID)) {
                    if (potentialNeighborRight != null) {
                       m1Neighbor = potentialNeighborRight;
                    }
                } else {
                    m1Neighbor = potentialNeighborLeft;
                }
            }
            if (m2.hasPower(PositivelyChargedPower.POWER_ID)) {
                if (potentialNeighborLeftTwo != null && potentialNeighborLeftTwo.hasPower(NegativelyChargedPower.POWER_ID)) {
                    if (potentialNeighborRightTwo != null) {
                        m2Neighbor = potentialNeighborRightTwo;
                    }
                } else {
                    m2Neighbor = potentialNeighborLeft;
                }
            } else if (m2.hasPower(PositivelyChargedPower.POWER_ID)) {
                if (potentialNeighborLeftTwo.hasPower(PositivelyChargedPower.POWER_ID)) {
                    if (potentialNeighborRightTwo != null) {
                        m1Neighbor = potentialNeighborRightTwo;
                    }
                } else {
                    m2Neighbor = potentialNeighborLeftTwo;
                }
            }
            if (m1Neighbor != null) {
                Collections.swap(aq(), aq().indexOf(m1), aq().indexOf(m1Neighbor));
                float temp;
                temp = m1Neighbor.drawX;
                m1Neighbor.drawX = m1.drawX;
                m1.drawX = temp;
            }
            if (m2Neighbor != null) {
                Collections.swap(aq(), aq().indexOf(m2), aq().indexOf(m2Neighbor));
                float temp;
                temp = m2Neighbor.drawX;
                m2Neighbor.drawX = m1.drawX;
                m2.drawX = temp;
            }
        }
    }
}
