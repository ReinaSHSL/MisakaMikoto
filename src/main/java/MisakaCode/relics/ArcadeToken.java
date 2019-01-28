package MisakaCode.relics;

import MisakaCode.tools.TextureLoader;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.relics.AbstractRelic;

public class ArcadeToken extends AbstractMisakaRelic {
    public static final String ID = "misaka:ArcadeToken";
    private static final Texture IMG = TextureLoader.getTexture("MisakaResources/resources/relics/ArcadeToken.png");
    private static final int d = 5;
    private static final int co = 8;

    public ArcadeToken() {
        super(ID, IMG, RelicTier.STARTER, LandingSound.CLINK);
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0] + co + DESCRIPTIONS[1] + d + DESCRIPTIONS[2];
    }

    @Override
    public void onUseCard(AbstractCard c, UseCardAction a) {
        if(counter < 0) {
            counter = 0;
        }
        this.counter++;
        if (counter >= co) {
            act(new RelicAboveCreatureAction(AbstractDungeon.player, this));
            act(new DamageAllEnemiesAction(null, DamageInfo.createDamageMatrix(d, true), DamageInfo.DamageType.THORNS, AbstractGameAction.AttackEffect.SLASH_HEAVY));
        }
    }
}
