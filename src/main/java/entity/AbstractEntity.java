package entity;

import java.util.concurrent.atomic.AtomicInteger;

public abstract class AbstractEntity {
    public static final AtomicInteger idGenerator = new AtomicInteger();
    private Integer id;

    public AbstractEntity() {
        this.id = idGenerator.incrementAndGet();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
