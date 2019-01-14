package MisakaCode.actions;

import MisakaCode.cards.AbstractMisakaCard;
import MisakaCode.monsters.AbstractMisakaMonster;
import MisakaCode.monsters.NeodymiumMagnet;
import MisakaCode.powers.PositivelyChargedPower;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.SpawnMonsterAction;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class MisakaSpawnMonsterAction extends AbstractMisakaAction {
    private String m;
    private AbstractMonster target;
    private float shoveAmount = 50.0F * Settings.scale;

    public MisakaSpawnMonsterAction(String m, AbstractMonster target) {
        this.m = m;
        this.target = target;
        this.duration = Settings.ACTION_DUR_FASTER;
    }

    @Override
    public void update() {
        if (this.duration == Settings.ACTION_DUR_FASTER) {
            int index = aq().indexOf(target);
            float offsetX = (target.drawX - ((float)Settings.WIDTH * 0.75F)) / Settings.scale;
            if (aq().get(index - 1) != null) {
                AbstractMonster moveMonsterLeft = aq().get(index - 1);
                moveMonsterLeft.drawX -= shoveAmount;
            }
            target.drawX += shoveAmount;
            switch (m) {
                case NeodymiumMagnet.ID:
                    NeodymiumMagnet neodymiumMagnet = new NeodymiumMagnet(offsetX);
                    AbstractDungeon.getCurrRoom().monsters.addMonster(index, neodymiumMagnet);
                    act(new ApplyPowerAction(neodymiumMagnet, neodymiumMagnet, new PositivelyChargedPower(neodymiumMagnet)));
                    tickDuration();
            }
        }
    }
}
