package MisakaCode.monsters;

import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.MonsterStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class NeodymiumMagnet extends AbstractMisakaMonster {
    public static final String ID = "misaka:NeodymiumMagnet";
    private static final MonsterStrings monsterStrings = CardCrawlGame.languagePack.getMonsterStrings(ID);
    private static final String NAME = monsterStrings.NAME;
    private static final int MAX_HP = 5;
    private static final float HB_X = 0.0F;
    private static final float HB_Y = 10.0F;
    private static final float HB_W = 160.0F;
    private static final float HB_H = 180.0F;
    private static final String IMG = "MisakaResources/images/monsters/NeodymiumMagnet.png";
    private static final int DAMAGE = 5;

    public NeodymiumMagnet(float offsetX) {
        super(NAME, ID, MAX_HP, HB_X, HB_Y, HB_W, HB_H, IMG, offsetX, 0);
        setHp(5);
        damage.add(new DamageInfo(this, DAMAGE));
        setMove((byte)0, Intent.ATTACK, damage.get(0).base);
        createIntent();
    }

    @Override
    public void takeTurn() {
        switch (nextMove) {
            case 0:
                if (re(this) != null) {
                    AbstractMonster m1 = re(this);
                    act(na(m1, damage.get(0)));
                }
                if (rt(this) != null) {
                    AbstractMonster m2 = rt(this);
                    act(na(m2, damage.get(0)));
                }
                break;
            default:
                System.out.println("wtf?");
        }
        rollMove();
    }

    @Override
    protected void getMove(int i) {
        setMove((byte)0, Intent.ATTACK, damage.get(0).base);
    }
}
