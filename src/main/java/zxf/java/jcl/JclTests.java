package zxf.java.jcl;

import org.xeustechnologies.jcl.JarClassLoader;
import org.xeustechnologies.jcl.JclObjectFactory;
import org.xeustechnologies.jcl.context.DefaultContextLoader;
import org.xeustechnologies.jcl.context.JclContext;
import org.xeustechnologies.jcl.proxy.CglibProxyProvider;
import org.xeustechnologies.jcl.proxy.ProxyProviderFactory;

import java.io.FileInputStream;
import java.net.URL;

public class JclTests {
    public static void main(String[] args) {
        JarClassLoader jcl = new JarClassLoader();

        //Loading classes from different sources
        jcl.add("myjar.jar");
        //jcl.add(new URL("http://myserver.com/myjar.jar"));
        //jcl.add(new FileInputStream("myotherjar.jar"));
        jcl.add("myclassfolder/");

        // Set default to cglib (from version 2.2.1)
        ProxyProviderFactory.setDefaultProxyProvider( new CglibProxyProvider() );
        // -Djcl.autoProxy=true

        JclObjectFactory factory = JclObjectFactory.getInstance();

        //Create object of loaded class
        Object obj = factory.create(jcl, "mypack.MyClass");


        //In order to access the created JarClassLoader instance from any where in the application a JclContext must be created. The DefaultContextLoader provides a way to create this context for a single programmatically created JarClassLoader instance.
        DefaultContextLoader context=new DefaultContextLoader(jcl);
        context.loadContext();
        JarClassLoader jcl1 = JclContext.get();
    }
}
