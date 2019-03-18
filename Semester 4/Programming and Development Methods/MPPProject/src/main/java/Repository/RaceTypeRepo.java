package Repository;

import Domain.*;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.sql.ResultSet;
import java.util.Properties;

public class RaceTypeRepo extends DataBaseRepo<Domain.Pair<RaceLength, Style>, RaceType> {
    public RaceTypeRepo(String tableName, Properties properties) {
        super(tableName, properties);
    }

    @Override
    String getInsertStatement(RaceType entity) {
        return "INSERT INTO racetypes (raceLength, style) " +
                "VALUES ('" + entity.getLen().toString() + "','" + entity.getStyle() + "')";
    }

    @Override
    String getUpdateStatement(RaceType entity){
        return "UPDATE racetypes " +
                "SET raceLength ='" + entity.getLen().toString() + "'," + "style = '" + entity.getStyle().toString() + "'" +
                "WHERE raceLength ='" + entity.getLen().toString() + "' and" + "style = '" + entity.getStyle().toString() + "'";
    }

    @Override
    String getDeleteStatement(Domain.Pair<RaceLength, Style> raceLengthStylePair) {
        raceLengthStylePair = (RaceTypeLenPair)raceLengthStylePair;
        return "DELETE FROM racetypes " +
                "WHERE raceLength ='" + ((RaceTypeLenPair) raceLengthStylePair).getFirst() + "' and" +
                "style = '" + ((RaceTypeLenPair) raceLengthStylePair).getSecond() + "'";
    }

    @Override
    String getSelectStatement(Pair<RaceLength, Style> raceLengthStylePair) {
        raceLengthStylePair = (RaceTypeLenPair)raceLengthStylePair;
        return "SELECT raceLength, style FROM racetypes " +
                "WHERE raceLength ='" + ((RaceTypeLenPair) raceLengthStylePair).getFirst() + "' and " +
                "style='" + ((RaceTypeLenPair) raceLengthStylePair).getSecond() + "'";
    }

    @Override
    RaceType getEntity(ResultSet resultSet) {
        try {
            if (resultSet.next()) {
                String len = resultSet.getString("raceLength");
                String style = resultSet.getString("style");
                return new RaceType(RaceLength.valueOf(len),Style.valueOf(style));
            }
        }
        catch (Exception e){
            Logger logger = LogManager.getLogger("DataBase Logger");
            logger.error("Exception in raceTypes repo getEntity method.");
            return null;
        }
        return null;
    }

}
