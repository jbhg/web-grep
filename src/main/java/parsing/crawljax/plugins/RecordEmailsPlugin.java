package parsing.crawljax.plugins;

import app.*;
import com.crawljax.core.*;
import com.crawljax.core.plugin.*;
import com.crawljax.core.state.*;
import dataaccess.*;

import java.util.*;

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
            _emailManager.putAll (emails);
        }
    }
}
