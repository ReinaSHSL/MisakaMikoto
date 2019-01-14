package MisakaCode.relics;

import MisakaCode.tools.TextureLoader;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.relics.AbstractRelic;

public class ArcadeToken extends AbstractMisakaRelic {
    public static final String ID = "misaka:ArcadeToken";
    private static final Texture IMG = TextureLoader.getTexture("MisakaResources/resources/relics/ArcadeToken.png");

    public ArcadeToken() {
        super(ID, IMG, RelicTier.STARTER, LandingSound.CLINK);
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }

    @Override
    public void onUseCard(AbstractCard c, UseCardAction a) {

    }

}
