package parsing;

import app.*;
import com.crawljax.browser.*;
import com.crawljax.core.*;
import com.crawljax.core.configuration.*;
import com.crawljax.core.plugin.*;
import com.crawljax.core.state.*;
import dataaccess.*;

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
                .addPlugin (new OnNewStatePlugin () {
                    @Override
                    public void onNewState (CrawlerContext context, StateVertex newState) {
                        List<String> emails = Matching.findAllEmails (context.getBrowser().getUnStrippedDom());
                        if (emails != null)
                        {
                            _emailManager.putAll(emails);
                        }
                    }
                });
        builder.crawlRules().click("a");
        CrawljaxRunner crawljax = new CrawljaxRunner (builder.build());
        crawljax.call();
        return _emailManager.getAll();
    }
}
