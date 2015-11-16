package app;

import parsing.*;
import parsing.crawljax.*;

import java.util.*;

public class Application {

    public static void main(String[] args) {
        String startUrl;
        if (args.length == 0)
        {
            startUrl = "http://jana.com";
        }
        else
        {
            startUrl = args[0];
        }

        if (!startUrl.startsWith("http://"))
        {
            startUrl = "http://" + startUrl;
        }

        IPageParser pageParser = new CrawljaxPageParser (Settings.getDepth());
        Set<String> results = pageParser.printAllEmails(startUrl);
        if (results != null)
        {
            for (String result : results)
            {
                System.out.println (result);
            }
        }
    }

    /**
     * Created by jbg on 11/16/15.
     */
    private static class Settings
    {
        public static int getDepth() { return 2; }
    }
}
