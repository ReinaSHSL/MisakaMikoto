package MisakaCode.cards;

import MisakaCode.actions.ChainLightningAction;
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
    private static final int m = 3;
    private static final int mu = 1;

    public ChainLightning() {
        super(ID, NAME, IMG, COST, DESC, TYPE, COLOR, RARITY, TARGET);
        damage = baseDamage = d;
        magicNumber = baseMagicNumber = m;
    }

    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            ud(du);
            um(mu);
        }
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        act(na(m, nd(damage)));
        act(new ChainLightningAction(m, magicNumber, damage));
    }

    @Override
    public AbstractCard makeCopy() {
        return new ChainLightning();
    }
}
