package konicki.mateusz.greendaosample.entites;

/**
 * Created by Mateusz on 17.04.2017.
 */

public enum MatchType {
    OneVsOne(1),
    TwoVsTwo(2),
    OneVsTwo(3);

    private int id;

    MatchType(int id) {
        this.id = id;
    }

    public static MatchType of(Integer databaseValue) {
        for (MatchType type : MatchType.values()) {
            if (type.getId() != databaseValue) {
                continue;
            }
            return type;
        }
        return null;
    }

    public int getId() {
        return id;
    }


}
