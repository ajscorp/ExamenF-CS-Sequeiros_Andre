package org.openjpa.entidades;

import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import org.openjpa.control.exceptions.EntidadPreexistenteException;
import org.openjpa.control.exceptions.NoExisteEntidadException;

public class PersonControl implements Serializable {

    public PersonControl(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
    
    public int insertar(Person person) throws EntidadPreexistenteException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(person);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (buscaPerson(person.getPersonId()) != null) {
                throw new EntidadPreexistenteException("Person " + person + " ya existe en la base de datos.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
        return person.getPersonId();
    }

    public int editar(Person person) throws NoExisteEntidadException {
        EntityManager em = null;
        int aux = 0;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            person = em.merge(person);
            em.getTransaction().commit();
            aux = person.getPersonId();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = person.getPersonId();
                if (buscaPerson(id) == null) {
                    throw new NoExisteEntidadException("El person con el id " + id + " no existe.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
        return aux;
    }

    public void eliminar(Integer id) throws NoExisteEntidadException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Person person;
            try {
                person = em.getReference(Person.class, id);
                person.getPersonId();
            } catch (EntityNotFoundException enfe) {
                throw new NoExisteEntidadException("El person con el id " + id + " no existe.", enfe);
            }
            em.remove(person);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Person> buscaPersons() {
        EntityManager em = getEntityManager();
        try {
            TypedQuery<Person> query = em.createNamedQuery("Person.seleccionaTodos", Person.class);
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    public Person buscaPerson(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Person.class, id);
        } finally {
            em.close();
        }
    }

    public int getTotalAlumno() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Person> rt = cq.from(Person.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    

}
