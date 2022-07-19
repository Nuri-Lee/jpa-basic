package helloJpa;

import javax.persistence.*;
import java.util.List;

public class JpaMain {

    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();

       EntityTransaction tx = em.getTransaction();
       tx.begin();

       try {

           Team team = new Team();
           team.setName("TeamA");
           em.persist(team);    // id 에 값이 들어감 -> 영속상태가 되면 무조건 pk 값이 세팅됨

           Member member = new Member();
           member.setUsername("member1");
           member.setTeamId(team.getId());
           em.persist(member);

           Member findMember = em.find(Member.class, member.getId());
           Long findTeamId = findMember.getTeamId();
           Team teamId = em.find(Team.class, findTeamId);

           // -> 객체지향스럽지 않은 방식식

          tx.commit();
       } catch (Exception e){
           tx.rollback();
       } finally {
           em.close();
       }
        emf.close();
    }
}