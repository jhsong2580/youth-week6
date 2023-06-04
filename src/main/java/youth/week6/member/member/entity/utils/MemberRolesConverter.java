package youth.week6.member.member.entity.utils;

import java.util.Arrays;
import java.util.EnumSet;
import javax.persistence.AttributeConverter;
import org.springframework.util.StringUtils;
import youth.week6.member.member.entity.MemberRoles;

public class MemberRolesConverter implements AttributeConverter<EnumSet<MemberRoles>, String> {

    @Override
    public String convertToDatabaseColumn(EnumSet<MemberRoles> attribute) {
        StringBuilder sb = new StringBuilder();

        attribute.stream()
            .forEach(e -> sb.append(e.name()+","));
        String result = sb.toString();

        if(result.charAt(result.length() - 1) == ',') {
            result = result.substring(0, result.length() - 1);
        }

        return result;
    }

    @Override
    public EnumSet<MemberRoles> convertToEntityAttribute(String dbData) {

        if(dbData == null || dbData == "" || dbData.contains(".")) {
            return EnumSet.noneOf(MemberRoles.class);
        }

        EnumSet<MemberRoles> attribute = EnumSet.noneOf(MemberRoles.class);

        String[] dbDataArray = StringUtils.trimAllWhitespace(dbData)
            .toUpperCase()
            .split(",");

        Arrays.stream(dbDataArray).forEach(e -> attribute.add(MemberRoles.valueOf(e)));
        return attribute;
    }
}
