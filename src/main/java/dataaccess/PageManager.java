package dataaccess;

import app.Debug;
import app.SiteLevel;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

/**
 * Created by jbg on 11/16/15.
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

    public boolean putSite(String site, int level)
    {
        if (_siteLevels.containsKey(site))
        {
            Debug.println("Site already enqueued; ignoring: " + site);
            return false;
        }
        _pageQueue.add(site);
        _siteLevels.put(site, level);
        return true;
    }

    public boolean hasNext()
    {
        return !_pageQueue.isEmpty();
    }

    public SiteLevel pop()
    {
        final String page = _pageQueue.remove();
        return new SiteLevel(page, _siteLevels.get(page));
    }
}
