package Database;

import org.junit.jupiter.api.Test;

import static Database.Security.generateHash;
import static org.junit.jupiter.api.Assertions.*;

class SecurityTest {

    private String compare="ca978112ca1bbdcafac231b39a23dc4da786eff8147c4e72b9807785afee48bb";

    @Test
    void generateHashTest1() throws Exception
    {
        assertEquals(generateHash("a"),compare.toUpperCase());
    }


}