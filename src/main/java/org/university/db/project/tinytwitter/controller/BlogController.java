package org.university.db.project.tinytwitter.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.university.db.project.tinytwitter.controller.base.AbstractMenuController;
import org.university.db.project.tinytwitter.entity.Blog;
import org.university.db.project.tinytwitter.service.BlogService;
import org.university.db.project.tinytwitter.service.TwitterContext;

import java.util.Date;
import java.util.List;

@Controller
public class BlogController extends AbstractMenuController {
    @Autowired
    BlogService blogService;

    @Autowired
    BlogViewController blogViewController;

    protected BlogController() {
        super("Browse Blogs");
    }

    @Override
    protected void registerMenu() {
        register("Create Blog", this::addBlog);
        register("Update Blog", this::updateBlog);
        register("Search Blog", this::searchBlog);
        register("Delete Blog", this::deleteBlog);
        register("View Blog", blogViewController);
    }

    @Override
    protected ControllerResult process(TwitterContext context) {
        TwitterContext.BlogSearchContext searchContext = context.getBlogSearchContext();
        List<Blog> blogList = blogService.searchBlog(searchContext);
        context.setBlogList(blogList);

        for (int i = 0; i < blogList.size(); i++) {
            Blog blog = blogList.get(i);
            System.out.printf("%2d. | Title : %s", i+1, blog.getTitle());
            if (searchContext.getUser() != null) {
                System.out.println("    | Author: " + blog.getUser().getName());
            }
            if (searchContext.getBlogContent() != null) {
                String keyword = searchContext.getBlogContent();
                String[] pieces = blog.getContent().split(keyword);
                StringBuilder sb = new StringBuilder();
                if (pieces[0].length() <= 10) {
                    sb.append(pieces[0]);
                } else {
                    sb.append("... ").append(pieces[0].substring(pieces.length-10)).append(keyword);
                }
                for (int j = 1; j < pieces.length - 1; j++) {
                    if (pieces[j].length() <= 20) {
                        sb.append(pieces[j]).append(keyword);
                    } else {
                        sb.append(pieces[j], 0, 10).append(" ... ")
                                .append(pieces[j].substring(pieces[j].length()-10))
                                .append(keyword);
                    }
                }
                if (pieces[pieces.length-1].length() <= 10) {
                    sb.append(pieces[pieces.length-1]);
                } else {
                    sb.append(pieces[pieces.length-1], 0, 10).append(" ...");
                }
                System.out.println("    | Content: " + sb);
            }
        }
        return ControllerResult.NORMAL;
    }

    public ControllerResult addBlog(TwitterContext context) {
        Blog blog = new Blog();

        System.out.print("title  : ");
        blog.setTitle(context.getIn().next());
        System.out.print("content: ");
        blog.setContent(context.getIn().next());
        blog.setAuthor(context.getUser().getUserId());
        blog.setCreateDate(new Date());
        blog.setUpdateDate(blog.getCreateDate());
        blog.setAuthor(context.getUser().getUserId());
        blogService.add(blog);
        context.setBlog(blog);
        System.out.println("Blog \"" + blog.getTitle() + "\" created");

        return ControllerResult.NORMAL;
    }

    public ControllerResult updateBlog(TwitterContext context) {
        System.out.print("Enter blog number: ");
        int num = context.getIn().nextInt();
        if (num < 1 || context.getBlogList().size() < num) {
            System.out.println("Invalid blog number");
            return ControllerResult.NORMAL;
        }

        Blog blog = context.getBlogList().get(num - 1);
        boolean modified = queryModifyString("title", context.getIn(), blog::setTitle)
                || queryModifyString("content", context.getIn(), blog::setTitle);

        if (modified) {
            blog.setUpdateDate(new Date());
            blogService.update(blog);
            System.out.println("Blog " + blog.getTitle() + " updated");
        }
        return ControllerResult.NORMAL;
    }

    private ControllerResult searchBlog(TwitterContext context) {
        TwitterContext.BlogSearchContext searchContext = context.getBlogSearchContext();
        querySpecifyString("user", context.getIn(), searchContext::setUser);
        querySpecifyString("title", context.getIn(), searchContext::setBlogTitle);
        querySpecifyString("content", context.getIn(), searchContext::setBlogContent);
        querySpecifyString("type", context.getIn(), searchContext::setBlogType);
        querySpecifyBool("like", context.getIn(), searchContext::setIsLike);
        querySpecifyBool("collection", context.getIn(), searchContext::setIsCollected);
        return ControllerResult.NORMAL;
    }

    private ControllerResult deleteBlog(TwitterContext context) {
        System.out.print("Enter blog number: ");
        int num = context.getIn().nextInt();
        if (num < 0 || num >= context.getBlogList().size()) {
            System.out.println("Invalid blog number");
            return ControllerResult.RETURN;
        }

        Blog blog = context.getBlogList().get(num);
        blogService.delete(blog);
        System.out.println("Blog " + blog.getTitle() + " deleted");

        return ControllerResult.NORMAL;
    }
}