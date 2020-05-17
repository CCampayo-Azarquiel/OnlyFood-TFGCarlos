package dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.OptimisticLockException;
import javax.persistence.RollbackException;

import entidades.Grupo;
import entidades.GrupoPK;
import entidades.Oferta;
import entidades.Usuario;
import util.Hash;

public class DaoUsuario extends BaseJPADao{

	public DaoUsuario() {
		// TODO Auto-generated constructor stub
	}
	public void insertaUsuario(Usuario usuario) {
		System.out.println("entra en insertar socio");
		EntityManager em= getEntityManager();
		EntityTransaction tx = em.getTransaction();
		try {
			//Usuario usuario = new Usuario();
			Grupo grupo = new Grupo();
			GrupoPK gPK = new GrupoPK();
			usuario.setClave(Hash.md5(usuario.getClave()));
			gPK.setIdgrupo("usuario");
			gPK.setIdusuario(usuario.getUsuario());
			//usuario.setUsuario(user);
			System.out.println("peta antes");
			// usuario.setGrupos(grupo);
			System.out.println("peta");
			grupo.setId(gPK);
			//grupo.setId(gPK);
			grupo.setUsuario(usuario);
			tx.begin();
			em.persist(usuario);
			System.out.println(grupo.getId().getIdgrupo()+grupo.getId().getIdusuario());
			System.out.println(grupo.getUsuario().getUsuario()+grupo.getUsuario().getClave());
			System.out.println("antes de persistir insertarUsuario");
			em.persist(grupo);
			//em.persist(usuario);
			tx.commit();
			em.close();
			System.out.println("commit insertarUsuario");
		} catch (OptimisticLockException e) {
			System.out.println("entra en OptimisticLockException insertarUsuario metodo");
			em.close();
			throw e;
		} catch (RollbackException e) {
			System.out.println("entra en RollbackException insertarUsuario metodo");
			if (tx.isActive())
				tx.rollback();
			em.close();
			throw e;
		}	
	}
	public Usuario encontrarUsuario(String usuario) {
		System.out.println("entra encontrarUsuario");
		System.out.println(usuario);
		EntityManager em= getEntityManager();
		Usuario user=em.find(Usuario.class,usuario);
		em.close();
		/*
		 * System.out.println(user.getUsuario()+user.getGrupos()); for(int
		 * i=0;i<user.getOfertas().size();i++) {
		 * System.out.println("------------------------"); Oferta
		 * o=user.getOfertas().get(i); System.out.println(o.getImagen()); }
		 */
		System.out.println("termina encontrarUsuario");
		return user;
	}
	public Usuario refrescarUsuario(Usuario user) {
		EntityManager em= getEntityManager();
		user=em.find(Usuario.class,user.getUsuario());
		em.refresh(user);
		em.close();
		return user;
	}
}
