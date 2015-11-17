package parsing.crawljax.plugins;

import app.Matching;
import com.crawljax.core.CrawlerContext;
import com.crawljax.core.plugin.OnNewStatePlugin;
import com.crawljax.core.state.StateVertex;
import dataaccess.EmailManager;

import java.util.List;

/**
 * A plugin to the crawljax implementation to filter for email addresses.
 */
public class RecordEmailsPlugin implements OnNewStatePlugin
{
    private final EmailManager _emailManager;

    public RecordEmailsPlugin (EmailManager emailManager)
    {
        _emailManager = emailManager;
    }

    @Override
    public void onNewState (CrawlerContext context, StateVertex newState)
    {
        List<String> emails = Matching.findAllEmails(context.getBrowser().getUnStrippedDom());
        if (emails != null)
        {
            _emailManager.putAll(newState.getUrl(), emails);
        }
    }
}
