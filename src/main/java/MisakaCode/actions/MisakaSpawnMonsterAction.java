package MisakaCode.actions;

import MisakaCode.cards.AbstractMisakaCard;
import MisakaCode.monsters.AbstractMisakaMonster;
import MisakaCode.monsters.NeodymiumMagnet;
import MisakaCode.powers.PositivelyChargedPower;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.TalkAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.SpawnMonsterAction;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.MonsterStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.monsters.exordium.ApologySlime;

public class MisakaSpawnMonsterAction extends AbstractMisakaAction {
    private String m;
    private AbstractMonster target;
    private float shoveAmount = 50.0F * Settings.scale;
    public MisakaSpawnMonsterAction(String monsterID, AbstractMonster target) {
        m = monsterID;
        this.target = target;
        duration = Settings.ACTION_DUR_FASTER;
    }

    @Override
    public void update() {
        if (duration == Settings.ACTION_DUR_FASTER) {
            int index = aq().indexOf(target);
            float offsetX = (target.drawX - ((float)Settings.WIDTH * 0.75F)) / Settings.scale;
            AbstractMonster leftM = null;
            if (aq().indexOf(target) != 0) {
                leftM = aq().get(index - 1);
            }
            if (leftM != null) {
                for (AbstractMonster m : aq()) {
                    if (aq().indexOf(m) > aq().indexOf(leftM)) break;
                    m.drawY -= shoveAmount;
                }
            }
            for (int i = aq().indexOf(target); i < aq().size(); i++) {
                aq().get(i).drawX += shoveAmount;
            }
            switch (m) {
                case NeodymiumMagnet.ID:
                    NeodymiumMagnet neodymiumMagnet = new NeodymiumMagnet(offsetX);
                    AbstractDungeon.getCurrRoom().monsters.addMonster(index, neodymiumMagnet);
                    act(new ApplyPowerAction(neodymiumMagnet, neodymiumMagnet, new PositivelyChargedPower(neodymiumMagnet)));
                    break;
                default:
                    AbstractMonster slimeyboi = new ApologySlime();
                    act(new SpawnMonsterAction(slimeyboi, false));
                    act(new TalkAction(slimeyboi, "Aw, something went wrong... NL please let the devs know! NL Also yell at MegaCrit for me being a hardcoded mess.", 4.0F, 4.0F));
                    break;
            }
            isDone = true;
        }
    }
}
