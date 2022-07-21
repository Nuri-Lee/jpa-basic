package helloJpa;

import net.bytebuddy.dynamic.loading.InjectionClassLoader;

import javax.persistence.*;

@Entity
public class Member {

    @Id
    @GeneratedValue
    @Column(name = "MEMBER_ID")
    private Long id;

    @Column(name = "USERNAME")
    private String username;

    @ManyToOne  // member 입장에서 member 은 N 이고, Team 은 1 이기 때문이다.
    @JoinColumn(name = "TEAM_ID")   // 조인하는 컬럼 매핑 = 관리한다는 뜻
    private Team team;  // Member 클래스의 mappedBy 로 연결되어 있는 대상 = 연관관계의 주인

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }
}
