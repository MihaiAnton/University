package Domain;



public class Registration implements IdEntity<Domain.Pair<Integer,RaceTypeLenPair>> {

    private int athleteId;
    private RaceLength length;
    private Style style;

    public Style getStyle() {
        return style;
    }

    public void setStyle(Style style) {
        this.style = style;
    }

    public Registration(int athleteId, RaceLength length, Style style) {
        this.athleteId = athleteId;
        this.length = length;
        this.style = style;
    }

    public void setRaceId( RaceTypeLenPair id){
        this.length = id.getFirst();
        this.style = id.getSecond();
    }

    public RaceTypeLenPair getRaceId(){
        return new RaceTypeLenPair(length,style);
    }

    public int getAthleteId() {
        return athleteId;
    }

    public void setAthleteId(int athleteId) {
        this.athleteId = athleteId;
    }

    @Override
    public Domain.Pair<Integer, RaceTypeLenPair> getId() {
        Domain.Pair<Integer, RaceTypeLenPair> pair = new Pair<Integer, RaceTypeLenPair>() {
            @Override
            public Integer getFirst() {
                return super.getFirst();
            }
        };
        pair.setFirst(athleteId);
        pair.setSecond(getRaceId());

        return pair;
    }

    @Override
    public void setId(Domain.Pair<Integer, RaceTypeLenPair> integerRaceTypeLenPairPair) {
        this.athleteId = integerRaceTypeLenPairPair.getFirst();
        this.setRaceId(integerRaceTypeLenPairPair.getSecond());
    }
}
