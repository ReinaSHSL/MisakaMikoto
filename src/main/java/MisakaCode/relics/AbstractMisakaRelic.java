package MisakaCode.relics;

import MisakaCode.patches.AbstractCardEnum;
import basemod.helpers.RelicType;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.relics.AbstractRelic;

public abstract class AbstractMisakaRelic extends AbstractRelic {
    public AbstractCard.CardColor color = AbstractCardEnum.MISAKA_MAGNETIC;

    public AbstractMisakaRelic(String setId, String imgName, RelicTier tier, LandingSound sfx) {
        super(setId, imgName, tier, sfx);
    }
}
