package app;

import parsing.IPageParser;
import parsing.crawljax.CrawljaxPageParser;

import java.util.Set;

public class Application
{
    public static void main(String[] args)
    {
        String startUrl = DefaultSettings.getDefaultUrl();
        int maxDepth = DefaultSettings.getDepth();

        if (args.length >= 1)
        {
            startUrl = args[0];
        }

        if (args.length >= 2)
        {
            try
            {
                maxDepth = Integer.parseInt(args[1]);
            }
            catch (NumberFormatException e)
            {
                // do nothing, use default value.
            }
        }

        if (!startUrl.startsWith("http://"))
        {
            startUrl = "http://" + startUrl;
        }

        IPageParser pageParser = new CrawljaxPageParser(maxDepth);
        Set<String> results = pageParser.printAllEmails(startUrl);
        if (results != null)
        {
            for (final String result : results)
            {
                System.out.println(result);
            }
        }
    }

    /**
     * Default application settings.
     */
    private static class DefaultSettings
    {
        public static int getDepth()
        {
            return 2;
        }

        public static String getDefaultUrl()
        {
            return "http://startupinstitute.com";
        }
    }
}
