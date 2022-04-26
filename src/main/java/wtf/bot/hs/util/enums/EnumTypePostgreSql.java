package wtf.bot.hs.util.enums;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.type.EnumType;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

public class EnumTypePostgreSql extends EnumType {

    @Override
    public void nullSafeSet(PreparedStatement st, Object value, int index,
                            SharedSessionContractImplementor session)
            throws HibernateException, SQLException {
        if(value == null) {
            st.setNull( index, Types.OTHER );
        } else {
            Class<? extends PersistableEnum> enumClass = returnedClass();
            st.setObject( index, enumClass.cast(value).getValue(), Types.OTHER );
        }
    }

    @Override
    public Object nullSafeGet(ResultSet rs, String[] names, SharedSessionContractImplementor session, Object owner) throws SQLException {
        Class<? extends PersistableEnum> enumClass = returnedClass();
        PersistableEnum[] enumConstants = enumClass.getEnumConstants();
        for (PersistableEnum enumConstant : enumConstants) {
            String value = rs.getString(names[0]);
            if (rs.wasNull()) {
                return null;
            }
            if (enumConstant.getValue().equals(value)) {
                return enumConstant;
            }
        }
       return null;
    }
}
