package br.novo.Repository;

import br.novo.Model.Livro;
import br.novo.Util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class LivroRepository {

  private Session session;
  public LivroRepository(){
    SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
    this.session = sessionFactory.openSession();
  }

  public void save(Livro livro){
    Transaction iniciaTransacao = null;

    try{
      iniciaTransacao = this.session.beginTransaction();
      this.session.save(livro);
      iniciaTransacao.commit();
    }catch (Exception e) {
      if (iniciaTransacao != null) {
        iniciaTransacao.rollback();
      }
      System.out.println(e.getMessage());
      throw e;
    }

  }
  public List<Livro> exibirLivros(){
    return this.session.createQuery("FROM Livro", Livro.class).list();
  }

  public boolean removerLivro(Long id) {
    Transaction iniciaTransacao = null;
    boolean isRemoved = false;

    try {
      iniciaTransacao = this.session.beginTransaction();
      Livro livro = this.session.get(Livro.class, id);
      if (livro != null) {
        this.session.delete(livro);
        iniciaTransacao.commit();
        isRemoved = true;
      }
    } catch (Exception e) {
      if (iniciaTransacao != null) {
        iniciaTransacao.rollback();
      }
      System.out.println(e.getMessage());
      throw e;
    }

    return isRemoved;
  }

  public Livro getLivro(Long id){
    Session session = HibernateUtil.getSessionFactory().openSession();
    Livro livro = null;

    try{
      livro = session.get(Livro.class, id);
    }catch(Exception ex){
      ex.printStackTrace();
    } finally{
      session.close();
    }
    return livro;
  }

  public void editaLivro(Livro livro){
    Transaction iniciaTransacao = null;

    try{
      iniciaTransacao = this.session.beginTransaction();
      this.session.merge(livro);
      iniciaTransacao.commit();
    }catch (Exception e) {
      if (iniciaTransacao != null) {
        iniciaTransacao.rollback();
      }
      System.out.println(e.getMessage());
      throw e;
    }
  }
}
