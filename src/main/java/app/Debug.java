package app;

/**
 * Created by jbg on 11/16/15.
 */
public class Debug
{
    public static void println(final String s)
    {
        if (Settings.runmode().equals(Settings.Runmode.DEBUG))
        {
            System.out.println(s);
        }
    }
}
