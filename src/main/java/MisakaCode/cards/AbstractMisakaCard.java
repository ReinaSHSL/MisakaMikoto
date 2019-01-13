package MisakaCode.cards;

import basemod.abstracts.CustomCard;

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
}
