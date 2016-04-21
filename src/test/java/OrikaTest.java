
import ma.glasnost.orika.BoundMapperFacade;
import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.OrikaSystemProperties;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import org.hamcrest.Matcher;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;


public class OrikaTest {
    @Test
    public void mapPrivateClass() {
        System.setProperty(OrikaSystemProperties.WRITE_SOURCE_FILES,"true");

        MapperFactory mapperFactory = new DefaultMapperFactory.Builder().build();
        mapperFactory.classMap(A.class, Target.class).
                field("name", "TARGETName").
                register();


        logger.debug("Creating facade");

        MapperFacade facade = mapperFactory.getMapperFacade();
        A a = new AImpl();
        Target target = facade.map(a, Target.class);
        assertThat(target.getTARGETName(), is(a.getName()));

    }

    private final static Logger logger =
        LoggerFactory.getLogger(OrikaTest.class);

}
