package MisakaCode.cards;

import MisakaCode.patches.AbstractCardEnum;
import MisakaCode.patches.MisakaCardTags;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class Positivity extends AbstractMisakaCard {
    public static final String ID = "misaka:Positivity";
    public static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    private static final String NAME = cardStrings.NAME;
    private static final String IMG = "MisakaResources/images/cards/Positivity.png";
    private static final int COST = 1;
    private static final String DESC = cardStrings.DESCRIPTION;
    private static final CardType TYPE = CardType.ATTACK;
    private static final CardColor COLOR = AbstractCardEnum.MISAKA_MAGNETIC;
    private static final CardRarity RARITY = CardRarity.COMMON;
    private static final CardTarget TARGET = CardTarget.ENEMY;
    private static final int d = 10;
    private static final int du = 2;

    public Positivity() {
        super(ID, NAME, IMG, COST, DESC, TYPE, COLOR, RARITY, TARGET);
        damage = baseDamage = d;
        tags.add(MisakaCardTags.isPositive);
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
        act(na(m, nd()));
    }
}
