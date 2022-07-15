package annotations.room;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TableCreator {


    public static String getConstrains(Constraints con) {
        String constraints = "";
        if (!con.allowNull()) {
            constraints += " NOT NULL";
        }
        if (con.primaryKey()) {
            constraints += " PRIMARY KEY";
        }
        if (con.unique()) {
            constraints += " UNIQUE";
        }
        return constraints;
    }

    public static List<String> handle(Class<?>... aClasses) {
        List<String> list = new ArrayList<>();
        for (Class<?> aClass : aClasses) {
            DBTable dbTable = aClass.getAnnotation(DBTable.class);
            if (dbTable == null) {
                System.err.format("name: %s 不支持处理\n", aClass.getName());
                continue;
            }
            String tableName = dbTable.name();
            if (tableName.length() < 1) {
                tableName = aClass.getName();
            }
            List<String> columnDefs = new ArrayList<>();
            for (Field field : aClass.getDeclaredFields()) {
                String fieldName = field.getName();
                Annotation[] ans = field.getAnnotations();
                if (ans.length < 1) continue;
                if (ans[0] instanceof SQLString) {
                    SQLString sqlStr = (SQLString) ans[0];
                    if (sqlStr.name().length() >= 1) {
                        fieldName = sqlStr.name();

                    }
                    columnDefs.add(String.format("%s STRING VARCHAR(%d)%s",
                            fieldName, sqlStr.value(), getConstrains(sqlStr.constraints())));
                } else if (ans[0] instanceof SQLInteger) {
                    SQLInteger sqlInt = (SQLInteger) ans[0];
                    if (sqlInt.name().length() > 1) {
                        fieldName = sqlInt.name();
                    }
                    columnDefs.add(String.format("%s INT%s",
                            fieldName, getConstrains(sqlInt.constraints())));
                }
            }

            StringBuilder createCommands = new StringBuilder(
                    String.format("CREATE TABLE %s (", tableName)
            );
            for (String def : columnDefs) {
                createCommands.append("\n  " + def + ",");
            }
            String tableCreate = createCommands.substring(0, createCommands.length() - 1) + ")";
            list.add(tableCreate);

        }
        return list;
    }


    public static void main(String[] args) {
        List<String> commands = handle(UserRoom.class, String.class);
        for (String command : commands) {
            System.out.println(command);
        }
    }
}
