package mysteryDungeon.cards.tempCards;

import static mysteryDungeon.MysteryDungeon.makeCardPath;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.AbstractGameAction.AttackEffect;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.WeightyImpactEffect;

import mysteryDungeon.MysteryDungeon;
import mysteryDungeon.abstracts.PokemonCard;

public class SquirtleSkullBashGo extends PokemonCard {

    // TEXT DECLARATION

    public static final String ID = MysteryDungeon.makeID(SquirtleSkullBashGo.class.getSimpleName());
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String IMG = makeCardPath("SquirtleAttack.png");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String UPGRADE_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;

    // /TEXT DECLARATION/


    // STAT DECLARATION

    private static final CardRarity RARITY = CardRarity.SPECIAL;
    private static final CardTarget TARGET = CardTarget.ALL_ENEMY;
    private static final CardType TYPE = CardType.ATTACK;
    public static final CardColor COLOR = CardColor.COLORLESS;

    private static final int COST = 3;
    private static final int BASE_DAMAGE = 50;


    // /STAT DECLARATION/

    public SquirtleSkullBashGo() {
        super(ID, NAME, IMG, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
        isMultiDamage = true;
        baseDamage = BASE_DAMAGE;
        exhaust = true;
    }

    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        for(AbstractMonster mo : AbstractDungeon.getMonsters().monsters) {
            if(!mo.isDeadOrEscaped()) {
                addToBot(new VFXAction(new WeightyImpactEffect(mo.hb.cX, mo.hb.cY)));
            }

        }
        addToBot((AbstractGameAction)new DamageAllEnemiesAction(p, multiDamage, damageTypeForTurn, AttackEffect.NONE));

        if(AbstractDungeon.player.hand.size()==1)
        { 
            addToBot((AbstractGameAction)new DamageAllEnemiesAction(p, multiDamage, damageTypeForTurn, AttackEffect.NONE)); 
        }

    }

    public void triggerOnGlowCheck(AbstractPlayer p) {
        if(AbstractDungeon.player.hand.size()==1) {
          this.glowColor = AbstractCard.GOLD_BORDER_GLOW_COLOR.cpy();
        } else {
          this.glowColor = AbstractCard.BLUE_BORDER_GLOW_COLOR.cpy();
        } 
      }

    // Upgraded stats.
    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            selfRetain = true;
            rawDescription = UPGRADE_DESCRIPTION;
            initializeDescription();
        }
    }
}
