package db;

import domain.Blog;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

public class BlogDB {

    ArrayList<Blog> blogs = new ArrayList<Blog>();
    AtomicInteger id = new AtomicInteger();

    public BlogDB(){
        Blog blog1 = new Blog(id.getAndIncrement(),"Wat ben je van plan om te doen vandaag");
        blogs.add(blog1);

        Blog blog2 = new Blog(id.getAndIncrement(),"Naar welke muziek ben je momenteel aan het luistern");
        blogs.add(blog2);

        Blog blog3 = new Blog(id.getAndIncrement(),"Wat zijn de exaamenvragen voor web4");
        blogs.add(blog3);

        Blog blog4 = new Blog(id.getAndIncrement(),"Wat ben je van plan om te doen vandaag");
        blogs.add(blog4);

        Blog blog5 = new Blog(id.getAndIncrement()," Hoeveel studiepunten heb je nog?");
        blogs.add(blog5);

    }

    public ArrayList<Blog> getBlogs() {
        return blogs;
    }

    public Blog getBlogById(int id){
        for(Blog b: blogs){
            if(id == b.getId()) return b;
        }
        return null;
    }


}
