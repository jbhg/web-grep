package dataaccess;

/**
 * An object representing an absolute URL and its depth within the search.
 */
public class SiteLevel {

    private final String _page;
    private final int _level;

    public SiteLevel(final String page, final int level)
    {
        _page = page;
        _level = level;
    }

    public String page()
    {
        return _page;
    }

    public int level()
    {
        return _level;
    }

}
