package parsing.crawljax;


import com.crawljax.browser.EmbeddedBrowser;
import com.crawljax.core.CrawljaxRunner;
import com.crawljax.core.configuration.BrowserConfiguration;
import com.crawljax.core.configuration.CrawljaxConfiguration;
import dataaccess.EmailManager;
import dataaccess.PageManager;
import parsing.IPageParser;
import parsing.crawljax.plugins.PreCrawlingFilterForDuplicates;
import parsing.crawljax.plugins.RecordEmailsPlugin;

import java.util.Set;

/**
 * Created by jbg on 11/16/15.
 */
public class CrawljaxPageParser implements IPageParser
{
    private final EmailManager _emailManager = new EmailManager();
    private final PageManager _pageManager = new PageManager();
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
                .addPlugin(new PreCrawlingFilterForDuplicates())
                .addPlugin(new RecordEmailsPlugin(_emailManager, _pageManager))
                ;
        builder.crawlRules().click("a");
        CrawljaxRunner crawljax = new CrawljaxRunner(builder.build());
        crawljax.call();
        return _emailManager.getAll();
    }

}
