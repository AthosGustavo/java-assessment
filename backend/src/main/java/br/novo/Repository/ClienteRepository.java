package br.novo.Repository;

import br.novo.Model.Cliente;
import br.novo.Model.Livro;
import br.novo.Util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class ClienteRepository {
  private Session session;
  public ClienteRepository(){
    SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
    this.session = sessionFactory.openSession();
  }

  public Cliente getCliente(Long id){
    Session session = HibernateUtil.getSessionFactory().openSession();
    Cliente cliente = null;

    try{
      cliente = session.get(Cliente.class, id);
    }catch(Exception ex){
      ex.printStackTrace();
    } finally{
      session.close();
    }
    return cliente;
  }
  public void save(Cliente cliente){
    Transaction iniciaTransacao = null;

    try{
      iniciaTransacao = this.session.beginTransaction();
      this.session.save(cliente);
      iniciaTransacao.commit();
    }catch (Exception e) {
      if (iniciaTransacao != null) {
        iniciaTransacao.rollback();
      }
      System.out.println(e.getMessage());
      throw e;
    }
  }

  public void editaCliente(Cliente cliente){
    Transaction iniciaTransacao = null;

    try{
      iniciaTransacao = this.session.beginTransaction();
      this.session.merge(cliente);
      iniciaTransacao.commit();
    }catch (Exception e) {
      if (iniciaTransacao != null) {
        iniciaTransacao.rollback();
      }
      System.out.println(e.getMessage());
      throw e;
    }
  }


  public List<Cliente> exibirCliente() {
    return this.session.createQuery("FROM Cliente", Cliente.class).list();
  }

  public void removeCliente(Long id){
    Transaction iniciaTransacao = null;
    Cliente cliente = null;

    try{
      iniciaTransacao = this.session.beginTransaction();
      cliente = this.session.get(Cliente.class, id);
      this.session.delete(cliente);
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
