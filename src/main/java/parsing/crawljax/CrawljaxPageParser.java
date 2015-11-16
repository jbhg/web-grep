package parsing.crawljax;

import com.crawljax.browser.*;
import com.crawljax.core.*;
import com.crawljax.core.configuration.*;
import dataaccess.*;
import parsing.*;
import parsing.crawljax.plugins.*;

import java.util.*;

/**
 * Created by jbg on 11/16/15.
 */
public class CrawljaxPageParser implements IPageParser
{
    private final EmailManager _emailManager = new EmailManager();
    private final int _maxDepth;

    public CrawljaxPageParser(final int maxDepth)
    {
        _maxDepth = maxDepth;
    }

    @Override
    public Set<String> printAllEmails (final String startingUrl)
    {
        CrawljaxConfiguration.CrawljaxConfigurationBuilder builder =
                CrawljaxConfiguration.builderFor(startingUrl)
                .setBrowserConfig(new BrowserConfiguration(EmbeddedBrowser.BrowserType.CHROME, 2))
                .setMaximumDepth(_maxDepth)
                .addPlugin(new RecordEmailsPlugin (_emailManager));
        builder.crawlRules().click("a");
        CrawljaxRunner crawljax = new CrawljaxRunner (builder.build());
        crawljax.call();
        return _emailManager.getAll();
    }
}
