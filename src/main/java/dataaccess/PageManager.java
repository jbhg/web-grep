package dataaccess;

import app.Debug;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

/**
 * Maintains a list of which URLs have been visited and a queue for those
 * which must still be visited.
 */
public class PageManager
{
    private final Queue<String> _pageQueue;
    private final Map<String, Integer> _siteLevels;

    public PageManager()
    {
        _pageQueue = new LinkedList<>();
        _siteLevels = new HashMap<>();
    }

    /**
     * Returns true if the site was not already cached; false otherwise.
     * @param site site url
     * @param level depth in scraping tree
     * @return true if the site was not already cached; false otherwise
     */
    public boolean putSite(final String site, final int level)
    {
        if (_siteLevels.containsKey(site))
        {
            Debug.println("Site already enqueued; ignoring: " + site);
            return false;
        }
        else
        {
            Debug.println("Enqueuing site " + site + " at level " + level);
        }
        _pageQueue.add(site);
        _siteLevels.put(site, level);
        return true;
    }

    public boolean hasNext()
    {
        return !_pageQueue.isEmpty();
    }

    /**
     * @pre PageManager#hasNext == true
     * @return the next page to process
     */
    public SiteLevel pop()
    {
        final String page = _pageQueue.remove();
        return new SiteLevel(page, _siteLevels.get(page));
    }
}
