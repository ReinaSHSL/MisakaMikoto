package MisakaCode.relics;

import MisakaCode.patches.AbstractCardEnum;
import basemod.abstracts.CustomRelic;
import basemod.helpers.RelicType;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.relics.AbstractRelic;

public abstract class AbstractMisakaRelic extends CustomRelic {
    public AbstractCard.CardColor color = AbstractCardEnum.MISAKA_MAGNETIC;

    public AbstractMisakaRelic(String id, Texture texture, RelicTier tier, LandingSound sfx) {
        super(id, texture, tier, sfx);
    }
}
