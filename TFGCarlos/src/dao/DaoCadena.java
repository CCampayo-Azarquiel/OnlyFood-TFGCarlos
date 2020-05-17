package dao;

import java.util.List;

import javax.persistence.EntityManager;

import javax.persistence.Query;

import entidades.Cadena;


public class DaoCadena extends BaseJPADao{

	public DaoCadena() {
		// TODO Auto-generated constructor stub
	}
	public List<Cadena> getAllCadenas(){
		EntityManager em=getEntityManager();
		List<Cadena> allCadenas;
		Query allCadenaQuery =em.createQuery("select a from Cadena a ");
		allCadenas = (List<Cadena>) allCadenaQuery.getResultList();
		for(Cadena a:allCadenas)
		em.refresh(a);
		em.close();
		//System.out.println(allCadenas.get(0).getNombre());
		return allCadenas;
	}

	/*
	 * public List<Oferta> getOfertasdeCadena(String id) { EntityManager
	 * em=getEntityManager(); int intId=Integer.parseInt(id); Cadena
	 * cadena=em.find(Cadena.class, intId); return cadena.getOfertas(); }
	 */
	public Cadena seachCadenabyId(String id) {
		EntityManager em=getEntityManager();
		System.out.println("el id es"+id);
		long longId=Long.parseLong(id);
		System.out.println("el longId es"+longId);
		Cadena cadena=em.find(Cadena.class, longId);
		em.refresh(cadena);
		return cadena;
	}


}
