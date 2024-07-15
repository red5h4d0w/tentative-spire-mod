package mysteryDungeon.relics;

import mysteryDungeon.MysteryDungeon;
import mysteryDungeon.abstracts.PokemonRelic;
import mysteryDungeon.pokemons.Bulbasaur;
import mysteryDungeon.pokemons.Chikorita;
import mysteryDungeon.util.TextureLoader;

import static mysteryDungeon.MysteryDungeon.makeRelicOutlinePath;
import static mysteryDungeon.MysteryDungeon.makeRelicPath;

import java.util.HashSet;

import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.cards.AbstractCard.CardColor;
import com.megacrit.cardcrawl.cards.AbstractCard.CardType;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerToRandomEnemyAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.RelicStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.AbstractPower.PowerType;
import com.megacrit.cardcrawl.powers.PoisonPower;

public class BlackSludgeRelic extends PokemonRelic {

    // ID, images, text.
    public static final String ID = MysteryDungeon.makeID(BlackSludgeRelic.class);

    private static final RelicStrings relicStrings = CardCrawlGame.languagePack.getRelicStrings(ID);
    public static final String NAME = relicStrings.NAME;
    public static final String[] DESCRIPTIONS = relicStrings.DESCRIPTIONS;
    private static final Texture IMG = TextureLoader.getTexture(makeRelicPath("grass-gem.png"));
    private static final Texture OUTLINE = TextureLoader.getTexture(makeRelicOutlinePath("grass-gem.png"));

    public BlackSludgeRelic() {
        super(ID, IMG, OUTLINE, RelicTier.UNCOMMON, LandingSound.CLINK);

        cardColors = new HashSet<CardColor>() {
            {
                add(Bulbasaur.CARD_COLOR);
                add(Chikorita.CARD_COLOR);
            }
        };

    }

    public void onMonsterDeath(AbstractMonster m) {
    if (m.hasPower(m.powers.stream().filter(p->p.type == PowerType.DEBUFF).count())) {
      int amount = (m.getPower(m.powers.stream().filter(p->p.type == PowerType.DEBUFF).count())).amount;
      if (!AbstractDungeon.getMonsters().areMonstersBasicallyDead()) {
        flash();
        addToBot((AbstractGameAction)new RelicAboveCreatureAction((AbstractCreature)m, this));
        addToBot((AbstractGameAction)new ApplyPowerToRandomEnemyAction((AbstractCreature)AbstractDungeon.player, (AbstractPower)new PoisonPower(null, (AbstractCreature)AbstractDungeon.player, amount), amount, false, AbstractGameAction.AttackEffect.POISON));
      } else {
        logger.info("no target for the specimen");
      } 
    } 
  }

    // Description
    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }

}
