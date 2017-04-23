package konicki.mateusz.greendaosample.entites;

import org.greenrobot.greendao.converter.PropertyConverter;

/**
 * Created by Mateusz on 23.04.2017.
 */

public class MatchTypeConverter implements PropertyConverter<MatchType, Integer> {
    @Override
    public MatchType convertToEntityProperty(Integer databaseValue) {
        return MatchType.of(databaseValue);
    }

    @Override
    public Integer convertToDatabaseValue(MatchType entityProperty) {
        return entityProperty.getId();
    }
}