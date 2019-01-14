package MisakaCode.cards;

import MisakaCode.monsters.NeodymiumMagnet;
import MisakaCode.patches.AbstractCardEnum;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class Neodymium extends AbstractMisakaCard {
    public static final String ID = "misaka:Neodymium";
    public static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    private static final String NAME = cardStrings.NAME;
    private static final String IMG = "MisakaResources/images/cards/Neodymium";
    private static final int COST = 1;
    private static final String DESC = cardStrings.DESCRIPTION;
    private static final CardType TYPE = CardType.SKILL;
    private static final CardColor COLOR = AbstractCardEnum.MISAKA_MAGNETIC;
    private static final CardRarity RARITY = CardRarity.BASIC;
    private static final CardTarget TARGET = CardTarget.ENEMY;
    private static final int cc = 0;

    public Neodymium() {
        super(ID, NAME, IMG, COST, DESC, TYPE, COLOR, RARITY, TARGET);
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            uc(cc);
        }
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        act(nq(NeodymiumMagnet.ID, m));
    }
}
