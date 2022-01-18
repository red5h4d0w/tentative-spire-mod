package mysteryDungeon.pokemons;

import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.cards.AbstractCard.CardColor;

import mysteryDungeon.characters.Pokemon;

public class Charmeleon extends AbstractPokemon {

    public static final Color COLOR = Color.RED.cpy();
    public static final CardColor CARD_COLOR = Pokemon.Enums.CHARMANDER_RED;

    public Charmeleon(){
        super(COLOR, CARD_COLOR);
        evolution = new Charizard();
    }
}
