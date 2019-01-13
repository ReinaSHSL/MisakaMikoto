package MisakaCode.cards;

import basemod.abstracts.DynamicVariable;
import com.megacrit.cardcrawl.cards.AbstractCard;

public class MisakaDynamicVariable extends DynamicVariable {

    @Override
    public String key() {
        return "misaka:m";
    }

    @Override
    public boolean isModified(AbstractCard c) {
        if (c instanceof AbstractMisakaCard) {
            AbstractMisakaCard ac = (AbstractMisakaCard)c;
            return ac.baseMisakaMagicNumber != ac.misakaMagicNumber;
        }
        return false;
    }

    @Override
    public int value(AbstractCard c) {
        if (c instanceof AbstractMisakaCard) {
            AbstractMisakaCard ac = (AbstractMisakaCard)c;
            return ac.misakaMagicNumber;
        }
        return 0;
    }

    @Override
    public int baseValue(AbstractCard c) {
        if (c instanceof AbstractMisakaCard) {
            AbstractMisakaCard ac = (AbstractMisakaCard)c;
            return ac.baseMisakaMagicNumber;
        }
        return 0;
    }

    @Override
    public boolean upgraded(AbstractCard c) {
        if (c instanceof AbstractMisakaCard) {
            AbstractMisakaCard ac = (AbstractMisakaCard)c;
            return ac.misakaMagicNumberChanged;
        }
        return false;
    }
}
