package MisakaCode.cards;

import MisakaCode.patches.MisakaCardTags;
import basemod.BaseMod;
import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public abstract class AbstractMisakaCard extends CustomCard {
    public int misakaMagicNumber;
    public int baseMisakaMagicNumber;
    public boolean misakaMagicNumberChanged = false;

    public AbstractMisakaCard(String id, String name, String img, int cost, String rawDescription, CardType type, CardColor color, CardRarity rarity, CardTarget target) {
        super(id, name, img, cost, rawDescription, type, color, rarity, target);
    }

    public void upgradeMisakaMagicNumber(int i) {
        this.baseMisakaMagicNumber += i;
        this.misakaMagicNumberChanged = true;
    }

    public boolean isPositive() {
        return this.hasTag(MisakaCardTags.isPositive);
    }

    public boolean isNegative() {
        return this.hasTag(MisakaCardTags.isNegative);
    }

    void act(AbstractGameAction act) {
        AbstractDungeon.actionManager.addToBottom(act);
    }

    DamageInfo nd(int i) {
        return new DamageInfo(AbstractDungeon.player, i, this.damageTypeForTurn);
    }

    DamageAction na(AbstractMonster m, DamageInfo i) {
        return new DamageAction(m, i, AbstractGameAction.AttackEffect.SLASH_DIAGONAL);
    }

    DamageAction na(AbstractMonster m, DamageInfo i, AbstractGameAction.AttackEffect e) {
        return new DamageAction(m, i, e);
    }

    GainBlockAction nz(int i) {
        return new GainBlockAction(AbstractDungeon.player, AbstractDungeon.player, i, true);
    }

    void ub(int i) {
        this.upgradeBlock(i);
    }

    void ud(int i) {
        this.upgradeDamage(i);
    }

    void um(int i) {
        this.upgradeMagicNumber(i);
    }

    void umn(int i) {
        this.upgradeMisakaMagicNumber(i);
    }
}
