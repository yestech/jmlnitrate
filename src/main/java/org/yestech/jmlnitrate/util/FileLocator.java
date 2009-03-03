/*
 * File name:           $RCSfile: FileLocator.java,v $
 *
 * Revision:            $Revision: 3 $
 * Last revised by:     $Author: yeslinux $
 * Last revision date:  $Date: 2008-08-16 21:24:01 -0700 (Sat, 16 Aug 2008) $
 *
 * Original Author:     Arthur Copeland
 *
 * Licensed using GPL Available - http://opensource.org/licenses/gpl-license.php
 *
 */

package org.yestech.jmlnitrate.util;

import java.io.InputStream;
import java.net.URL;


/**
 * This class will find any file located in the users classpath.
 *<br>
 *<p>
 * This class was donated by saphari.com version: 1.5....
 *</p>
 *
 * @author Arthur Copeland
 * @version $Revision: 3 $
 *
 */
final public class FileLocator {

    //--------------------------------------------------------------------------
    // S T A T I C   V A R I A B L E S 
    //--------------------------------------------------------------------------
    /**
     * Holds the default filename -> extension delimeter
     */
    private final static String FILE_EXTENSION_DELIMETER = ".";
    
    /**
     * Default Ctor. Creates a new file locator
     */
    private FileLocator() {
        super();
    }
    
    /**
     * Locates a properties file in the classes classpath and returns the URL
     * associated with the file.
     *
     * @param file the file to locate
     * @return the URL associated with the file or NULL if file not found
     */
    public URL locatePropertiesFile(String file) {
        return locateFile(file, "properties");
    }
    
    /**
     * Locates an xml file in the classes classpath and returns the URL
     * associated with the file.
     *
     * @param file the file to locate
     * @return the URL associated with the file or NULL if file not found
     */
    public URL locateXMLFile(String file) {
        return locateFile(file, "xml");
    }
    
    /**
     * Locates a file in the classes classpath and returns the URL
     * associated with the file.  If the file doesn't have an extension then the
     * extension parameter <b>MUST</b> be NULL.
     *
     * @param file the file to locate
     * @param extension the extension of the file to locate
     * @return the URL associated with the file or NULL if file not found
     */
    public URL locateFile(String file, String extension) {
        return getClassLoader().getResource(getFileName(file,extension));
    }
    
    /**
     * Locates a file in the classes classpath and returns the URL
     * associated with the file.
     *
     * @param file the file to locate
     * @return the URL associated with the file or NULL if file not found
     */
    public URL locateFile(String file) {
        return locateFile(file, null);
    }
    
    /**
     * Locates a properties file in the classes classpath and returns the
     * InputStream associated with the file.
     *
     * @param file the file to locate
     * @return the InputStream associated with the file or NULL if file not
     * found
     */
    public InputStream locatePropertiesFileAsStream(String file) {
        return locateFileAsStream(file, "properties");
    }
    
    /**
     * Locates an xml file in the classes classpath and returns the InputStream
     * associated with the file.
     *
     * @param file the file to locate
     * @return the InputStream associated with the file or NULL if file not
     * found
     */
    public InputStream locateXMLFileAsStream(String file) {
        return locateFileAsStream(file, "xml");
    }
    
    /**
     * Locates a file in the classes classpath and returns the
     * InputStream associated with the file.
     *
     * @param file the file to locate
     * @param extension the extension of the file to locate
     * @return the InputStream associated with the file or NULL if file not
     * found
     */
    public InputStream locateFileAsStream(String file,
                                                 String extension) {
        return getClassLoader().getResourceAsStream(getFileName(file,
                                                                extension));
    }
    
    /**
     * Locates a file in the classes classpath and returns the
     * InputStream associated with the file.
     *
     * @param file the file to locate
     * @return the InputStream associated with the file or NULL if file not
     * found
     */
    public InputStream locateFileAsStream(String file) {
        return locateFileAsStream(file, null);
    }
    
    //--------------------------------------------------------------------------
    // F A C T O R Y   M E T H O D S 
    //--------------------------------------------------------------------------
    /**
     * Creates a new FileLocator
     *
     * @return new FileLocator
     */
    public static FileLocator getFile() {
        return new FileLocator();
    }
    
    //--------------------------------------------------------------------------
    // I N T E R N A L   M E T H O D S 
    //--------------------------------------------------------------------------
    /**
     * Return the formated name of the file to find
     *
     * @param file FileName of the file to find
     * @param extension extension to the file name
     * @return the properly formatted filename to locate
     */
    private String getFileName(String file, String extension) {
        String fileName = file;
        if (extension != null &&
            !extension.startsWith(FILE_EXTENSION_DELIMETER)) {
            fileName += FILE_EXTENSION_DELIMETER + extension;
        }
        return fileName;
    }

    /**
     * Retrieves the ClassLoader to use when trying to locate the resource.
     */
    private ClassLoader getClassLoader() {
        ClassLoader cl = getClass().getClassLoader();
        if (cl == null) {
            cl = ClassLoader.getSystemClassLoader();
        }        
        return cl;
    }
    
}

/*
 * $Log: FileLocator.java,v $
 * Revision 1.2  2003/09/05 04:21:27  boo
 * tagging version 1.1.2
 *
 * Revision 1.1  2002/11/19 07:08:52  boo
 * initial add from move to YES
 *
 * Revision 1.1  2001/12/17 05:44:12  boo
 * intial add.
 *
 * Revision 1.1  2001/12/16 11:21:37  boo
 * replaced make with ant build system.  add the filelocator utility.
 *
 * Revision 1.5  2001/11/13 02:22:36  boo
 * *** empty log message ***
 *
 * Revision 1.4  2001/10/31 03:28:44  boo
 * converted from a static -> instance implementation.
 *
 * Revision 1.3  2001/10/29 01:03:50  boo
 * removed logging.
 *
 * Revision 1.2  2001/10/29 01:00:01  boo
 * added fall back to system classLoader.
 *
 * Revision 1.1  2001/10/28 06:02:19  boo
 * initial add.
 *
 */
