package dataaccess;

import app.Debug;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Storage for emails found; associates an email address with the first site
 * on which it was found.
 */
public class EmailManager
{
    private final Map<String, String> _emailSites;

    public EmailManager()
    {
        _emailSites = new HashMap<>();
    }

    /**
     * Writes all emails found on a particular site.
     * @param siteUrl web URL on which emails were found
     * @param allEmails email addresses (collection).
     */
    public void putAll(final String siteUrl, final List<String> allEmails)
    {
        Debug.println("Found emails " + allEmails + " on site " + siteUrl);
        for (final String email : allEmails)
        {
            if (!_emailSites.containsKey(email)) // if not already listed
            {
                _emailSites.put(email, siteUrl);
            }
        }
    }

    /**
     * @return unique email addresses found while scraping this particular site
     */
    public Set<String> getAll()
    {
        Debug.println(_emailSites.toString());
        return _emailSites.keySet();
    }
}
