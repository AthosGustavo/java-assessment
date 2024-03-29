package br.novo.Repository;

import br.novo.Model.Autor;
import br.novo.Model.Livro;
import br.novo.Util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.NativeQuery;

import java.util.List;

public class AutorRepository {

  private Session session;
  private LivroRepository livroRepository;
  public AutorRepository(){
    SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
    this.session = sessionFactory.openSession();
    this.livroRepository = new LivroRepository();
  }

  public List<Autor> exibirAutores() {
    return this.session.createQuery("FROM Autor", Autor.class).list();
  }

  public void save(Autor autor){
    Transaction iniciaTransacao = null;

    try{
      iniciaTransacao = this.session.beginTransaction();
      this.session.save(autor);
      iniciaTransacao.commit();
    }catch (Exception e) {
      if (iniciaTransacao != null) {
        iniciaTransacao.rollback();
      }
      System.out.println(e.getMessage());
      throw e;
    }

  }

  public Autor getAutor(Long id) {
    Session session = HibernateUtil.getSessionFactory().openSession();
    Autor autor = null;
    try {
      autor = session.get(Autor.class, id);
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      session.close();
    }
    return autor;
  }

  public void saveEdit(Autor autor){
    Transaction iniciaTransacao = null;

    try{
      iniciaTransacao = this.session.beginTransaction();
      this.session.merge(autor);
      iniciaTransacao.commit();
    }catch (Exception e) {
      if (iniciaTransacao != null) {
        iniciaTransacao.rollback();
      }
      System.out.println(e.getMessage());
      throw e;
    }
  }

  public boolean removerAutor(Long id){
    Transaction iniciaTransacao = null;
    boolean isRemoved = false;

    try {
      Livro possuiLivro = verificaSeAutorPossuiLivro(id);
      if(possuiLivro != null){
        livroRepository.removerLivro(possuiLivro.getId());
      }

      iniciaTransacao = this.session.beginTransaction();
      Autor autor = this.session.get(Autor.class, id);
      if (autor != null) {
        this.session.delete(autor);
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

  public Livro verificaSeAutorPossuiLivro(Long id) {
    Transaction iniciaTransacao = null;
    Livro livro = null;

    try {
      iniciaTransacao = this.session.beginTransaction();
      String sql = "SELECT * FROM Livro WHERE autor_id = :id";
      NativeQuery query = this.session.createNativeQuery(sql, Livro.class);
      query.setParameter("id", id);
      livro = (Livro) query.uniqueResult();
      iniciaTransacao.commit();
    } catch (Exception e) {
      if (iniciaTransacao != null) {
        iniciaTransacao.rollback();
      }
      System.out.println(e.getMessage());
      throw e;
    }

    return livro;
  }
}
