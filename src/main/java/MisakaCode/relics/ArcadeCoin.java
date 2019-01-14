package MisakaCode.relics;

import MisakaCode.tools.TextureLoader;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.relics.AbstractRelic;

public class ArcadeCoin extends AbstractMisakaRelic {
    public static final String ID = "misaka:ArcadeCoin";
    private static final Texture IMG = TextureLoader.getTexture("MisakaResources/resources/relics/ArcadeCoin.png");

    public ArcadeCoin() {
        super(ID, IMG, RelicTier.STARTER, LandingSound.CLINK);
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }

    @Override
    public AbstractRelic makeCopy() {
        return new ArcadeCoin();
    }
}
