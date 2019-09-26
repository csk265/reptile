package com.csk.tool.string; 
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;   
import com.csk.tool.object.ObjectExpression;
public class StringTemplateUtils {
	public static final String DEF_REGEX="\\{(.+?)\\}";

    public static String render(String template, Map<String, Object> data) {
        return render(template,data,DEF_REGEX);
    }

    public static String render(String template, Map<String, Object> data,String regex) {
        if(template==null||template.length()==0){
            return "";
        }
        if(regex==null||regex.length()==0){
            return template;
        }
        if(data == null || data.size() == 0){
            return template;
        }
        try {
            StringBuffer sb = new StringBuffer();
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(template);
            while (matcher.find()) {
                String name = matcher.group(1);// 键名 
                Object o=ObjectExpression.getValue(data, name);
                String value =o==null?"":o.toString();// 键值
                if (value == null) {value = "";}
                matcher.appendReplacement(sb, value);
            }
            matcher.appendTail(sb);
            return sb.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return template;

    }
}

