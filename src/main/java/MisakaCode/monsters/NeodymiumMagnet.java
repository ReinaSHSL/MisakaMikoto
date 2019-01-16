package MisakaCode.monsters;

import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.MonsterStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class NeodymiumMagnet extends AbstractMisakaMonster {
    public static final String ID = "Mikasa:NeodymiumMagnet";
    private static final MonsterStrings monsterStrings = CardCrawlGame.languagePack.getMonsterStrings(ID);
    private static final String NAME = monsterStrings.NAME;
    private static final int MAX_HP = 5;
    private static final float HB_X = -2.0F;
    private static final float HB_Y = 10.0F;
    private static final float HB_W = 230.0F;
    private static final float HB_H = 240.0F;
    private static final String IMG = "MisakaResources/images/monsters/NeodymiumMagnet.png";

    public NeodymiumMagnet(float offsetX) {
        super(NAME, ID, MAX_HP, HB_X, HB_Y, HB_W, HB_H, IMG, offsetX, 0, true);
        setHp(5);
        damage.add(new DamageInfo(this, 5));
    }

    @Override
    public void takeTurn() {
        switch (this.nextMove) {
            case 1:
                AbstractMonster m1;
                AbstractMonster m2;
                if (re(this) != null) {
                    m1 = re(this);
                }
                if (rt(this) != null) {
                    m2 = rt(this);
                }
                act(na())
        }
    }

    @Override
    protected void getMove(int i) {
        setMove((byte)1, AbstractMonster.Intent.ATTACK, damage.get(0).base);
    }
}
