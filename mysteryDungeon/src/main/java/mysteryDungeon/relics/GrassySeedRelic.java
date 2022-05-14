package mysteryDungeon.relics;

import mysteryDungeon.MysteryDungeon;
import mysteryDungeon.abstracts.PokemonCard;
import mysteryDungeon.abstracts.PokemonRelic;
import mysteryDungeon.characters.Pokemon;
import mysteryDungeon.pokemons.Charmander;
import mysteryDungeon.util.TextureLoader;

import static mysteryDungeon.MysteryDungeon.makeRelicOutlinePath;
import static mysteryDungeon.MysteryDungeon.makeRelicPath;

import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDrawPileAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.AbstractCard.CardColor;
import com.megacrit.cardcrawl.cards.AbstractCard.CardTags;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.CardLibrary;
import com.megacrit.cardcrawl.helpers.PowerTip;
import com.megacrit.cardcrawl.localization.RelicStrings;

public class GrassySeedRelic extends PokemonRelic { 

    // ID, images, text.
    public static final String ID = MysteryDungeon.makeID(GrassySeedRelic.class.getSimpleName());

    private static final RelicStrings relicStrings = CardCrawlGame.languagePack.getRelicStrings(ID);
    public static final String NAME = relicStrings.NAME;
    public static final String[] DESCRIPTIONS = relicStrings.DESCRIPTIONS;
    private static final Texture IMG = TextureLoader.getTexture(makeRelicPath("Meadow-Plate.png"));
    private static final Texture OUTLINE = TextureLoader.getTexture(makeRelicOutlinePath("Meadow-Plate.png"));

    public GrassySeedRelic() {
        super(ID, IMG, OUTLINE, RelicTier.COMMON, LandingSound.CLINK);

        cardColor = Charmander.CARD_COLOR;
        tips.clear();
        tips.add(new PowerTip(name, description));
    }
 
    @Override
    public void atBattleStartPreDraw() {
        AbstractCard[] possibleCards = CardLibrary.cards.values().stream()
                .filter(c -> c instanceof PokemonCard)
                .filter(c -> c.color == Pokemon.partner.cardColor)
                .filter(c -> c.color == Pokemon.adventurer.cardColor)
                .filter(c -> c.color != CardColor.COLORLESS)
                .filter(c -> !c.tags.contains(CardTags.STARTER_DEFEND))
                .filter(c -> !c.tags.contains(CardTags.STARTER_STRIKE))
                .toArray(AbstractCard[]::new);
            AbstractCard c = possibleCards[(int)AbstractDungeon.cardRng.random(possibleCards.length-1)];
            if (c.cost > 0) {
                c.cost = 0;
                c.costForTurn = 0;
                c.isCostModified = true;
              } 
        addToBot(new MakeTempCardInDrawPileAction(c, 1, true, false, false));
    }

    // Description
    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }

}
