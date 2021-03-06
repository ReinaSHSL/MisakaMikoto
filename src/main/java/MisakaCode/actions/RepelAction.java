package MisakaCode.actions;

import MisakaCode.powers.NegativelyChargedPower;
import MisakaCode.powers.PositivelyChargedPower;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import java.util.Collections;

public class RepelAction extends AbstractMisakaAction {
    private AbstractMonster m1;
    private AbstractMonster m2;

    public RepelAction(AbstractCreature leftM, AbstractMonster rightM) {
        m1 = (AbstractMonster) leftM;
        m2 = rightM;
        duration = Settings.ACTION_DUR_FASTER;
    }

    @Override
    public void update() {
        if (duration == Settings.ACTION_DUR_FASTER) {
            AbstractMonster m1Neighbor = null;
            AbstractMonster m2Neighbor = null;
            AbstractMonster potentialNeighborLeft = null;
            AbstractMonster potentialNeighborRight = null;
            AbstractMonster potentialNeighborLeftTwo = null;
            AbstractMonster potentialNeighborRightTwo = null;
            if (re(m1) != null) {
                potentialNeighborLeft = re(m1);
            }
            if (rt(m1) != null) {
                potentialNeighborRight = rt(m1);
            }
            if (re(m2) != null) {
                potentialNeighborLeftTwo = re(m2);
            }
            if (rt(m2) != null) {
                potentialNeighborRightTwo = rt(m2);
            }

            if (m1.hasPower(NegativelyChargedPower.POWER_ID)) {
                if (potentialNeighborLeft != null && potentialNeighborLeft.hasPower(NegativelyChargedPower.POWER_ID)) {
                    if (potentialNeighborRight != null) {
                        m1Neighbor = potentialNeighborRight;
                    }
                } else {
                    m1Neighbor = potentialNeighborLeft;
                }
            } else if (m1.hasPower(PositivelyChargedPower.POWER_ID)) {
                if (potentialNeighborLeft != null && potentialNeighborLeft.hasPower(PositivelyChargedPower.POWER_ID)) {
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
                if (potentialNeighborLeftTwo != null && potentialNeighborLeftTwo.hasPower(PositivelyChargedPower.POWER_ID)) {
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
                act(na(m1Neighbor, nd(1)));
            }
            if (m2Neighbor != null) {
                Collections.swap(aq(), aq().indexOf(m2), aq().indexOf(m2Neighbor));
                float temp;
                temp = m2Neighbor.drawX;
                m2Neighbor.drawX = m1.drawX;
                m2.drawX = temp;
                act(na(m2Neighbor, nd(1)));
            }
            isDone = true;
        }
    }
}
