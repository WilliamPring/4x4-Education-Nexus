import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by William Pring on 3/10/2016.
 */
public class Login_DataBase {
    public static final String DB_NAME = "ACCOUNT.db";
    public static final int DB_VERSION = 1;

    //list table consants
    public static final String LIST_TABLE = "Account";
    public static String NAME = "Name";
    public static String LastName = "Last Name";
    public static String Email = "Email";
    public static String DOB = "Date Of Birth";
    public static String School = "School";
    public static String Password = "Password";
    public static int ID =0;
    public static final String CREATE_ACCOUNT_TABLE =
            "CREATE TABLE" + LIST_TABLE + " (" +
                    ID + "INTERGER PRIMARY KEY AUTOINCREMENT, " +
                    NAME + "TEXT NOT NULL, " +
                    LastName + "TEXT NOT NULL, " +
                    Email + "TEXT NOT NULL, " +
                    DOB + "TEXT NOT NULL, " +
                    School + "TEXT NOT NULL, " +
                    Password + "TEXT NOT NULL);";

    public static final String DROP_LIST_TABLE =
            "DROP TABLE IF EXISTS" + LIST_TABLE;

}
