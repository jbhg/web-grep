package parsing.crawljax.plugins;

import app.RegexPatternMatcher;
import com.crawljax.core.CrawlerContext;
import com.crawljax.core.plugin.OnNewStatePlugin;
import com.crawljax.core.state.StateVertex;
import dataaccess.EmailManager;
import dataaccess.PageManager;

import java.util.List;

/**
 * A plugin to the crawljax implementation to filter for email addresses.
 */
public class RecordEmailsPlugin implements OnNewStatePlugin
{
    private final EmailManager _emailManager;
    private final PageManager _pageManager;

    public RecordEmailsPlugin (EmailManager emailManager, PageManager pageManager)
    {
        _emailManager = emailManager;
        _pageManager = pageManager;
    }

    @Override
    public void onNewState (CrawlerContext context, StateVertex newState)
    {
        if (!_pageManager.putSite(newState.getUrl(), context.getCrawlPath().size()))
        {
            return;
        }

        List<String> emails = RegexPatternMatcher.findAllEmails(context.getBrowser().getUnStrippedDom());
        if (emails != null)
        {
            _emailManager.putAll(newState.getUrl(), emails);
        }
    }
}
