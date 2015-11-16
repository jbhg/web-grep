package app;

import org.jsoup.nodes.Document;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by jbg on 11/16/15.
 */
public class Matching
{
    public static String EMAIL_STRING = "[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+";
    public static Pattern EMAIL_PATTERN = Pattern.compile(EMAIL_STRING);

    public static List<String> findAllEmails(final Document document)
    {
        if (document != null && document.body() != null)
        {
            return findAllEmails(document.body().toString());
        }
        return new ArrayList<>();
    }

    public static List<String> findAllEmails (final String content) {
        List<String> result = new ArrayList<>();
        Matcher m = EMAIL_PATTERN.matcher(content);
        while (m.find())
        {
            result.add(m.group());
        }
        return result;
    }
}
