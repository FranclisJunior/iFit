//package test;
//
//import javax.persistence.EntityManager;
//import javax.persistence.EntityManagerFactory;
//import javax.persistence.Persistence;
//
//import br.com.ifit.model.Aluno;
//
//public class TestarHibernate {
//
//	public static void main(String[] args) {
//		EntityManagerFactory factory = Persistence.createEntityManagerFactory("iFitP");
//		EntityManager em = factory.createEntityManager();
//		Aluno p = new Aluno();
//		
//		p.setCpf("09141635400");
//		p.setNome("Renan");
//		p.setEmail("renan@soares.com");
//		p.setSenha("renan123");
//		
//		em.getTransaction().begin();
//		em.persist(p);
//		em.getTransaction().commit();
//	
//
//	}
//
//}
