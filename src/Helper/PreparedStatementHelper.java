package Helper;

/**
 * Created by Carlos Zubiran on 5/18/2016.
 */
public class PreparedStatementHelper {

    //region PROPERTIES


    //endregion PROPERTIES

    //region CONSTRUCTORS


    //endregion CONSTRUCTORS

    //region GETTERS / SETTERS


    //endregion GETTERS / SETTERS

    //region CUSTOM METHODS


    // This method will help create prepared statements with
    // the proper questionmarks, instead of counting all of them
    public static String callNameAndNumOfProcedures(String nameOfProcedure , int num) {
        StringBuilder sb = new StringBuilder();

        sb.append("{call " + nameOfProcedure + "(?,?");
        for (int i = 2; i < (num) ; i++) {
            sb.append(",?");
        }
        sb.append(")}");

        return sb.toString();
    }

    //endregion CUSTOM METHODS


}
