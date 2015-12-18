package test.testHelpers;

import java.lang.reflect.Field;

/**
 * Created by Sebastian Börebäck on 2015-12-16.
 */
public class TestHelper {
    public static <F> F getPrivateField( String fieldName, Object obj)
            throws NoSuchFieldException, IllegalAccessException {
        Field field =
                obj.getClass().getDeclaredField( fieldName );

        field.setAccessible( true );
        return (F)field.get( obj );
    }
}
