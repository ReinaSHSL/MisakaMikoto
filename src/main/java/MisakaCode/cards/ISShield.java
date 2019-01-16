package MisakaCode.cards;

import MisakaCode.patches.AbstractCardEnum;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class ISShield extends AbstractMisakaCard {
    public static final String ID = "misaka:ISShield";
    public static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    private static final String NAME = cardStrings.NAME;
    private static final String IMG = "MisakaResources/images/cards/ISShield";
    private static final int COST = 2;
    private static final String DESC = cardStrings.DESCRIPTION;
    private static final CardType TYPE = CardType.SKILL;
    private static final CardColor COLOR = AbstractCardEnum.MISAKA_MAGNETIC;
    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final int b = 10;
    private static final int bu = 2;
    private static final int m = 1;
    private static final int mu = 1;

    public ISShield() {
        super(ID, NAME, IMG, COST, DESC, TYPE, COLOR, RARITY, TARGET);
        this.block = this.baseBlock = b;
        this.magicNumber = this.baseMagicNumber = m;
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            ub(bu);
            um(mu);
        }
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        act(nz(this.block));
        act(ip(io(false), this.magicNumber));
    }

    @Override
    public AbstractCard makeCopy() {
        return new ISShield();
    }
}
