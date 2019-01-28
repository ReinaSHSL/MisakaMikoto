package MisakaCode.cards;

import MisakaCode.actions.ProgramCardFromHandAction;
import MisakaCode.patches.AbstractCardEnum;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class Reserves extends AbstractMisakaCard {
    public static final String ID = "misaka:Reserves";
    public static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    private static final String NAME = cardStrings.NAME;
    private static final String IMG = "MisakaResources/images/cards/Reserves.png";
    private static final int COST = 2;
    private static final String DESC = cardStrings.DESCRIPTION;
    private static final CardType TYPE = CardType.SKILL;
    private static final CardColor COLOR = AbstractCardEnum.MISAKA_MAGNETIC;
    private static final CardRarity RARITY = CardRarity.COMMON;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final int m = 2;
    private static final int mu = 1;

    public Reserves() {
        super(ID, NAME, IMG, COST, DESC, TYPE, COLOR, RARITY, TARGET);
        magicNumber = baseMagicNumber = m;
    }

    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            um(mu);
        }
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        act(new ProgramCardFromHandAction(magicNumber));
    }
}
