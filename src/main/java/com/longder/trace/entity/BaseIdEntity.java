package com.longder.trace.entity;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.io.Serializable;

/**
 * 统一实体类
 */
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@MappedSuperclass
public class BaseIdEntity implements Serializable {

    @Id
    @Column(name = "id_")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        BaseIdEntity that = (BaseIdEntity) o;

        return id != null && id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return 1274844179;
    }
}
