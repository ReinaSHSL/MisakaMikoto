package MisakaCode.cards;

import MisakaCode.patches.AbstractCardEnum;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class ChainLightning extends AbstractMisakaCard {
    public static final String ID = "misaka:ChainLightning";
    public static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    private static final String NAME = cardStrings.NAME;
    private static final String IMG = "MisakaResources/images/cards/ChainLightning";
    private static final int COST = 1;
    private static final String DESC = cardStrings.DESCRIPTION;
    private static final CardType TYPE = CardType.ATTACK;
    private static final CardColor COLOR = AbstractCardEnum.MISAKA_MAGNETIC;
    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.ENEMY;
    private static final int d = 4;
    private static final int du = 2;

    public ChainLightning() {
        super(ID, NAME, IMG, COST, DESC, TYPE, COLOR, RARITY, TARGET);
        damage = baseDamage = d;
    }

    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            ud(du);
        }
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        act(new ChainLightningAction(m));
    }

    @Override
    public AbstractCard makeCopy() {
        return new ChainLightning();
    }
}
