package mysteryDungeon.cards.Charmander;

import static mysteryDungeon.MysteryDungeon.makeCardPath;

import basemod.abstracts.CustomCard;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.WeakPower;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;

import mysteryDungeon.MysteryDungeon;
import mysteryDungeon.characters.Pokemon;
import mysteryDungeon.powers.BurnPower;

public class CharmanderWillOWisp extends CustomCard {

    // TEXT DECLARATION

    public static final String ID = MysteryDungeon.makeID(CharmanderWillOWisp.class.getSimpleName());
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String IMG = makeCardPath("CharmanderSkill.png");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String UPGRADE_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;

    // /TEXT DECLARATION/


    // STAT DECLARATION

    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.ENEMY;
    private static final CardType TYPE = CardType.SKILL;
    public static final CardColor COLOR = Pokemon.Enums.CHARMANDER_RED;

    private static final int COST = -1;
    private static final int BASE_MAGIC_NUMBER = 3;


    // /STAT DECLARATION/

    public CharmanderWillOWisp() {
        super(ID, NAME, IMG, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
        baseMagicNumber = BASE_MAGIC_NUMBER;
        magicNumber = BASE_MAGIC_NUMBER;
        exhaust = true;
    }

    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        if (energyOnUse < EnergyPanel.totalCount)
        {
            energyOnUse = EnergyPanel.totalCount; 
        }
        if (p.hasRelic("Chemical X")) {
            this.energyOnUse += 2;
            p.getRelic("Chemical X").flash();
        }
        energyOnUse += upgraded?1:0;
        addToBot(new ApplyPowerAction(m, p, new WeakPower(p, energyOnUse, false), energyOnUse));
        addToBot(new ApplyPowerAction(m, p, new BurnPower(m, 3*energyOnUse), 3*energyOnUse));
        if (!this.freeToPlayOnce)
        {
            p.energy.use(EnergyPanel.totalCount);
        }
    }

    // Upgraded stats.
    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            rawDescription = UPGRADE_DESCRIPTION;
            initializeDescription();
        }
    }
}
