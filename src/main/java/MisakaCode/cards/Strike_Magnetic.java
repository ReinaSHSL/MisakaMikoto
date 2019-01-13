package MisakaCode.cards;

import MisakaCode.patches.AbstractCardEnum;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class Strike_Magnetic extends AbstractMisakaCard {
    public static final String ID = "misaka:Strike";
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    private static final String NAME = cardStrings.NAME;
    private static final String IMG = "MisakaResources/images/cards/Strike";
    private static final int COST = 1;
    private static final String DESC = cardStrings.DESCRIPTION;
    private static final AbstractCard.CardType TYPE = CardType.ATTACK;
    private static final AbstractCard.CardColor COLOR = AbstractCardEnum.MISAKA_MAGNETIC;
    private static final AbstractCard.CardRarity RARITY = CardRarity.BASIC;
    private static final AbstractCard.CardTarget TARGET = CardTarget.ENEMY;

    public Strike_Magnetic() {
        super(ID, NAME, IMG, COST, DESC, TYPE, COLOR, RARITY, TARGET);
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
        }
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {

    }

    @Override
    public AbstractCard makeCopy() {
        return new Strike_Magnetic();
    }
}
