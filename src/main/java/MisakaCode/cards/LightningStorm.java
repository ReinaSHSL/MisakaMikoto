package MisakaCode.cards;

import MisakaCode.patches.AbstractCardEnum;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.LightningEffect;

public class LightningStorm extends AbstractMisakaCard {
    public static final String ID = "misaka:LightningStorm";
    public static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    private static final String NAME = cardStrings.NAME;
    private static final String IMG = "MisakaResources/images/cards/LightningStorm";
    private static final int COST = 1;
    private static final String DESC = cardStrings.DESCRIPTION;
    private static final CardType TYPE = CardType.ATTACK;
    private static final CardColor COLOR = AbstractCardEnum.MISAKA_MAGNETIC;
    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.ALL_ENEMY;
    private static final int d = 4;
    private static final int du = 2;

    public LightningStorm() {
        super(ID, NAME, IMG, COST, DESC, TYPE, COLOR, RARITY, TARGET);
        damage = baseDamage = d;
        isMultiDamage = true;
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
        act(new SFXAction("THUNDERCLAP", 0.05F));
        for (AbstractMonster mo : aq()) {
            if (!mo.isDeadOrEscaped()) {
                act(new VFXAction(new LightningEffect(mo.drawX, mo.drawY), 0.05F));
            }
        }
        act(new DamageAllEnemiesAction(p, multiDamage, DamageInfo.DamageType.NORMAL, AbstractGameAction.AttackEffect.NONE));
    }
}
