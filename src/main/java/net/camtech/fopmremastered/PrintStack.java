/*
 * Supress warnings for printstacktrace exceptions
 */
package net.camtech.fopmremastered;

public class PrintStack
{

    @SuppressWarnings("CallToPrintStackTrace")
    public static void trace(Exception ex)
    {
        ex.printStackTrace();
    }
}
