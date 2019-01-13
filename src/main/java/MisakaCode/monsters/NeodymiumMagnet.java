package MisakaCode.monsters;

import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.MonsterStrings;

public class NeodymiumMagnet extends AbstractMisakaMonster {
    public static final String ID = "Mikasa:NeodymiumMagnet";
    private static final MonsterStrings monsterStrings = CardCrawlGame.languagePack.getMonsterStrings(ID);
    private static final String NAME = monsterStrings.NAME;
    private static final float HB_X = -2.0F;
    private static final float HB_Y = 10.0F;
    

    public NeodymiumMagnet() {
        super(name, id, maxHealth, hb_x, hb_y, hb_w, hb_h, imgUrl, offsetX, offsetY, ignoreBlights);
    }

    @Override
    public void takeTurn() {

    }

    @Override
    protected void getMove(int i) {

    }
}
