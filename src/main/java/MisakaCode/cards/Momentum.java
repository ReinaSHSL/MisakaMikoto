package MisakaCode.cards;

import MisakaCode.patches.AbstractCardEnum;
import MisakaCode.powers.MomentumPower;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class Momentum extends AbstractMisakaCard {
    public static final String ID = "misaka:Momentum";
    public static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    private static final String NAME = cardStrings.NAME;
    private static final String IMG = "MisakaResources/images/cards/Momentum.png";
    private static final int COST = 0;
    private static final String DESC = cardStrings.DESCRIPTION;
    private static final CardType TYPE = CardType.SKILL;
    private static final CardColor COLOR = AbstractCardEnum.MISAKA_MAGNETIC;
    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final int m = 4;
    private static final int mn = 1;
    private static final int mnu = 1;

    public Momentum() {
        super(ID, NAME, IMG, COST, DESC, TYPE, COLOR, RARITY, TARGET);
        magicNumber = baseMagicNumber = m;
        misakaMagicNumber = baseMisakaMagicNumber = mn;
        buildDescription();
    }

    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            umn(mnu);
            buildDescription();
        }
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        act(ns(new MomentumPower(p, magicNumber, misakaMagicNumber)));
    }

    private void buildDescription() {
        StringBuilder sbuf = new StringBuilder(cardStrings.EXTENDED_DESCRIPTION[0]);
        for (int i = 0; i < misakaMagicNumber; i++) {
            sbuf.append("[E] ");
        }
        sbuf.append(cardStrings.EXTENDED_DESCRIPTION[1]);
        rawDescription = sbuf.toString();
        initializeDescription();
    }
}
