package MisakaCode.cards;

import MisakaCode.patches.AbstractCardEnum;
import basemod.helpers.BaseModCardTags;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class Defend_Magnetic extends AbstractMisakaCard {
    public static final String ID = "misaka:Defend_Magnetic";
    public static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    private static final String NAME = cardStrings.NAME;
    private static final String IMG = "MisakaResources/images/cards/Defend_Magnetic.png";
    private static final int COST = 1;
    private static final String DESC = cardStrings.DESCRIPTION;
    private static final CardType TYPE = CardType.SKILL;
    private static final CardColor COLOR = AbstractCardEnum.MISAKA_MAGNETIC;
    private static final CardRarity RARITY = CardRarity.BASIC;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final int b = 5;
    private static final int bu = 3;

    public Defend_Magnetic() {
        super(ID, NAME, IMG, COST, DESC, TYPE, COLOR, RARITY, TARGET);
        tags.add(BaseModCardTags.BASIC_DEFEND);
        block = baseBlock = b;
    }

    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            ub(bu);
        }
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        act(nz());
    }
}
