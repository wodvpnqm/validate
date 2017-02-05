package validate;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Administrator on 2017/1/12.
 */
public class TestPattern {

    Pattern pattern = Pattern.compile("\\{(.+?)\\}");


    @Test
    public void test1() {

        String message = "sdfasdf{0}sfsdf{1}胜多负少东方闪电";
        Matcher matcher = pattern.matcher(message);
        StringBuffer sb = new StringBuffer();
        Map<String,String> tokens = new HashMap<String,String>();
        tokens.put("cat", "Garfield");
        tokens.put("beverage", "coffee");
        tokens.put("0", "零");
        tokens.put("1", "壹");
        while(matcher.find())
        {
            System.out.println(matcher.group());
            System.out.println(matcher.group(1));
            if(tokens.containsKey(matcher.group(1)))
            {
                matcher.appendReplacement(sb, tokens.get(matcher.group(1)));
            }
        }
        matcher.appendTail(sb);

        System.out.println(sb.toString());
        System.out.println();
    }

}
