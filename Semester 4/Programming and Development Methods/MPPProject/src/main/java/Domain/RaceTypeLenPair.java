package Domain;

public class RaceTypeLenPair extends Pair<RaceLength, Style> {

    RaceLength length;
    Style style;

    public RaceTypeLenPair(RaceLength length,Style style){
        this.length = length;
        this.style = style;
    }

    @Override
    public RaceLength getFirst() {
        return length;
    }

    @Override
    public void setFirst(RaceLength raceLength) {
        this.length = raceLength;
    }

    @Override
    public Style getSecond() {
        return style;
    }

    @Override
    public void setSecond(Style style) {
        this.style = style;
    }
}
