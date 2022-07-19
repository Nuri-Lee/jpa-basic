package helloJpa;

import net.bytebuddy.dynamic.loading.InjectionClassLoader;

import javax.persistence.*;

@Entity
@SequenceGenerator(
        name = "MEMBER_SEQ_GENERATOR",
        sequenceName = "MEMBER_SEQ", // 매핑할 데이터베이스 시퀀스 이름
        // allocationSize = 50 :
        // next call 할 때 미리 50개를 불러오는 것 (최적화 향상)
        // -> 매번 next call 로 가져오면 네트워크를 타야하기 때문에 성능 문제가 있다.
        // 이론적으로 allocationSize 값을 증가시키면 좋지만, ( ex 만개)
        // 하지만 이렇게 했을 때 웹서버가 내려갔을 때 다 날아간다 ..
        // 그 사이 숫자에 구멍이 생긴다. -> 괜히 낭비가 됨
        // 즉, 50~100이 적절하다.
        // 다만 서버가 여러 대면 상관없다.
        initialValue = 1, allocationSize = 50
)
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
                    generator = "MEMBER_SEQ_GENERATOR")
    private Long id;

    @Column(name = "name", nullable = false)
    private String username;

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

    public Member() {
    }
}
