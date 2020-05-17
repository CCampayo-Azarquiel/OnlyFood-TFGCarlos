package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.OptimisticLockException;
import javax.persistence.Query;
import javax.persistence.RollbackException;

import entidades.Cadena;
import entidades.Oferta;
import entidades.OfertaPK;
import entidades.Usuario;


public class DaoOferta extends BaseJPADao{

	public DaoOferta() {
		// TODO Auto-generated constructor stub
	}
	
	public void nuevaOferta(Oferta o,Cadena cadena) {
		System.out.println("entra en nuevaOferta");
		EntityManager em= getEntityManager();
		EntityTransaction tx = em.getTransaction();
		try {
			Long idOferta=(long) cadena.getOfertas().size()+1;
			OfertaPK oPK=new OfertaPK();
			oPK.setCadena(cadena.getId());
			oPK.setId(idOferta);
			o.setId(oPK);
			o.setCadenaBean(cadena);
			//Usuario u=em.find(Usuario.class, o.getUsuarioBean().getUsuario());
			Cadena c=em.find(Cadena.class, cadena.getId());
			tx.begin();
			em.persist(o);
			System.out.println("antes de persistir oferta nueva");
			tx.commit();
			em.refresh(c);
			//em.refresh(u);
			//em.refresh(o);
			//em.refresh(o.getUsuarioBean());
			em.close();
			System.out.println("commit nuevaOferta");
		} catch (OptimisticLockException e) {
			System.out.println("entra en OptimisticLockException nuevaOferta metodo");
			em.close();
			throw e;
		} catch (RollbackException e) {
			System.out.println("entra en RollbackException nuevaOferta metodo");
			if (tx.isActive())
				tx.rollback();
			em.close();
			throw e;
		}	
	}

	public void eliminarOferta(String idcadena, String idoferta) {
		// TODO Auto-generated method stub
		System.out.println("entra en eliminarOferta");
		EntityManager em= getEntityManager();
		EntityTransaction tx = em.getTransaction();
		try {
			Oferta o=new Oferta();
			Long id=Long.parseLong(idoferta);
			Long cadena=Long.parseLong(idcadena);
			OfertaPK oPK=new OfertaPK();
			oPK.setCadena(cadena);
			oPK.setId(id);
			o=em.find(Oferta.class,oPK);
			Usuario u=em.find(Usuario.class, o.getUsuarioBean().getUsuario());
			Cadena c=em.find(Cadena.class, cadena);
			tx.begin();
			System.out.println("antes de remove eliminarOferta");
			em.remove(o);
			tx.commit();
			em.refresh(c);
			em.refresh(u);
			em.close();
			System.out.println("commit eliminarOferta");
		} catch (OptimisticLockException e) {
			System.out.println("entra en OptimisticLockException eliminarOferta metodo");
			em.close();
			throw e;
		} catch (RollbackException e) {
			System.out.println("entra en RollbackException eliminarOferta metodo");
			if (tx.isActive())
				tx.rollback();
			em.close();
			throw e;
		}	
	}

	public List<Oferta> getAllOfertas() {
		EntityManager em=getEntityManager();
		List<Oferta> allOfertas;
		Query allOfertaQuery =em.createQuery("select a from Oferta a ");
		allOfertas = (List<Oferta>) allOfertaQuery.getResultList();
		for(Oferta a:allOfertas)
		em.refresh(a);
		em.close();
		//System.out.println(allCadenas.get(0).getNombre());
		return allOfertas;
	}
	
}