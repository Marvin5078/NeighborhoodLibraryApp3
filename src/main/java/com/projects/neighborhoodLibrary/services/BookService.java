package com.projects.neighborhoodLibrary.services;

import com.projects.neighborhoodLibrary.data.BookDao;
import com.projects.neighborhoodLibrary.models.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.repository.query.FluentQuery;

import java.util.List;
import java.util.function.Function;

public class BookService
{
    private BookDao bookDao;

    @Autowired
    public BookService(BookDao bookDao)
    {
        this.bookDao = bookDao;
    }

    //Create
    public Book createBook(Book book)
    {
     return this.bookDao.save(book);
    }

    //Retrieve
    public List<Book> getAllBooks()
    {
        return this.bookDao.findAll();
    }
    public Book getBookById(int bookId)
    {
        return this.bookDao.findById(bookId).orElse((new Book()));
    }
    public <S extends Book, R> R getBookByTitle(String title, Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryRFunction)
    {
        return (R) this.bookDao.getBookByTitle(title);
    }

    //Update/CheckOut
    public Book checkOutBook(int bookId, String name)
    {
        Book bookToCheckOut = this.bookDao.findById(bookId).orElse(null);

        if(bookToCheckOut == null)
        {
            return new Book();
        }
        bookToCheckOut.setCheckedOut(true);
        bookToCheckOut.setCheckedOutTo(name);

        return this.bookDao.save(bookToCheckOut);
    }

    public Book checkInBook(int bookId)
    {
        Book bookToCheckIn = this.bookDao.findById(bookId).orElse(null);

        if (bookToCheckIn == null)
        {
            return new Book();
        }
        bookToCheckIn.setCheckedOut(false);
        bookToCheckIn.setCheckedOutTo("");

        return this.bookDao.save(bookToCheckIn);
    }

    public Book updateBook(int bookId, String name)
    {
        Book bookToUpdate = this.bookDao.findById(bookId).orElse(null);

        if(bookToUpdate == null)
        {
            return new Book();
        }
        bookToUpdate.setCheckedOutTo(name);

        return this.bookDao.save(bookToUpdate);
    }


}
