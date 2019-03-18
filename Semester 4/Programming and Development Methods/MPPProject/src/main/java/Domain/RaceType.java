package Domain;


public class RaceType implements IdEntity<Domain.Pair<RaceLength,Style>> {

    public RaceLength len;
    public Style style;

    public RaceType(RaceLength len, Style style) {
        this.len = len;
        this.style = style;
    }

    public RaceLength getLen() {
        return len;
    }

    public void setLen(RaceLength len) {
        this.len = len;
    }

    public Style getStyle() {
        return style;
    }

    public void setStyle(Style style) {
        this.style = style;
    }


    @Override
    public Pair<RaceLength, Style> getId() {
        return new RaceTypeLenPair(len,style);
    }

    @Override
    public void setId(Pair<RaceLength, Style> raceLengthStylePair) {
        this.len = raceLengthStylePair.getFirst();
        this.style = raceLengthStylePair.getSecond();
    }
}
