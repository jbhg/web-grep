package app;

/**
 * Created by jbg on 11/16/15.
 */
public class Settings
{
    public enum Runmode { PRODUCTION, DEBUG }

    public static Runmode runmode()
    {
        return Runmode.PRODUCTION;
    }
}
