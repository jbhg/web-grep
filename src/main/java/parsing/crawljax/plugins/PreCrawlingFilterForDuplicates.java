package parsing.crawljax.plugins;

import app.Debug;
import com.crawljax.core.CandidateElement;
import com.crawljax.core.CrawlerContext;
import com.crawljax.core.plugin.PreStateCrawlingPlugin;
import com.crawljax.core.state.StateVertex;
import com.google.common.collect.ImmutableList;

/**
 * This class is intended to look at the available objects to determine
 * whether we can avoid crawling the same page more than once.
 */
public class PreCrawlingFilterForDuplicates implements PreStateCrawlingPlugin
{
    @Override
    public void preStateCrawling(CrawlerContext context, ImmutableList<CandidateElement> candidateElements, StateVertex state)
    {
        Debug.println("Joel was here.");
        for (CandidateElement element : candidateElements.asList())
        {
            System.out.println("Element: " + element.getElement().getTagName());
        }
    }
}
