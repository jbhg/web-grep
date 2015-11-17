package dataaccess;

import app.Debug;

import java.util.*;

/**
 * Created by jbg on 11/16/15.
 */
public class EmailManager
{
    private Set<String> _processedSites;
    private Map<String, String> _emailSites;

    public EmailManager()
    {
        _processedSites = new HashSet<>();
        _emailSites = new HashMap<>();
    }

    public void putAll(String site, List<String> allEmails)
    {
        _processedSites.add(site);
        Debug.println("Found emails " + allEmails + " on site " + site);
        for (String email : allEmails)
        {
            if (!_emailSites.containsKey(email))
            {
                _emailSites.put(email, site);
            }
        }
    }

    public Set<String> getAll()
    {
        return _emailSites.keySet();
    }

    // TODO: deprecate this and figure out how to grab the site name.
    public void putAll (List<String> emails)
    {
        putAll("", emails);
    }
}
