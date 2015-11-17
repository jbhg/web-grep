package parsing.jsoup;

import app.Debug;
import app.RegexPatternMatcher;
import dataaccess.EmailManager;
import org.apache.commons.validator.routines.UrlValidator;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import parsing.IPageParser;

import java.io.IOException;
import java.util.Set;

/**
 * Created by jbg on 11/16/15.
 */
public class JsoupPageParser implements IPageParser
{
    private final EmailManager _emailManager = new EmailManager();
    private final PageManager _pageManager = new PageManager();
    private final String[] _schemes = {"http","https"};
    private final UrlValidator _urlValidator = new UrlValidator(_schemes);
    private final int _maxDepth;

    public JsoupPageParser(final int maxDepth)
    {
        _maxDepth = maxDepth;
    }

    @Override
    public Set<String> printAllEmails(String startingUrl)
    {
        try
        {
            _parsePage(startingUrl, 1);
        }
        catch (IOException e)
        {
            System.err.println("Error parsing top level URL; aborting.");
            e.printStackTrace();
            System.exit(1);
        }
        while (_pageManager.hasNext())
        {
            SiteLevel siteLevel = _pageManager.pop();
            try
            {
                _parsePage(siteLevel.page(), siteLevel.level());
            }
            catch (IOException e)
            {
                System.err.println("Error parsing URL: " + siteLevel.page() + "; continuing...");
                e.printStackTrace();
            }
        }
        return _emailManager.getAll();
    }

    private void _parsePage(final String site, final int level) throws IOException {
        Debug.println("Processing " + level + ":" + site);
        final Document doc = Jsoup.connect(site).get();
        _emailManager.putAll(site, RegexPatternMatcher.findAllEmails(doc));
        if (level < _maxDepth)
        {
            _enqueueLinksForProcessing(doc, level);
        }
    }

    private void _enqueueLinksForProcessing(Document doc, int level) {
        Elements children = doc.select("a");
        for (Element e : children)
        {
            final String absoluteUrl = e.attr("abs:href"); // http://jsoup.org/cookbook/extracting-data/working-with-urls
            if (_urlValidator.isValid(absoluteUrl))
            {
                _pageManager.putSite(absoluteUrl, level + 1);
            }
            else
            {
                Debug.println("INVALID: " + absoluteUrl);
            }
        }
    }
}
