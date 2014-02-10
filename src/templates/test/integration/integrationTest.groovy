@package.line@

import static org.junit.Assert.*
import org.junit.*

class @controller.name@IntegrationTest {
    def @controller.name@Test,tokenAdmin

    @Before
     void setUp() {

        @controller.name@Test = new @controller.name@()
        tokenAdmin = ArrestedUser.findByUsername('user@test.me')?.token.token

    }

    @After
    void tearDown() {
        // Tear down logic here
    }

    @Test
    void testGetById(){
        @controller.name@Test.params.id =  1
        @controller.name@Test.getById()
        assertNotNull(@controller.name@Test.response.json)
    }

    @Test
    void testGetAll(){
        @controller.name@Test.getAll()
        assertNotNull(@controller.name@Test.response)
        assert @controller.name@Test.response.json?.id.size() >= 0
    }

    @Test
    void testCreate() {
        @controller.name@Test.params.@class.instance@ = [] //We must type here the required attributes for the class
        @controller.name@Test.create()
        assertEquals(@controller.name@Test.response.json.response,"@class.name@_created")
        assert @controller.name@Test.response != null
        assertNotNull(@class.name@.findById(@controller.name@Test.response.json.id))
    }

    @Test
    void testUpdate() {
        @controller.name@Test.params.@class.instance@ = [id: 1L]//We need to search for a specific attribute of the class
        @controller.name@Test.update()
        assertEquals(@controller.name@Test.response.json?.response,"@class.name@_updated")
    }

    @Test
    void testDelete(){
        @controller.name@Test.params.id = 1L //We need to search for a specific attribute of the class
        @controller.name@Test.delete()
        assertEquals(response.json.response,"@class.name@_deleted")
        assertNull(@class.name@.findById(@controller.name@Test.params.id as Long))
    }


}