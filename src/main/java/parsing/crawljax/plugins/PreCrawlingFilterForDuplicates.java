package parsing.crawljax.plugins;

import com.crawljax.core.CandidateElement;
import com.crawljax.core.CrawlerContext;
import com.crawljax.core.plugin.PreStateCrawlingPlugin;
import com.crawljax.core.state.StateVertex;
import com.google.common.collect.ImmutableList;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

/**
 * This class is intended to look at the available objects to determine
 * whether we can avoid crawling the same page more than once.
 */
public class PreCrawlingFilterForDuplicates implements PreStateCrawlingPlugin
{
    private static Set<String> _seenSites;

    public PreCrawlingFilterForDuplicates()
    {
        _seenSites = new HashSet<>();
    }
    @Override
    public void preStateCrawling(CrawlerContext context, ImmutableList<CandidateElement> candidateElements, StateVertex state)
    {
        Set<CandidateElement> elementsToRemove = new HashSet<>();
        System.out.println("Current page: " + context.getCurrentState().getUrl());
        for(CandidateElement i : state.getCandidateElements())
        {
            if (i.getElement().hasAttribute("href"))
            {
                System.out.println("\tcandidate has link: " + i.getElement().getAttribute("href"));
                if (!_seenSites.add(i.getElement().getAttribute("href")))
                {
                    System.out.println("\t\tRemoving link: " + i.getElement().getAttribute("href"));
                    elementsToRemove.add(i);
                }
            }
        }
        LinkedList<CandidateElement> newElements = new LinkedList<>();
        for (CandidateElement i : state.getCandidateElements())
        {
            if (!elementsToRemove.contains(i))
            {
                newElements.add(i);
            }
        }
        state.setElementsFound(newElements);
    }
}
