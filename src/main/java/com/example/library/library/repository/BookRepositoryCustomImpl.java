package com.example.library.library.repository;


import com.example.library.library.model.Book;
import com.example.library.library.model.User;
import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.Session;
import org.hibernate.criterion.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;


@Repository
public class BookRepositoryCustomImpl implements BookRepositoryCustom {

    @PersistenceContext
    private EntityManager em;

    @Override
    public List<Book> filterBook(String filterStr) {
         /* Session session = em.unwrap(Session.class);
        Criteria criteria = session.createCriteria(Book.class);
        criteria.createAlias("author", "author");
        criteria.setFetchMode("author", FetchMode.JOIN);
        LogicalExpression disjunction = Restrictions.or(
                Restrictions.ilike("name", filterStr, MatchMode.ANYWHERE),
                Restrictions.ilike("author.lastName", filterStr, MatchMode.ANYWHERE)
        );
        criteria.add(disjunction);
        criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        return criteria.list();*/
      Session session = em.unwrap(Session.class);
        Criteria criteria = session.createCriteria(Book.class);
        criteria.createAlias("author", "author");
        criteria.setFetchMode("author", FetchMode.JOIN);

        Criterion restriction1 = Restrictions.ilike("name", filterStr, MatchMode.ANYWHERE);
        Criterion restriction3 = Restrictions.ilike("genre", filterStr, MatchMode.ANYWHERE);
        Criterion restriction4 = Restrictions.ilike("year_of_issue", filterStr, MatchMode.ANYWHERE);
        Criterion restriction2 = Restrictions.ilike("author.firstName", filterStr, MatchMode.ANYWHERE);
        Criterion restriction5 = Restrictions.ilike("author.lastName", filterStr, MatchMode.ANYWHERE);

        Criterion cr = Restrictions.or(restriction1, restriction2,/* restriction3, restriction4,*/ restriction5);

        criteria.add(cr);
        return criteria.list();
    }
}
