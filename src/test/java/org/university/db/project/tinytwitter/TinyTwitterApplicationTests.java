package org.university.db.project.tinytwitter;

import org.springframework.boot.test.context.SpringBootTest;
import org.university.db.project.tinytwitter.dao.BlogMapper;

@SpringBootTest(classes = BlogMapper.class)
//@MapperScan("org.university.db.project.tinytwitter.mapper")
//@ComponentScan("org.university.db.project.tinytwitter")
class TinyTwitterApplicationTests {

//    @Autowired
//    private BlogMapper blogMapper;

//    @Test
//    void contextLoads() {
//        blogMapper.insert(new Blog() {{
//            setTitle("title");
//        }});
//    }

}
