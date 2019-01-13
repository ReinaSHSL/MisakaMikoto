package MisakaCode.monsters;

import com.megacrit.cardcrawl.monsters.AbstractMonster;

public abstract class AbstractMisakaMonster extends AbstractMonster {

    public AbstractMisakaMonster(String name, String id, int maxHealth, float hb_x, float hb_y, float hb_w, float hb_h, String imgUrl, float offsetX, float offsetY, boolean ignoreBlights) {
        super(name, id, maxHealth, hb_x, hb_y, hb_w, hb_h, imgUrl, offsetX, offsetY, ignoreBlights);
    }
}
