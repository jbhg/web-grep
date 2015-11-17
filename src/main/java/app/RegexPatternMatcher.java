package app;

import org.jsoup.nodes.Document;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Static methods to handle regular expression pattern matching.
 */
public class RegexPatternMatcher
{
    private static String EMAIL_STRING = "[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+";
    private static Pattern EMAIL_PATTERN = Pattern.compile(EMAIL_STRING);

    public static List<String> findAllEmails(final Document document)
    {
        if (document != null && document.body() != null)
        {
            return findAllEmails(document.body().toString());
        }
        return new ArrayList<>(); // empty list
    }

    public static List<String> findAllEmails (final String content) {
        List<String> result = new ArrayList<>();
        if (content != null)
        {
            Matcher m = EMAIL_PATTERN.matcher(content);
            while (m.find())
            {
                result.add(m.group());
            }
        }
        return result;
    }
}
